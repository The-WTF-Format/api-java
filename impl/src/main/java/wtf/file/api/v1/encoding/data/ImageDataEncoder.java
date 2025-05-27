package wtf.file.api.v1.encoding.data;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.jetbrains.annotations.NotNull;
import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.color.channel.FixedColorChannel;
import wtf.file.api.editable.compression.DataCompressionType;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.WriteBitStream;
import wtf.file.api.v1.impl.editable.EditableWtfImageImpl;
import wtf.file.api.v1.pixel.BitSize;
import wtf.file.api.v1.pixel.ImageData;
import wtf.file.api.v1.pixel.type.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ImageDataEncoder {

    @NotNull
    public static ImageData asImageData(EditableWtfImageImpl image, DataCompressionType compressionType) throws WtfException {
        Map<Long, Integer> colorOccurrences = new HashMap<>();
        Map<Long, Map<ColorChannel, Short>> clutEntries = new HashMap<>();

        PixelInformation[][][] pixels = new PixelInformation[image.animationInformation().frames()][image.height()][image.width()];
        for (int frame = 0; frame < image.animationInformation().frames(); frame++) {
            for (int y = 0; y < image.height(); y++) {
                for (int x = 0; x < image.width(); x++) {
                    DirectPixelInformation pixelInformation = new DirectPixelInformation(frame, x, y, image.animationInformation().frame(frame).at(x, y).values());
                    colorOccurrences.put(pixelInformation.code(), colorOccurrences.getOrDefault(pixelInformation.code(), 0) + 1);
                    pixels[frame][x][y] = pixelInformation;
                }
            }
        }

        // apply mapped/clut compression
        if (compressionType.useClut()) {
            Map<Long, Long> clutCodes = new HashMap<>();
            AtomicLong clutIndex = new AtomicLong(1L);
            colorOccurrences.entrySet().stream()
                .filter((entry) -> entry.getValue() > 5)
                .forEach((entry) -> clutCodes.put(entry.getKey(), clutIndex.getAndIncrement()));

            for (int frame = 0; frame < image.animationInformation().frames(); frame++) {
                for (int y = 0; y < image.height(); y++) {
                    for (int x = 0; x < image.width(); x++) {
                        DirectPixelInformation pixelInformation = (DirectPixelInformation) pixels[frame][x][y];
                        if (clutCodes.containsKey(pixelInformation.code())) {
                            long clutCode = clutCodes.get(pixelInformation.code());
                            pixels[frame][x][y] = new ClutEntryPixelInformation(frame, x, y, clutCode);

                            if (!clutEntries.containsKey(clutCode)) {
                                clutEntries.put(clutCode, pixelInformation.channelValues());
                            }
                        }
                    }
                }
            }
        }

        // apply reference compression
        if (compressionType.useReference()) {
            Multimap<Long, int[]> references = ArrayListMultimap.create();

            for (int frame = 0; frame < image.animationInformation().frames(); frame++) {
                for (int y = 0; y < image.height(); y++) {
                    for (int x = 0; x < image.width(); x++) {
                        PixelInformation pixelInformation = pixels[frame][x][y];
                        if (!(pixelInformation instanceof DirectPixelInformation directInformation)) {
                            references.put(((ClutEntryPixelInformation) pixelInformation).code(), new int[]{frame, x, y});
                            continue;
                        }

                        long code = directInformation.code();

                        Collection<int[]> sameReferences = references.get(code);

                        int previousX = x == 0 ? image.width() - 1 : x - 1;
                        int previousY = x == 0 ? y - 1 : y;

                        int[] bestReference = null;
                        BestReferenceType bestEntry = BestReferenceType.NOT_SET;

                        for (int[] reference : sameReferences) {
                            //noinspection DuplicatedCode
                            boolean previousFrame = reference[0] == frame - 1 && reference[1] == x && reference[2] == y;
                            boolean previousLocation = reference[0] == frame && reference[1] == previousX && reference[2] == previousY;
                            boolean sameFrame = reference[0] == frame;
                            boolean sameLocation = reference[1] == x && reference[2] == y;

                            if (previousFrame != previousLocation) {
                                bestReference = reference;
                                break;
                            }

                            if (sameLocation && bestEntry != BestReferenceType.LOCATION_SAME) {
                                bestReference = reference;
                                bestEntry = BestReferenceType.LOCATION_SAME;
                                continue;
                            }

                            if (sameFrame && bestEntry != BestReferenceType.LOCATION_SAME && bestEntry != BestReferenceType.FRAME_SAME) {
                                bestReference = reference;
                                bestEntry = BestReferenceType.FRAME_SAME;
                                continue;
                            }

                            if (bestEntry == BestReferenceType.NOT_SET) {
                                bestReference = reference;
                                bestEntry = BestReferenceType.FULL;
                            }
                        }

                        if (bestReference != null) {
                            //noinspection DuplicatedCode
                            boolean previousFrame = bestReference[0] == frame - 1 && bestReference[1] == x && bestReference[2] == y;
                            boolean previousLocation = bestReference[0] == frame && bestReference[1] == previousX && bestReference[2] == previousY;
                            boolean sameFrame = bestReference[0] == frame;
                            boolean sameLocation = bestReference[1] == x && bestReference[2] == y;

                            if (previousFrame) {
                                pixels[frame][x][y] = new ReferencePixelInformation(frame, x, y, PixelType.COPY_PREVIOUS_FRAME, -1, -2, -2, image.width());
                            } else if (previousLocation) {
                                pixels[frame][x][y] = new ReferencePixelInformation(frame, x, y, PixelType.COPY_PREVIOUS_LOCATION, -2, -1, -1, image.width());
                            } else if (sameFrame) {
                                pixels[frame][x][y] = new ReferencePixelInformation(frame, x, y, PixelType.COPY_BY_LOCATION, -2, bestReference[1], bestReference[2], image.width());
                            } else if (sameLocation) {
                                pixels[frame][x][y] = new ReferencePixelInformation(frame, x, y, PixelType.COPY_BY_FRAME, bestReference[0], -2, -2, image.width());
                            } else {
                                pixels[frame][x][y] = new ReferencePixelInformation(frame, x, y, PixelType.COPY_BY_FRAME_AND_LOCATION, bestReference[0], bestReference[1], bestReference[2], image.width());
                            }
                        }

                        references.put(code, new int[]{frame, x, y});
                    }
                }
            }
        }

        return new ImageData(pixels, clutEntries);
    }

    public static void encode(ColorSpace colorSpace, BitSize bitSize, ImageData data, WriteBitStream bitStream) throws WtfException {
        for (PixelInformation[][] frame : data.pixels()) {
            for (PixelInformation[] row : frame) {
                for (PixelInformation pixel : row) {
                    bitStream.write(pixel.type().flag(), 3);

                    switch (pixel.type()) {
                        case DIRECT_ENTRY -> {
                            Map<ColorChannel, Short> channelValues = ((DirectPixelInformation) pixel).channelValues();
                            writeChannelValues(channelValues, colorSpace, bitSize.channelWidth(), bitStream);
                        }
                        case COPY_BY_LOCATION, COPY_BY_FRAME, COPY_BY_FRAME_AND_LOCATION -> {
                            ReferencePixelInformation pixelInformation = (ReferencePixelInformation) pixel;

                            if (pixelInformation.frameReference() >= 0) {
                                bitStream.writeNumber(pixelInformation.frameReference(), bitSize.frameReference());
                            }

                            if (pixelInformation.xReference() >= 0) {
                                bitStream.writeNumber(pixelInformation.xReference(), bitSize.xReference());
                            }

                            if (pixelInformation.yReference() >= 0) {
                                bitStream.writeNumber(pixelInformation.yReference(), bitSize.yReference());
                            }
                        }
                        case CLUT_ENTRY -> {
                            long clutCode = ((ClutEntryPixelInformation) pixel).clutCode();
                            bitStream.writeNumber(clutCode, bitSize.clutCode());
                        }
                    }
                }
            }
        }

        bitStream.padToByte();
    }

    public static void writeChannelValues(Map<ColorChannel, Short> channelValues, ColorSpace colorSpace, int channelWidth, WriteBitStream bitStream) throws WtfException {
        for (ColorChannel channel : colorSpace.channels()) {
            short value = channelValues.get(channel);

            switch (channel.type()) {
                case FIXED -> bitStream.writeNumber(value, ((FixedColorChannel) channel).bits());
                case DYNAMIC -> bitStream.writeNumber(value, channelWidth);
            }
        }
    }

    private enum BestReferenceType {
        NOT_SET,
        FULL,
        FRAME_SAME,
        LOCATION_SAME,
        PREVIOUS
    }

}

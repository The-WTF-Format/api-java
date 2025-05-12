package wtf.file.api.v1.decoding.data;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.color.channel.FixedColorChannel;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.ReadBitStream;
import wtf.file.api.v1.decoding.clut.ClutInformation;
import wtf.file.api.v1.decoding.header.HeaderInformation;
import wtf.file.api.v1.pixel.PixelInformation;
import wtf.file.api.v1.pixel.PixelType;
import wtf.file.api.v1.pixel.type.ClutEntryPixelInformation;
import wtf.file.api.v1.pixel.type.DirectPixelInformation;
import wtf.file.api.v1.pixel.type.ReferencePixelInformation;

import java.util.HashMap;
import java.util.Map;

public class ImageDataDecoder {

    @NotNull
    public static ImageData decode(HeaderInformation headerInformation, ClutInformation clutInformation, ReadBitStream bitStream) throws WtfException {
        BitSize bitSize = getBitSize(headerInformation, clutInformation);
        PixelInformation[][][] pixels = new PixelInformation[headerInformation.frames()][headerInformation.height()][headerInformation.width()];

        for (int frame = 0; frame < headerInformation.frames(); frame++) {
            for (int y = 0; y < headerInformation.height(); y++) {
                for (int x = 0; x < headerInformation.width(); x++) {
                    if (!bitStream.hasRemaining(3)) {
                        throw new WtfException(String.format("Missing pixel [%d;%d;%d]", frame, y, x));
                    }

                    PixelType pixelType = PixelType.fromFlag(bitStream.readBits(3)[0]);
                    pixels[frame][y][x] =
                            switch (pixelType) {
                                case DIRECT_ENTRY -> {
                                    if (!bitStream.hasRemaining(bitSize.colorEntry())) {
                                        throw new WtfException(String.format("Pixel at [%d;%d;%d] is direct entry but has not enough data for every channel", frame, y, x));
                                    }

                                    Map<ColorChannel, Long> channelValues = new HashMap<>();
                                    for (ColorChannel channel : headerInformation.colorSpace().channels()) {
                                        switch (channel.type()) {
                                            case FIXED ->
                                                    channelValues.put(channel, bitStream.readNumber(((FixedColorChannel) channel).bits()));
                                            case DYNAMIC ->
                                                    channelValues.put(channel, bitStream.readNumber(headerInformation.channelWidth()));
                                        }
                                    }

                                    yield new DirectPixelInformation(frame, x, y, channelValues);
                                }
                                case COPY_BY_LOCATION -> {
                                    if (!bitStream.hasRemaining(bitSize.xReference() + bitSize.yReference())) {
                                        throw new WtfException(String.format("Pixel at [%d;%d;%d] is reference entry but has not enough data for every reference", frame, y, x));
                                    }

                                    int xLocation = Math.toIntExact(bitStream.readNumber(bitSize.xReference()));
                                    int yLocation = Math.toIntExact(bitStream.readNumber(bitSize.yReference()));
                                    yield new ReferencePixelInformation(
                                            frame, x, y, PixelType.COPY_BY_LOCATION,
                                            0, xLocation, yLocation
                                    );
                                }
                                case COPY_BY_FRAME -> {
                                    if (!bitStream.hasRemaining(bitSize.frameReference())) {
                                        throw new WtfException(String.format("Pixel at [%d;%d;%d] is reference entry but has not enough data for every reference", frame, y, x));
                                    }

                                    int frameLocation = Math.toIntExact(bitStream.readNumber(bitSize.frameReference()));
                                    yield new ReferencePixelInformation(
                                            frame, x, y, PixelType.COPY_BY_FRAME,
                                            frameLocation, 0, 0
                                    );
                                }
                                case COPY_BY_FRAME_AND_LOCATION -> {
                                    if (!bitStream.hasRemaining(bitSize.frameReference() + bitSize.xReference() + bitSize.yReference())) {
                                        throw new WtfException(String.format("Pixel at [%d;%d;%d] is reference entry but has not enough data for every reference", frame, y, x));
                                    }

                                    int frameLocation = Math.toIntExact(bitStream.readNumber(bitSize.frameReference()));
                                    int xLocation = Math.toIntExact(bitStream.readNumber(bitSize.xReference()));
                                    int yLocation = Math.toIntExact(bitStream.readNumber(bitSize.yReference()));
                                    yield new ReferencePixelInformation(
                                            frame, x, y, PixelType.COPY_BY_FRAME_AND_LOCATION,
                                            frameLocation, xLocation, yLocation
                                    );
                                }
                                case COPY_PREVIOUS_LOCATION -> new ReferencePixelInformation(
                                        frame, x, y, PixelType.COPY_PREVIOUS_LOCATION,
                                        -2, -1, -1
                                );
                                case COPY_PREVIOUS_FRAME -> new ReferencePixelInformation(
                                        frame, x, y, PixelType.COPY_PREVIOUS_FRAME,
                                        -1, -2, -2
                                );
                                case CLUT_ENTRY -> {
                                    if (!bitStream.hasRemaining(bitSize.clutCode())) {
                                        throw new WtfException(String.format("Pixel at [%d;%d;%d] is clut entry but has not enough data for every clut entry", frame, y, x));
                                    }

                                    long clutCode = bitStream.readNumber(bitSize.clutCode());
                                    yield new ClutEntryPixelInformation(frame, x, y, clutCode);
                                }
                            };
                }
            }
        }

        return new ImageData(pixels);
    }

    private static BitSize getBitSize(HeaderInformation headerInformation, ClutInformation clutInformation) {
        int colorEntry = 0;
        for (ColorChannel channel : headerInformation.colorSpace().channels()) {
            switch (channel.type()) {
                case DYNAMIC -> colorEntry += headerInformation.channelWidth();
                case FIXED -> colorEntry += ((FixedColorChannel) channel).bits();
            }
        }

        return new BitSize(
                colorEntry, clutInformation.codeLength(),
                getBitSize(headerInformation.frames()),
                getBitSize(headerInformation.height()), getBitSize(headerInformation.width())
        );
    }

    private static int getBitSize(int maxValue) {
        return 1 + (int) Math.floor(Math.log(maxValue) / Math.log(2));
    }

}

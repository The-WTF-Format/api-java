package wtf.file.api.v1.encoding.data;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.color.channel.FixedColorChannel;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.WriteBitStream;
import wtf.file.api.v1.pixel.BitSize;
import wtf.file.api.v1.pixel.ImageData;
import wtf.file.api.v1.impl.editable.EditableWtfImageImpl;
import wtf.file.api.v1.pixel.type.ClutEntryPixelInformation;
import wtf.file.api.v1.pixel.type.PixelInformation;
import wtf.file.api.v1.pixel.type.DirectPixelInformation;
import wtf.file.api.v1.pixel.type.ReferencePixelInformation;

import java.util.Map;

public class ImageDataEncoder {

    @NotNull
    public static ImageData asImageData(EditableWtfImageImpl image) {
        throw new NotYetImplementedException();
    }

    public static void encode(ColorSpace colorSpace, BitSize bitSize, ImageData data, WriteBitStream bitStream) throws WtfException {
        for (PixelInformation[][] frame : data.pixels()) {
            for (PixelInformation[] row : frame) {
                for (PixelInformation pixel : row) {
                    bitStream.write(pixel.type().flag(), 3);

                    switch (pixel.type()) {
                        case DIRECT_ENTRY -> {
                            Map<ColorChannel, Short> channelValues = ((DirectPixelInformation) pixel).channelValues();
                            for (ColorChannel channel : colorSpace.channels()) {
                                short value = channelValues.get(channel);

                                switch (channel.type()) {
                                    case FIXED -> bitStream.writeNumber(value, ((FixedColorChannel) channel).bits());
                                    case DYNAMIC -> bitStream.writeNumber(value, bitSize.channelWidth());
                                }
                            }
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
                            int clutCode = ((ClutEntryPixelInformation) pixel).clutCode();
                            bitStream.writeNumber(clutCode, bitSize.clutCode());
                        }
                    }
                }
            }
        }
    }

}

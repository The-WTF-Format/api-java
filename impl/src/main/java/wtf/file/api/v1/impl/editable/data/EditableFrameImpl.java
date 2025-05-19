package wtf.file.api.v1.impl.editable.data;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.ColorSpaceChannels;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.editable.data.EditableFrame;
import wtf.file.api.editable.data.EditablePixel;
import wtf.file.api.exception.NumberOutOfBoundsException;
import wtf.file.api.util.ArrayUtil;
import wtf.file.api.util.ColorUtil;
import wtf.file.api.v1.impl.data.FrameImpl;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public final class EditableFrameImpl extends FrameImpl implements EditableFrame {
    private final EditablePixel[][] pixels;

    public EditableFrameImpl(EditablePixel[][] pixels, ColorSpace colorSpace, int channelWidth) {
        super(pixels, colorSpace, channelWidth);
        this.pixels = pixels;
    }

    @Override
    public EditablePixel[][] pixels() {
        return ArrayUtil.saveCopy(pixels, EditablePixel[][]::new);
    }

    @Override
    public EditablePixel at(int x, int y) throws NumberOutOfBoundsException {
        return (EditablePixel) super.at(x, y);
    }

    @Override
    public void byJavaImage(BufferedImage image) {
        if (image.getWidth() != pixels.length || image.getHeight() != pixels[0].length) {
            throw new IllegalArgumentException("The image dimensions do not match the frame dimensions.");
        }

        ColorSpace imageSpace = switch (image.getType()) {
            case BufferedImage.TYPE_INT_RGB -> ColorSpace.RGB;
            case BufferedImage.TYPE_INT_ARGB -> ColorSpace.DYNAMIC_RGBa;
            default -> throw new IllegalArgumentException("The image type is not supported.");
        };

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Map<ColorChannel, Short> imageValues = new HashMap<>();
                int rgbValue = image.getRGB(x, y);

                int alpha = (rgbValue >> 24) & 0xFF;
                int red = (rgbValue >> 16) & 0xFF;
                int green = (rgbValue >> 8) & 0xFF;
                int blue = rgbValue & 0xFF;

                if (imageSpace == ColorSpace.DYNAMIC_RGBa) {
                    imageValues.put(ColorSpaceChannels.DYNAMIC_ALPHA, (short) alpha);
                }

                imageValues.put(ColorSpaceChannels.RED, (short) red);
                imageValues.put(ColorSpaceChannels.GREEN, (short) green);
                imageValues.put(ColorSpaceChannels.BLUE, (short) blue);

                imageValues = ColorUtil.toColorSpace(imageValues, imageSpace, this.colorSpace, 8);
                imageValues = ColorUtil.toChannelWidth(imageValues, 8, this.channelWidth);

                this.pixels[x][y] = new EditablePixelImpl(imageValues, this.colorSpace, this.channelWidth);
            }
        }
    }

}

package wtf.file.api.v1.impl.data;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.ColorSpaceChannels;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.color.channel.DynamicColorChannel;
import wtf.file.api.color.channel.FixedColorChannel;
import wtf.file.api.data.Frame;
import wtf.file.api.data.Pixel;
import wtf.file.api.exception.NumberOutOfBoundsException;
import wtf.file.api.util.ArrayUtil;
import wtf.file.api.util.NumberUtil;
import wtf.file.api.v1.impl.data.transformation.AlphaTransformer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class FrameImpl implements Frame {

    private final Pixel[][] pixels;
    protected final ColorSpace colorSpace;
    protected final int channelWidth;

    public FrameImpl(Pixel[][] pixels, ColorSpace colorSpace, int channelWidth) {
        this.pixels = pixels;
        this.colorSpace = colorSpace;
        this.channelWidth = channelWidth;
    }

    @Override
    public Pixel[][] pixels() {
        return ArrayUtil.saveCopy(pixels, Pixel[][]::new);
    }

    @Override
    public Pixel at(int x, int y) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(x, 0, pixels.length - 1, "x");
        NumberUtil.checkBounds(y, 0, pixels[0].length - 1, "y");

        return this.pixels()[x][y];
    }

    @Override
    public Image asJavaImage() {
        ColorSpace rgbSpace = switch (AlphaTransformer.INSTANCE.getAlphaChannel(this.pixels()[0][0].colorSpace())) {
            case null -> ColorSpace.RGB;
            case FixedColorChannel ignored -> ColorSpace.RGBa;
            case DynamicColorChannel ignored -> ColorSpace.DYNAMIC_RGBa;
            default -> throw new IllegalArgumentException("Unknown alpha channel type");
        };
        ColorChannel alphaChannel = AlphaTransformer.INSTANCE.getAlphaChannel(rgbSpace);

        BufferedImage javaImage = new BufferedImage(
            this.pixels().length, this.pixels()[0].length,
            rgbSpace == ColorSpace.RGB ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB
        );

        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[x].length; y++) {
                Pixel pixel = pixels[x][y].withColorSpace(rgbSpace).withWidth(8);
                Map<ColorChannel, Short> values = pixel.values();

                short alpha = values.get(alphaChannel),
                    red = values.get(ColorSpaceChannels.RED),
                    green = values.get(ColorSpaceChannels.GREEN),
                    blue = values.get(ColorSpaceChannels.BLUE);

                int rgbValue;
                switch (alphaChannel) {
                    case null -> rgbValue = (red & 0xFF) << 16 | (green & 0xFF) << 8 | (blue & 0xFF);
                    case FixedColorChannel fixedColorChannel when fixedColorChannel.bits() == 1 -> {
                        int actualAlpha = alpha * 255;
                        rgbValue = (actualAlpha & 0xFF) << 24 | (red & 0xFF) << 16 | (green & 0xFF) << 8 | (blue & 0xFF);
                    }
                    case DynamicColorChannel ignored ->
                        rgbValue = (alpha & 0xFF) << 24 | (red & 0xFF) << 16 | (green & 0xFF) << 8 | (blue & 0xFF);
                    default ->
                        throw new IllegalStateException("Alpha channels of fixed size with more than 1 bit are currently not supported.");
                }

                javaImage.setRGB(x, y, rgbValue);
            }
        }

        return javaImage;
    }

}

package wtf.file.api.v1.impl.data;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.data.Pixel;
import wtf.file.api.exception.NumberOutOfBoundsException;
import wtf.file.api.util.ColorUtil;
import wtf.file.api.util.NumberUtil;

import java.util.HashSet;
import java.util.Map;

public class PixelImpl implements Pixel {

    private final Map<ColorChannel, Short> values;
    private final ColorSpace colorSpace;
    protected final int channelWidth;

    public PixelImpl(Map<ColorChannel, Short> values, ColorSpace colorSpace, int channelWidth) {
        this.values = Map.copyOf(values);
        this.colorSpace = colorSpace;
        this.channelWidth = channelWidth;

        if (!values.keySet().equals(new HashSet<>(colorSpace.channels()))) {
            throw new IllegalArgumentException("The values do not match the color space");
        }
    }

    @Override
    public ColorSpace colorSpace() {
        return colorSpace;
    }

    @Override
    public Map<ColorChannel, Short> values() {
        return values;
    }

    @Override
    public short valueOf(ColorChannel channel) {
        Short value = this.values().get(channel);
        if (value == null) {
            throw new IllegalArgumentException("The channel " + channel + " is not part of this pixel's color space");
        }

        return value;
    }

    @Override
    public Pixel withColorSpace(ColorSpace colorSpace) {
        if (colorSpace == this.colorSpace()) {
            return this;
        }

        return new PixelImpl(ColorUtil.toColorSpace(this.values(), this.colorSpace, colorSpace, this.channelWidth),
            colorSpace, channelWidth);
    }

    @Override
    public Pixel withWidth(int channelWidth) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(channelWidth, 1, 16, "channelWidth");
        if (this.channelWidth == channelWidth) {
            return this;
        }

        return new PixelImpl(ColorUtil.toChannelWidth(this.values(), this.channelWidth, channelWidth),
            colorSpace, channelWidth);
    }

}

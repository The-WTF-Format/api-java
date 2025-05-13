package wtf.file.api.v1.impl.data;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.data.Pixel;
import wtf.file.api.exception.NotYetImplementedException;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PixelImpl implements Pixel {

    private final ColorSpace colorSpace;
    private final Map<ColorChannel, Short> values;

    public PixelImpl(ColorSpace colorSpace, Map<ColorChannel, Short> values) {
        this.colorSpace = colorSpace;
        this.values = Map.copyOf(values);

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
        Short value = values.get(channel);
        if (value == null) {
            throw new IllegalArgumentException("The channel " + channel + " is not part of this pixel's color space");
        }

        return value;
    }

    @Override
    public Pixel as(ColorSpace colorSpace) {
        throw new NotYetImplementedException();
    }

}

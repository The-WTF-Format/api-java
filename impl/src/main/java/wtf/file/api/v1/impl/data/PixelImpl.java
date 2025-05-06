package wtf.file.api.v1.impl.data;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.data.Pixel;

import java.util.Map;

public class PixelImpl implements Pixel {

    @Override
    public ColorSpace colorSpace() {
        return null;
    }

    @Override
    public Map<ColorChannel, Short> values() {
        return Map.of();
    }

    @Override
    public short valueOf(ColorChannel channel) {
        return 0;
    }

    @Override
    public Pixel as(ColorSpace colorSpace) {
        return null;
    }

}

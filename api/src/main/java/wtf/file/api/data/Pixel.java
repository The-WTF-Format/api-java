package wtf.file.api.data;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;

import java.util.Map;

public interface Pixel {

    ColorSpace colorSpace();

    Map<ColorChannel, Long> values();

    long valueOf(ColorChannel channel);

    Pixel as(ColorSpace colorSpace);

}

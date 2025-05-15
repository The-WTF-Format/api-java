package wtf.file.api.v1.impl.data.transformation;

import wtf.file.api.color.channel.ColorChannel;

import java.util.Map;

public interface Transformer {

    Map<ColorChannel, Short> toRgb(Map<ColorChannel, Short> values, int channelWidth);

    Map<ColorChannel, Short> fromRgb(Map<ColorChannel, Short> values, int channelWidth);

    default short getMaxValue(int channelWidth) {
        return (short) (Math.pow(2, channelWidth) - 1);
    }



}

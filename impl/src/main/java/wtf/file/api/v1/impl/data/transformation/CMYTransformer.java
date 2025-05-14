package wtf.file.api.v1.impl.data.transformation;

import wtf.file.api.color.ColorSpaceChannels;
import wtf.file.api.color.channel.ColorChannel;

import java.util.Map;

import static wtf.file.api.color.ColorSpaceChannels.*;

public class CMYTransformer implements Transformer{

    public static final CMYTransformer INSTANCE = new CMYTransformer();

    private CMYTransformer() {}

    @Override
    public Map<ColorChannel, Short> toRgb(Map<ColorChannel, Short> values, int channelWidth) {
        int maxValue = getMaxValue(channelWidth);
        return Map.of(
            RED, (short) (maxValue - values.get(CYAN)),
            GREEN, (short) (maxValue - values.get(MAGENTA)),
            BLUE, (short) (maxValue - values.get(YELLOW))
        );
    }

    @Override
    public Map<ColorChannel, Short> fromRgb(Map<ColorChannel, Short> values, int channelWidth) {
        int maxValue = getMaxValue(channelWidth);
        return Map.of(
            CYAN, (short) (maxValue - values.get(RED)),
            MAGENTA, (short) (maxValue - values.get(GREEN)),
            YELLOW, (short) (maxValue - values.get(BLUE))
        );
    }

}

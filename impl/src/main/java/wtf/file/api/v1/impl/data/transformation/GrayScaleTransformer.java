package wtf.file.api.v1.impl.data.transformation;

import wtf.file.api.color.ColorSpaceChannels;
import wtf.file.api.color.channel.ColorChannel;

import java.util.Map;

import static wtf.file.api.color.ColorSpaceChannels.*;

public class GrayScaleTransformer implements Transformer {

    public static final GrayScaleTransformer INSTANCE = new GrayScaleTransformer();

    private GrayScaleTransformer() {}

    @Override
    public Map<ColorChannel, Short> toRgb(Map<ColorChannel, Short> values, int channelWidth) {
        short grayValue = values.get(GRAY);

        return Map.of(
            RED, grayValue,
            GREEN, grayValue,
            BLUE, grayValue
        );
    }

    @Override
    public Map<ColorChannel, Short> fromRgb(Map<ColorChannel, Short> values, int channelWidth) {
        short red = values.get(RED), green = values.get(GREEN), blue = values.get(BLUE);

        return Map.of(GRAY, (short) Math.round((0.299 * red + 0.587 * green + 0.114 * blue)));
    }

}

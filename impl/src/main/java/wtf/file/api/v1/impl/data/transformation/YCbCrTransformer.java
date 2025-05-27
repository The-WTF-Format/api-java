package wtf.file.api.v1.impl.data.transformation;

import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.util.NumberUtil;

import java.util.Map;

import static wtf.file.api.color.ColorSpaceChannels.*;

public class YCbCrTransformer implements Transformer{

    public static final YCbCrTransformer INSTANCE = new YCbCrTransformer();

    private YCbCrTransformer() {}

    @Override
    public Map<ColorChannel, Short> toRgb(Map<ColorChannel, Short> values, int channelWidth) {
        short y = values.get(LUMA);

        int maxValue = NumberUtil.getMaxValue(channelWidth);

        int cb = values.get(CHROMA_BLUE) - (maxValue / 2);
        int cr = values.get(CHROMA_RED) - (maxValue / 2);

        short r = (short) (y + 1.402 * cr);
        short g = (short) (y - 0.34414 * cb - 0.71414 * cr);
        short b = (short) (y + 1.772 * cb);

        r = (short) Math.min(Math.max(r, 0), maxValue);
        g = (short) Math.min(Math.max(g, 0), maxValue);
        b = (short) Math.min(Math.max(b, 0), maxValue);

        return Map.of(RED, r, GREEN, g, BLUE, b);
    }

    @Override
    public Map<ColorChannel, Short> fromRgb(Map<ColorChannel, Short> values, int channelWidth) {
        short r = values.get(RED), g = values.get(GREEN), b = values.get(BLUE);

        int maxValue = NumberUtil.getMaxValue(channelWidth);

        int y = (int) (0.299 * r + 0.587 * g + 0.114 * b);
        int cb = (int) ((-0.1687 * r - 0.3313 * g + 0.5 * b) + ((double) maxValue / 2));
        int cr = (int) ((0.5 * r - 0.4187 * g - 0.0813 * b) + ((double) maxValue / 2));

        y = Math.min(Math.max(y, 0), maxValue);
        cb = Math.min(Math.max(cb, 0), maxValue);
        cr = Math.min(Math.max(cr, 0), maxValue);

        return Map.of(LUMA, (short) y, CHROMA_BLUE, (short) cb, CHROMA_RED, (short) cr);
    }

}

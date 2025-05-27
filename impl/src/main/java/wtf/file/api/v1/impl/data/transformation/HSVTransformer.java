package wtf.file.api.v1.impl.data.transformation;

import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.util.NumberUtil;

import java.util.Map;

import static wtf.file.api.color.ColorSpaceChannels.*;

public class HSVTransformer implements Transformer{

    public static final HSVTransformer INSTANCE = new HSVTransformer();

    private HSVTransformer() {}

    @Override
    public Map<ColorChannel, Short> toRgb(Map<ColorChannel, Short> values, int channelWidth) {
        int maxValue = NumberUtil.getMaxValue(channelWidth);

        int h = values.get(HUE), s = values.get(SATURATION), v = values.get(VALUE);

        double normalH = h * 360.0 / maxValue;
        double normalS = (double) s / maxValue;
        double normalV = (double) v / maxValue;

        double c = normalV * normalS;
        double x = c * (1 - Math.abs((normalH / 60.0) % 2 - 1));
        double m = normalV - c;

        double rp = 0, gp = 0, bp = 0;
        if (normalH < 60) {
            rp = c;
            gp = x;
        } else if (normalH < 120) {
            rp = x;
            gp = c;
        } else if (normalH < 180) {
            gp = c;
            bp = x;
        } else if (normalH < 240) {
            gp = x;
            bp = c;
        } else if (normalH < 300) {
            rp = x;
            bp = c;
        } else {
            rp = c;
            bp = x;
        }

        return Map.of(
            RED, (short) Math.max(0, Math.min(maxValue, (rp + m) * maxValue)),
            GREEN, (short) Math.max(0, Math.min(maxValue, (gp + m) * maxValue)),
            BLUE, (short) Math.max(0, Math.min(maxValue, (bp + m) * maxValue))
        );
    }

    @Override
    public Map<ColorChannel, Short> fromRgb(Map<ColorChannel, Short> values, int channelWidth) {
        short red = values.get(RED), green = values.get(GREEN), blue = values.get(BLUE);
        int max = Math.max(red, Math.max(green, blue));
        int min = Math.min(red, Math.min(green, blue));

        int diff = max - min;
        int h, s, v;

        if (diff == 0) h = 0;
        else if (max == red) {
            h = Math.round(60f * (green - blue) / diff);
            if (h < 0) h += 360;
        } else if (max == green) h = Math.round(60f * (blue - red) / diff + 120);
        else h = Math.round(60f * (red - green) / diff + 240);

        int maxValue = NumberUtil.getMaxValue(channelWidth);
        h = h * maxValue / 360;

        s = max == 0 ? 0 : (int) Math.round((double) diff / max * maxValue);
        v = max;

        return Map.of(HUE, (short) h, SATURATION, (short) s, VALUE, (short) v);
    }

}

package wtf.file.api.v1.impl.data.transformation;

import wtf.file.api.color.ColorSpaceChannels;
import wtf.file.api.color.channel.ColorChannel;

import java.util.Map;

import static wtf.file.api.color.ColorSpaceChannels.*;

public class HSVTransformer implements Transformer{

    public static final HSVTransformer INSTANCE = new HSVTransformer();

    private HSVTransformer() {}

    @Override
    public Map<ColorChannel, Short> toRgb(Map<ColorChannel, Short> values, int channelWidth) {
        int h = values.get(HUE), s = values.get(SATURATION), v = values.get(VALUE);

        int normalH = h / getMaxValue(channelWidth) * 360;
        int normalS = s / getMaxValue(channelWidth) * 255;

        int c = v * normalS;
        int x = c * (1 - Math.abs(normalH / 60 % 2 - 1));
        int m = v - c;

        int rp = 0, gp = 0, bp = 0;
        if (h < 60) {
            rp = c;
            gp = x;
        } else if (h < 120) {
            rp = x;
            gp = c;
        } else if (h < 180) {
            gp = c;
            bp = x;
        } else if (h < 240) {
            gp = x;
            bp = c;
        } else if (h < 300) {
            rp = x;
            bp = c;
        } else {
            rp = c;
            bp = x;
        }

        return Map.of(RED, (short) (rp + m), GREEN, (short) (gp + m), BLUE, (short) (bp + m));
    }

    @Override
    public Map<ColorChannel, Short> fromRgb(Map<ColorChannel, Short> values, int channelWidth) {
        short red = values.get(RED), green = values.get(GREEN), blue = values.get(BLUE);
        int max = Math.max(red, Math.max(green, blue));
        int min = Math.min(red, Math.min(green, blue));

        int diff = max - min;
        int h, s, v;

        if (diff == 0) h = 0;
        else if (max == red) h = (int) Math.round(((float) (60 * (green - blue)) / diff)) % 360;
        else if (max == green) h = (int) Math.round(((float) (60 * (blue - red)) / diff) + 120);
        else h = (int) Math.round(((float) (60 * (red - green)) / diff) + 240);
        h = h / 360 * getMaxValue(channelWidth);

        s = max == 0 ? 0 : (int) Math.round((float) diff / max * getMaxValue(channelWidth));
        v = max;

        return Map.of(HUE, (short) h, SATURATION, (short) s, VALUE, (short) v);
    }

}

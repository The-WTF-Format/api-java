package wtf.file.api.util;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.ColorSpaceChannels;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.color.channel.FixedColorChannel;
import wtf.file.api.v1.impl.data.transformation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ColorUtil {

    public static Map<ColorChannel, Short> toColorSpace(Map<ColorChannel, Short> values, ColorSpace from, ColorSpace to, int channelWidth) {
        Map<ColorChannel, Short> rgbValues = switch (from) {
            case GRAY_SCALE, GRAY_SCALE_A, DYNAMIC_GRAY_SCALE_A ->
                GrayScaleTransformer.INSTANCE.toRgb(values, channelWidth);
            case RGB, RGBa, DYNAMIC_RGBa -> new HashMap<>(values);
            case CMY, CMYa, DYNAMIC_CMYa -> CMYTransformer.INSTANCE.toRgb(values, channelWidth);
            case HSV, HSVa, DYNAMIC_HSVa -> HSVTransformer.INSTANCE.toRgb(values, channelWidth);
            case YCbCr, YCbCra, DYNAMIC_YCbCra -> YCbCrTransformer.INSTANCE.toRgb(values, channelWidth);
        };

        if (rgbValues instanceof HashMap<ColorChannel, Short>) {
            rgbValues.remove(ColorSpaceChannels.FIXED_ALPHA);
            rgbValues.remove(ColorSpaceChannels.DYNAMIC_ALPHA);
        }

        Map<ColorChannel, Short> newValues = new HashMap<>(switch (to) {
            case GRAY_SCALE, GRAY_SCALE_A, DYNAMIC_GRAY_SCALE_A ->
                GrayScaleTransformer.INSTANCE.fromRgb(rgbValues, channelWidth);
            case RGB, RGBa, DYNAMIC_RGBa -> rgbValues;
            case CMY, CMYa, DYNAMIC_CMYa -> CMYTransformer.INSTANCE.fromRgb(rgbValues, channelWidth);
            case HSV, HSVa, DYNAMIC_HSVa -> HSVTransformer.INSTANCE.fromRgb(rgbValues, channelWidth);
            case YCbCr, YCbCra, DYNAMIC_YCbCra -> YCbCrTransformer.INSTANCE.fromRgb(rgbValues, channelWidth);
        });

        ColorChannel fromAlpha = AlphaTransformer.INSTANCE.getAlphaChannel(from);
        ColorChannel toAlpha = AlphaTransformer.INSTANCE.getAlphaChannel(to);

        if (toAlpha != null && fromAlpha == null) {
            if (toAlpha instanceof FixedColorChannel) {
                newValues.put(toAlpha, (short) 1);
            } else {
                newValues.put(toAlpha, (short) Math.round(Math.pow(2, channelWidth) - 1));
            }
        } else if (toAlpha != null) {
            newValues.put(toAlpha, AlphaTransformer.INSTANCE.transform(values.get(fromAlpha), channelWidth, fromAlpha, toAlpha));
        }

        return newValues;
    }

    public static Map<ColorChannel, Short> toChannelWidth(Map<ColorChannel, Short> values, int from, int to) {
        int oldMaxValue = (int) Math.pow(2, from) - 1;
        int newMaxValue = (int) Math.pow(2, to) - 1;
        double factor = (double) newMaxValue / oldMaxValue;

        Map<ColorChannel, Short> newValues = new HashMap<>();
        values.forEach((channel, value) -> {
            if (channel instanceof FixedColorChannel) {
                newValues.put(channel, value);
                return;
            }

            newValues.put(channel, (short) Math.round(value * factor));
        });
        return newValues;
    }

    public static Map<ColorChannel, Short> getDefaultColors(ColorSpace colorSpace, int channelWidth) {
        return colorSpace.defaultColor()
            .entrySet()
            .stream()
            .map(entry -> Map.entry(
                entry.getKey(),
                entry.getValue().forChannelWidth(channelWidth))
            ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}

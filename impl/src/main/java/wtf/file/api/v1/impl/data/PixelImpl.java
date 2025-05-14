package wtf.file.api.v1.impl.data;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.ColorSpaceChannels;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.data.Pixel;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.v1.impl.data.transformation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PixelImpl implements Pixel {

    private final ColorSpace colorSpace;
    private final Map<ColorChannel, Short> values;
    private final int channelWidth;

    public PixelImpl(ColorSpace colorSpace, Map<ColorChannel, Short> values, int channelWidth) {
        this.colorSpace = colorSpace;
        this.values = Map.copyOf(values);
        this.channelWidth = channelWidth;

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
        Map<ColorChannel, Short> rgbValues = switch(this.colorSpace) {
            case GRAY_SCALE, GRAY_SCALE_A, DYNAMIC_GRAY_SCALE_A -> GrayScaleTransformer.INSTANCE.toRgb(this.values(), this.channelWidth);
            case RGB, RGBa, DYNAMIC_RGBa -> new HashMap<>(this.values());
            case CMY, CMYa, DYNAMIC_CMYa -> CMYTransformer.INSTANCE.toRgb(this.values(), this.channelWidth);
            case HSV, HSVa, DYNAMIC_HSVa -> HSVTransformer.INSTANCE.toRgb(this.values(), this.channelWidth);
            case YCbCr, YCbCra, DYNAMIC_YCbCra -> YCbCrTransformer.INSTANCE.toRgb(this.values(), this.channelWidth);
        };

        if (rgbValues instanceof HashMap<ColorChannel, Short>) {
            rgbValues.remove(ColorSpaceChannels.FIXED_ALPHA);
            rgbValues.remove(ColorSpaceChannels.DYNAMIC_ALPHA);
        }

        Map<ColorChannel, Short> targetValues = new HashMap<>(switch (colorSpace) {
            case GRAY_SCALE, GRAY_SCALE_A, DYNAMIC_GRAY_SCALE_A -> GrayScaleTransformer.INSTANCE.fromRgb(rgbValues, this.channelWidth);
            case RGB, RGBa, DYNAMIC_RGBa -> rgbValues;
            case CMY, CMYa, DYNAMIC_CMYa -> CMYTransformer.INSTANCE.fromRgb(rgbValues, this.channelWidth);
            case HSV, HSVa, DYNAMIC_HSVa -> HSVTransformer.INSTANCE.fromRgb(rgbValues, this.channelWidth);
            case YCbCr, YCbCra, DYNAMIC_YCbCra -> YCbCrTransformer.INSTANCE.fromRgb(rgbValues, this.channelWidth);
        });

        ColorChannel fromAlpha = AlphaTransformer.INSTANCE.getAlphaChannel(this.colorSpace);
        ColorChannel toAlpha = AlphaTransformer.INSTANCE.getAlphaChannel(colorSpace);

        if (fromAlpha != null && toAlpha != null) {
            targetValues.put(toAlpha, AlphaTransformer.INSTANCE.transform(this.values().get(fromAlpha), channelWidth, fromAlpha, toAlpha));
        }

        // TODO: Rest of alpha

        throw new NotYetImplementedException();
    }

}

package wtf.file.api.color;

import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.color.value.DefaultValue;
import wtf.file.api.color.value.ZeroDefaultValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static wtf.file.api.color.ColorSpaceChannels.*;

/**
 * Represents predefined color spaces, each defined by a specific set of {@link ColorChannel}s.
 * <p>
 * This enum supports common color models such as Grayscale, RGB, CMY, HSV, and YCbCr,
 * with optional alpha channels (both fixed and dynamic).
 * </p>
 * <p>
 * Each color space can provide its list of channels and a default color map,
 * which currently defaults all channel values to zero (e.g., black or fully transparent).
 * </p>
 */
public enum ColorSpace {

    GRAY_SCALE(GRAY),
    GRAY_SCALE_A(GRAY, FIXED_ALPHA),
    DYNAMIC_GRAY_SCALE_A(GRAY, DYNAMIC_ALPHA),
    RGB(RED, GREEN, BLUE),
    RGBa(RED, GREEN, BLUE, FIXED_ALPHA),
    DYNAMIC_RGBa(RED, GREEN, BLUE, DYNAMIC_ALPHA),
    CMY(CYAN, MAGENTA, YELLOW),
    CMYa(CYAN, MAGENTA, YELLOW, FIXED_ALPHA),
    DYNAMIC_CMYa(CYAN, MAGENTA, YELLOW, DYNAMIC_ALPHA),
    HSV(HUE, SATURATION, VALUE),
    HSVa(HUE, SATURATION, VALUE, FIXED_ALPHA),
    DYNAMIC_HSVa(HUE, SATURATION, VALUE, DYNAMIC_ALPHA),
    YCbCr(LUMA, CHROMA_BLUE, CHROMA_RED),
    YCbCra(LUMA, CHROMA_BLUE, CHROMA_RED, FIXED_ALPHA),
    DYNAMIC_YCbCra(LUMA, CHROMA_BLUE, CHROMA_RED, DYNAMIC_ALPHA),
    ;

    private final List<ColorChannel> channels;

    /**
     * Constructs a color space with the specified channels.
     *
     * @param channels the color channels that define the color space
     */
    ColorSpace(ColorChannel... channels) {
        this.channels = List.of(channels);
    }

    /**
     * Returns the list of color channels that define this color space.
     *
     * @return a list of {@link ColorChannel}s
     */
    public List<ColorChannel> channels() {
        return channels;
    }

    /**
     * Returns a default color value map for this color space.
     * <p>
     * By default, all channels are assigned a value of {@code 0} via {@link ZeroDefaultValue}.
     * This represents black or fully transparent values, depending on the channel.
     * </p>
     *
     * @return a map from each {@link ColorChannel} to its {@link DefaultValue}
     */
    public Map<ColorChannel, DefaultValue> defaultColor() {
        final Map<ColorChannel, DefaultValue> defaultColor = new HashMap<>();

        // current default is for all channels 0 (black or transparent)
        for (ColorChannel channel : channels) {
            defaultColor.put(channel, ZeroDefaultValue.INSTANCE);
        }

        return defaultColor;
    }
    
}

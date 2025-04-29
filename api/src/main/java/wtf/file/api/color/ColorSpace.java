package wtf.file.api.color;

import wtf.file.api.color.channel.ColorChannel;

import java.util.List;

import static wtf.file.api.color.ColorSpaceChannels.*;

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

    ColorSpace(ColorChannel... channels) {
        this.channels = List.of(channels);
    }

    public List<ColorChannel> channels() {
        return channels;
    }
    
}

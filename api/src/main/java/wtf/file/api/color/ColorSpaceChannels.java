package wtf.file.api.color;

import wtf.file.api.color.channel.DynamicColorChannel;
import wtf.file.api.color.channel.FixedColorChannel;
class ColorSpaceChannels {

    static final FixedColorChannel FIXED_ALPHA = new FixedColorChannel("alpha", (short) 1);
    static final DynamicColorChannel DYNAMIC_ALPHA = new DynamicColorChannel("alpha");

    static final DynamicColorChannel GRAY = new DynamicColorChannel("gray");

    // RGB color space
    static final DynamicColorChannel RED = new DynamicColorChannel("red");
    static final DynamicColorChannel GREEN = new DynamicColorChannel("green");
    static final DynamicColorChannel BLUE = new DynamicColorChannel("blue");

    // CMY color space
    static final DynamicColorChannel CYAN = new DynamicColorChannel("cyan");
    static final DynamicColorChannel MAGENTA = new DynamicColorChannel("magenta");
    static final DynamicColorChannel YELLOW = new DynamicColorChannel("yellow");

    // HSV color space
    static final DynamicColorChannel HUE = new DynamicColorChannel("hue");
    static final DynamicColorChannel SATURATION = new DynamicColorChannel("saturation");
    static final DynamicColorChannel VALUE = new DynamicColorChannel("value");

    // YCbCr color space
    static final DynamicColorChannel LUMA = new DynamicColorChannel("luma");
    static final DynamicColorChannel CHROMA_BLUE = new DynamicColorChannel("chroma blue");
    static final DynamicColorChannel CHROMA_RED = new DynamicColorChannel("chroma red");

}

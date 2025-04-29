package wtf.file.api.color;

import wtf.file.api.color.channel.DynamicColorChannel;
import wtf.file.api.color.channel.FixedColorChannel;

class ColorSpaceChannels {

    static final FixedColorChannel FIXED_ALPHA = new FixedColorChannel((short) 1, "alpha");
    static final DynamicColorChannel DYNAMIC_ALPHA = new DynamicColorChannel("alpha");

    static final DynamicColorChannel GRAY = new DynamicColorChannel("gray");

    static final DynamicColorChannel RED = new DynamicColorChannel("red");
    static final DynamicColorChannel GREEN = new DynamicColorChannel("green");
    static final DynamicColorChannel BLUE = new DynamicColorChannel("blue");

    static final DynamicColorChannel CYAN = new DynamicColorChannel("cyan");
    static final DynamicColorChannel MAGENTA = new DynamicColorChannel("magenta");
    static final DynamicColorChannel YELLOW = new DynamicColorChannel("yellow");

    static final DynamicColorChannel HUE = new DynamicColorChannel("hue");
    static final DynamicColorChannel SATURATION = new DynamicColorChannel("saturation");
    static final DynamicColorChannel VALUE = new DynamicColorChannel("value");

    static final DynamicColorChannel LUMA = new DynamicColorChannel("luma");
    static final DynamicColorChannel CHROMA_BLUE = new DynamicColorChannel("chroma blue");
    static final DynamicColorChannel CHROMA_RED = new DynamicColorChannel("chroma red");

}

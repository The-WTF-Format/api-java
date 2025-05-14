package wtf.file.api.color;

import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.color.channel.DynamicColorChannel;
import wtf.file.api.color.channel.FixedColorChannel;

/**
 * Provides a collection of predefined color channels used across various color spaces.
 * <p>
 *     Each color channel represents a specific component of a color within a given color space,
 *     such as the red, green, or blue components in the RGB color space, or the luma and chroma components in the YCbCr color space.
 *     These channels are represented as instances of {@link FixedColorChannel} or {@link DynamicColorChannel},
 *     depending on whether their definition is static or dynamic.
 * </p>
 *
 * <p>This class includes the following color channels:</p>
 *
 * <p>
 *     1. Alpha Channels:
 *     <ul>
 *         <li>{@code FIXED_ALPHA}: A fixed channel representing transparency, defined with a constant bit depth.</li>
 *         <li>{@code DYNAMIC_ALPHA}: A dynamic channel representing transparency, whose properties may depend on image headers.</li>
 *     </ul>
 * </p>
 *
 * <p>
 *     2. Grayscale Channels:
 *     <ul>
 *         <li>{@code GRAY}: A dynamic channel representing grayscale intensity.</li>
 *     </ul>
 * </p>
 *
 * <p>
 * 3. RGB (Red, Green, Blue) Channels:
 *     <ul>
 *         <li>{@code RED}: A dynamic channel representing the red component.</li>
 *         <li>{@code GREEN}: A dynamic channel representing the green component.</li>
 *         <li>{@code BLUE}: A dynamic channel representing the blue component.</li>
 *     </ul>
 * </p>
 *
 * <p>
 * 4. CMY (Cyan, Magenta, Yellow) Channels:
 *     <ul>
 *         <li>{@code CYAN}: A dynamic channel representing the cyan component.</li>
 *         <li>{@code MAGENTA}: A dynamic channel representing the magenta component.</li>
 *         <li>{@code YELLOW}: A dynamic channel representing the yellow component.</li>
 *     </ul>
 * </p>
 *
 * <p>
 * 5. HSV (Hue, Saturation, Value) Channels:
 *     <ul>
 *         <li>{@code HUE}: A dynamic channel representing the hue component.</li>
 *         <li>{@code SATURATION}: A dynamic channel representing the saturation component.</li>
 *         <li>{@code VALUE}: A dynamic channel representing the value (brightness) component.</li>
 *     </ul>
 * </p>
 *
 * <p>
 * 6. YCbCr (Luma, Chroma) Channels:
 *     <ul>
 *         <li>{@code LUMA}: A dynamic channel representing the luminance.</li>
 *         <li>{@code CHROMA_BLUE}: A dynamic channel representing the blue-difference chrominance.</li>
 *         <li>{@code CHROMA_RED}: A dynamic channel representing the red-difference chrominance.</li>
 *     </ul>
 * </p>
 */
public class ColorSpaceChannels {

    public static final FixedColorChannel FIXED_ALPHA = new FixedColorChannel("alpha", (short) 1);
    public static final DynamicColorChannel DYNAMIC_ALPHA = new DynamicColorChannel("alpha");

    public static final DynamicColorChannel GRAY = new DynamicColorChannel("gray");

    // RGB color space
    public static final DynamicColorChannel RED = new DynamicColorChannel("red");
    public static final DynamicColorChannel GREEN = new DynamicColorChannel("green");
    public static final DynamicColorChannel BLUE = new DynamicColorChannel("blue");

    // CMY color space
    public static final DynamicColorChannel CYAN = new DynamicColorChannel("cyan");
    public static final DynamicColorChannel MAGENTA = new DynamicColorChannel("magenta");
    public static final DynamicColorChannel YELLOW = new DynamicColorChannel("yellow");

    // HSV color space
    public static final DynamicColorChannel HUE = new DynamicColorChannel("hue");
    public static final DynamicColorChannel SATURATION = new DynamicColorChannel("saturation");
    public static final DynamicColorChannel VALUE = new DynamicColorChannel("value");

    // YCbCr color space
    public static final DynamicColorChannel LUMA = new DynamicColorChannel("luma");
    public static final DynamicColorChannel CHROMA_BLUE = new DynamicColorChannel("chroma blue");
    public static final DynamicColorChannel CHROMA_RED = new DynamicColorChannel("chroma red");

}

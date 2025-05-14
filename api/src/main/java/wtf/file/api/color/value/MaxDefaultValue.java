package wtf.file.api.color.value;

/**
 * A {@link DefaultValue} implementation that returns the maximum possible value
 * for a given color channel width.
 * <p>
 * The maximum value is computed as {@code (2^channelWidth) - 1}, which is the
 * highest number representable by the specified number of bits.
 * </p>
 * <p>
 * This class uses the singleton pattern and provides a single shared instance
 * via {@link #INSTANCE}.
 * </p>
 */
public class MaxDefaultValue implements DefaultValue {

    /**
     * The singleton instance of {@code MaxDefaultValue}.
     */
    public static final MaxDefaultValue INSTANCE = new MaxDefaultValue();

    private MaxDefaultValue() {
        // Prevent instantiation
    }


    /**
     * Returns the maximum value representable by the specified channel width.
     * <p>
     * For example, if {@code channelWidth} is 8, the result is {@code 255}.
     * </p>
     *
     * @param channelWidth the bit width of the color channel
     * @return the maximum value representable with the given number of bits
     */
    @Override
    public short forChannelWidth(int channelWidth) {
        return (short) ((1 << channelWidth) - 1);
    }

}

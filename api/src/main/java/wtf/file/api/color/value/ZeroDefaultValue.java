package wtf.file.api.color.value;

/**
 * A {@link DefaultValue} implementation that always returns zero,
 * regardless of the channel width.
 * <p>
 * This can be used when a default value of zero is appropriate for a color channel,
 * such as representing full transparency or no intensity.
 * </p>
 * <p>
 * This class is implemented as a singleton and provides a shared instance
 * via {@link #INSTANCE}.
 * </p>
 */
public class ZeroDefaultValue implements DefaultValue {

    /**
     * The singleton instance of {@code ZeroDefaultValue}.
     */
    public static final ZeroDefaultValue INSTANCE = new ZeroDefaultValue();

    private ZeroDefaultValue() {
        // Prevent instantiation
    }

    /**
     * Returns zero as the default value, regardless of the channel width.
     *
     * @param channelWidth the bit width of the color channel
     * @return {@code 0L} as the default value
     */
    @Override
    public Long forChannelWidth(short channelWidth) {
        return 0L;
    }

}

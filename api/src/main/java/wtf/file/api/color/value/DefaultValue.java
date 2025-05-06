package wtf.file.api.color.value;

/**
 * Represents a default value provider for a color channel based on its bit width.
 * <p>
 * Implementations of this interface define how to compute a default value
 * given the width (in bits) of a color channel.
 * </p>
 */
public interface DefaultValue {

    /**
     * Returns the default value corresponding to the given channel width (in bits).
     *
     * @param channelWidth the bit width of the color channel
     * @return the default value as a {@link Long}
     */
    Long forChannelWidth(short channelWidth);

}

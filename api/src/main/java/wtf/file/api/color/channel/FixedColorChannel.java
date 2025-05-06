package wtf.file.api.color.channel;

/**
 * A {@link ColorChannel} implementation representing a fixed color channel.
 * A fixed color channel has a name and a fixed bit-depth value, which cannot change during runtime.
 * This type of channel corresponds to {@link ChannelType#FIXED}.
 */
public record FixedColorChannel(String name, short bits) implements ColorChannel {

    /**
     * Returns the type of the color channel, which is {@link ChannelType#FIXED} for this record.
     *
     * @return the type of the color channel, which is always {@code ChannelType.FIXED}
     */
    @Override
    public ChannelType type() {
        return ChannelType.FIXED;
    }

}

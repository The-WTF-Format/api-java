package wtf.file.api.color.channel;

/**
 * A {@link ColorChannel} implementation representing a dynamic color channel.
 * A dynamic color channel has a name and is characterized by the dependency on the header information from the image.
 * This type of channel corresponds to {@link ChannelType#DYNAMIC}.
 */
public record DynamicColorChannel(String name) implements ColorChannel {

    /**
     * Returns the type of the color channel, which is {@link ChannelType#DYNAMIC} for this record.
     *
     * @return the type of the color channel, which is always {@code ChannelType.DYNAMIC}
     */
    @Override
    public ChannelType type() {
        return ChannelType.DYNAMIC;
    }

}

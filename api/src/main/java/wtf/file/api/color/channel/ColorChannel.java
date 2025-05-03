package wtf.file.api.color.channel;

/**
 * Represents a color channel with a name and type.
 * A color channel is typically used to define specific components of a color, such as red, green, blue, or alpha channels.
 */
public interface ColorChannel {

    /**
     * Returns the name of the color channel.
     * The name could be something like "Red", "Green", "Blue", or "Alpha", depending on the color model.
     *
     * @return the name of the color channel
     */
    String name();

    /**
     * Returns the type of the color channel, which indicates whether the channel is dynamic or fixed.
     *
     * @return the type of the color channel
     */
    ChannelType type();

}

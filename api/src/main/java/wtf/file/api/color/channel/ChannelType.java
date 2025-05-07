package wtf.file.api.color.channel;

/**
 * Enum representing the different types of channels in the color system.
 * <p>
 * {@code DYNAMIC} channels depend on the header information from the image, while {@code FIXED} channels have a constant value.
 * </p>
 */
public enum ChannelType {

    /**
     * Represents a channel that depends on the header information from the image.
     */
    DYNAMIC,

    /**
     * Represents a channel with a fixed value that does not change during runtime.
     */
    FIXED,

}
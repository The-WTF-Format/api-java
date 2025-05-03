package wtf.file.api.color.channel;

/**
 * Enum representing the different types of channels in the color system.
 * <p>
 * {@code DYNAMIC} channels can change during runtime, while {@code FIXED} channels have a constant value.
 * </p>
 */
public enum ChannelType {

    /**
     * Represents a channel that can change dynamically during runtime.
     */
    DYNAMIC,

    /**
     * Represents a channel with a fixed value that does not change during runtime.
     */
    FIXED,

}
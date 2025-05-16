package wtf.file.api.builder;

import org.jetbrains.annotations.Contract;
import wtf.file.api.WtfImage;
import wtf.file.api.WtfLoader;
import wtf.file.api.color.ColorSpace;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.exception.NumberOutOfBoundsException;
import wtf.file.api.exception.ValueNotSetException;
import wtf.file.api.v1.version.Version;

/**
 * The WtfImageBuilder class provides a fluent API for constructing instances of WtfImage.
 * It allows setting various attributes such as dimensions, color space, and animation information
 * required to define a WtfImage.
 *
 * <p>
 * The builder ensures that all required fields are provided before the image can be built.
 * </p>
 *
 * @see WtfImageBuilder#build
 * @see WtfLoader#by
 * @see WtfImage
 */
public class WtfImageBuilder {

    /**
     * Sets the version for the WtfImage being built.
     * Per default the version is set to {@code VERSION_1}
     *
     * @param version the version of the WtfImage to be set
     * @return the current instance of WtfImageBuilder for method chaining
     * @see Version#VERSION_1
     */
    @Contract(value = "_ -> this", mutates = "this")
    public WtfImageBuilder version(Version version) {
        throw new NotYetImplementedException("Setting version");
    }


    /**
     * Sets the width of the WtfImage being built.
     * This value must be set, otherwise build will throw a {@code ValueNotSetException}.
     *
     * @param width the width of the image in pixels
     * @return the current instance of WtfImageBuilder for method chaining
     * @throws NumberOutOfBoundsException if the provided height value is out of the acceptable range (1 - 65 535)
     */
    @Contract(value = "_ -> this", mutates = "this")
    public WtfImageBuilder width(int width) throws NumberOutOfBoundsException  {
        throw new NotYetImplementedException("Setting width");
    }

    /**
     * Sets the height of the WtfImage being built.
     * This value must be set, otherwise build will throw a {@code ValueNotSetException}.
     *
     * @param height the height of the image in pixels
     * @return the current instance of WtfImageBuilder for method chaining
     * @throws NumberOutOfBoundsException if the provided height value is out of the acceptable range (1 - 65 535)
     */
    @Contract(value = "_ -> this", mutates = "this")
    public WtfImageBuilder height(int height) throws NumberOutOfBoundsException {
        throw new NotYetImplementedException("Setting height");
    }

    /**
     * Sets the color space of the WtfImage being built.
     * The color space determines how color information is represented and stored.
     * This value must be set, otherwise build will throw a {@code ValueNotSetException}.
     *
     * @param colorSpace the color space to be used for the image, such as RGB, CMY, HSV, etc.
     * @return the current instance of WtfImageBuilder for method chaining
     * @see ColorSpace
     */
    @Contract(value = "_ -> this", mutates = "this")
    public WtfImageBuilder colorSpace(ColorSpace colorSpace) {
        throw new NotYetImplementedException("Setting color space");
    }

    /**
     * Sets the channel width for the WtfImage being built.
     * This value represents the bit depth of each channel in the color space.
     * Per default the channel width is set to {@code 8}
     *
     * @param channelWidth the width of the channel in bits
     * @return the current instance of WtfImageBuilder for method chaining
     * @throws NumberOutOfBoundsException if the provided channelWidth value is out of the acceptable range (1 - 255)
     */
    @Contract(value = "_ -> this", mutates = "this")
    public WtfImageBuilder channelWidth(short channelWidth) throws NumberOutOfBoundsException {
        throw new NotYetImplementedException("Setting channel width");
    }

    /**
     * Sets the number of frames for the WtfImage being built.
     * This value specifies the total number of frames in the animation.
     * Per default the channel width is set to {@code 1}
     *
     * @param frames the number of frames
     * @return the current instance of WtfImageBuilder for method chaining
     * @throws NumberOutOfBoundsException if the provided frames value is out of the acceptable range (1 - 255)
     */
    @Contract(value = "_ -> this", mutates = "this")
    public WtfImageBuilder frames(int frames) throws NumberOutOfBoundsException {
        throw new NotYetImplementedException("Setting frames");
    }

    /**
     * Sets the frames per second (fps) for the WtfImage being built.
     * This value defines the playback speed of the animation.
     *
     * @param fps the frames per second value
     * @return the current instance of WtfImageBuilder for method chaining
     * @throws NumberOutOfBoundsException if the provided fps value is out of the acceptable range (1 - 127)
     */
    @Contract(value = "_ -> this", mutates = "this")
    public WtfImageBuilder framesPerSecond(int fps) throws NumberOutOfBoundsException {
        throw new NotYetImplementedException("Setting frames per second");
    }

    /**
     * Sets the seconds per frame for the WtfImage being built.
     * This value determines the duration of each frame in the animation sequence.
     *
     * @param spf the seconds per frame, representing the duration of each frame in seconds
     * @return the current instance of WtfImageBuilder for method chaining
     * @throws NumberOutOfBoundsException if the provided seconds per frame value is out of the acceptable range (1 - 127)
     */
    @Contract(value = "_ -> this", mutates = "this")
    public WtfImageBuilder secondsPerFrame(int spf) throws NumberOutOfBoundsException {
        throw new NotYetImplementedException("Setting seconds per frame");
    }

    /**
     * Builds and returns an instance of {@code WtfImage} based on the properties
     * configured in the {@code WtfImageBuilder}.
     * All mandatory properties (e.g., width, height, and color space) must be set
     * before calling this method, otherwise, a {@code ValueNotSetException} will
     * be thrown.
     * All image frames will be filled with the default color of the color space.
     *
     * @return the constructed {@code WtfImage} instance
     * @throws ValueNotSetException if any required property in the builder has not been set
     * @see ColorSpace#defaultColor
     */
    @Contract(value = "-> new", pure = true)
    public WtfImage build() throws ValueNotSetException {
        throw new NotYetImplementedException("Building WtfImage");
    }

}

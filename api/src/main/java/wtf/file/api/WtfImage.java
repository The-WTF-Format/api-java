package wtf.file.api;

import wtf.file.api.animation.AnimationInformation;
import wtf.file.api.color.ColorSpace;
import wtf.file.api.data.Frame;
import wtf.file.api.editable.EditableWtfImage;
import wtf.file.api.metadata.MetadataContainer;
import wtf.file.api.version.Version;

/**
 * The WtfImage interface represents an image with defined properties and functions,
 * supporting various color spaces, metadata manipulation, and animation information.
 * <p>
 * It extends the Frame interface, adding specific methods for retrieving
 * image configuration and metadata. Instances of WtfImage are immutable;
 * editing requires transitioning to an EditableWtfImage.
 * <p>
 * The methods from {@code Frame} take actions on the first frame of the image.
 *
 * @see Frame
 * @see EditableWtfImage
 */
public interface WtfImage extends Frame {

    /**
     * Retrieves the version of the WtfImage.
     *
     * @return the {@link Version} of the WtfImage
     */
    Version version();

    /**
     * Retrieves the width of the image.
     *
     * @return the width of the image in pixels
     */
    int width();

    /**
     * Retrieves the height of the image.
     *
     * @return the height of the image in pixels
     */
    int height();

    /**
     * Retrieves the color space configuration used by the image.
     * The color space determines how pixel information is represented
     * (e.g., RGB, CMY, HSV, etc.) and may include details like the number
     * of channels and whether an alpha channel is fixed or dynamic.
     *
     * @return the {@link ColorSpace} defining the color space of the image
     */
    ColorSpace colorSpace();

    /**
     * Retrieves the channel width of the image.
     * The channel width represents the bit depth per channel, specifying
     * the amount of information stored per color channel.
     *
     * @return the channel width in bits
     * @see ColorSpace
     */
    short channelWidth();

    /**
     * Retrieves animation information associated with the image.
     * The animation information includes details such as the number of frames,
     * frame rate (frames per second), or seconds per frame,
     * and whether the animation metadata is FPS-coded or SPF-coded.
     *
     * @return the {@link AnimationInformation} containing details about the animation of the image
     */
    AnimationInformation animationInformation();

    /**
     * Retrieves the metadata container associated with the image.
     * The metadata container provides functionalities to access and inspect
     * metadata elements associated with the image.
     *
     * @return the {@link MetadataContainer} containing the metadata of the image
     */
    MetadataContainer metadataContainer();

    /**
     * Transitions the current immutable {@code WtfImage} instance into a modifiable {@code EditableWtfImage}.
     * This method allows for editing properties, metadata, and other attributes of the image.
     *
     * @return a new instance of {@code EditableWtfImage} representing an editable version of the current image
     */
    EditableWtfImage edit();

}

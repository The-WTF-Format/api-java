package wtf.file.api.editable;

import wtf.file.api.WtfImage;
import wtf.file.api.color.ColorSpace;
import wtf.file.api.editable.animation.EditableAnimationInformation;
import wtf.file.api.editable.compression.DataCompressionType;
import wtf.file.api.editable.data.EditableFrame;
import wtf.file.api.editable.metadata.EditableMetadataContainer;
import wtf.file.api.exception.NumberOutOfBoundsException;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Represents an editable version of a WtfImage.
 * This interface combines the functionalities of {@link WtfImage} and {@link EditableFrame},
 * enabling modifications to the width, height, color space, channel width, animation information, and metadata of the image.
 * Changes can be saved to a file with various compression options.
 * <p>
 * Methods in this interface allow for direct changes to the properties of the image.
 * The modifications include altering dimensions, color channels, metadata,
 * and animation properties, along with saving the image in different formats and compression levels.
 *
 * @see WtfImage
 * @see EditableFrame
 */
public interface EditableWtfImage extends WtfImage, EditableFrame {

    /**
     * Sets the width of the editable image.
     * The specified width must be within valid bounds for the image dimensions (0 - 65 535).
     * <p>
     * If the image gets bigger by this, every new pixel will have the default color of this image's color space.
     * If the image gets smaller, data is truncated from the right of the image.
     *
     * @param width the new width of the image in pixels
     * @throws NumberOutOfBoundsException if the specified width is outside the allowed range
     * @see ColorSpace#defaultColor()
     */
    void setWidth(int width) throws NumberOutOfBoundsException;

    /**
     * Sets the height of the editable image.
     * The specified height must be within valid bounds for the image dimensions (0 - 65 535).
     * <p>
     * If the image gets larger due to this operation, new rows of pixels will have the default color of this image's color space.
     * If the image gets smaller, data is truncated from the bottom of the image.
     *
     * @param height the new height of the image in pixels
     * @throws NumberOutOfBoundsException if the specified height is outside the allowed range
     * @see ColorSpace#defaultColor()
     */
    void setHeight(int height) throws NumberOutOfBoundsException;

    /**
     * Sets the color space of the editable image.
     * The color space determines how pixel information is represented, such as the number
     * and type of color channels (e.g., RGB, CMY, HSV) and whether it includes
     * alpha transparency (fixed or dynamic).
     * <p>
     * By default, each pixel tries to convert itself to another color space.
     * However, information may be lost.
     * If the conversion fails, the color spaces' default color will be used.
     *
     * @param colorSpace the {@link ColorSpace} to be applied to the image
     * @throws IllegalArgumentException if the provided color space is null or unsupported
     * @see ColorSpace#defaultColor()
     */
    void setColorSpace(ColorSpace colorSpace);

    /**
     * Sets the channel width of the editable image.
     * The channel width specifies the bit depth per channel, determining the amount
     * of information stored per individual color channel (e.g., red, green, blue) or alpha channel.
     * The value must be within valid bounds for the channel width (1 - 8).
     * <p>
     * The api tries to represent the color as best as possible in the new channel width.
     *
     * @param channelWidth the bit depth of each channel, in the range from 1 to 8
     * @throws NumberOutOfBoundsException if the specified channel width is outside the allowed range
     */
    void setChannelWidth(int channelWidth) throws NumberOutOfBoundsException;

    @Override
    EditableAnimationInformation animationInformation();

    @Override
    EditableMetadataContainer metadataContainer();

    /**
     * Saves the current editable image to the specified file path using the provided compression settings.
     *
     * @param path the file path where the image will be saved; must not be null
     * @param dataCompressionType the type of compression applied to the image data; must not be null
     * @throws IOException if an I/O error occurs during the save operation
     */
    void save(Path path, DataCompressionType dataCompressionType) throws IOException;

    /**
     * Saves the current editable image to the specified file path using default compression settings.
     * The default settings use mapped compression for the data.
     *
     * @param path the file path where the image will be saved; must not be null
     * @throws IOException if an I/O error occurs during the save operation
     */
    default void save(Path path) throws IOException {
        save(path, DataCompressionType.MAPPED_COMPRESSION);
    }

}

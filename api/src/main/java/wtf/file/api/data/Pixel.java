package wtf.file.api.data;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.exception.NumberOutOfBoundsException;

import java.util.Map;

/**
 * Represents a single pixel in an image, defined by a {@link ColorSpace} and a mapping of
 * {@link ColorChannel} values.
 * <p>
 * A pixel provides access to its channel values, can be queried for individual channels,
 * and can be converted to another {@link ColorSpace} representation.
 * </p>
 */
public interface Pixel {

    /**
     * Gets the {@link ColorSpace} that defines the color composition of this pixel.
     *
     * @return the {@link ColorSpace} of the pixel.
     */
    ColorSpace colorSpace();

    /**
     * Returns the mapping of {@link ColorChannel} to their corresponding numeric values
     * for this pixel.
     *
     * @return a map of {@link ColorChannel} to {@code short} values.
     */
    Map<ColorChannel, Short> values();

    /**
     * Retrieves the numeric value associated with a specific color channel.
     *
     * @param channel the {@link ColorChannel} to query.
     * @return the value of the specified channel.
     * @throws IllegalArgumentException if the channel is not part of this pixel's {@link ColorSpace}.
     */
    short valueOf(ColorChannel channel);

    /**
     * Converts this pixel to the given {@link ColorSpace}, potentially performing
     * a transformation or mapping between channel sets.
     *
     * @param colorSpace the target {@link ColorSpace}.
     * @return a new {@code Pixel} in the specified color space, or null if it could not be converted.
     */
    Pixel withColorSpace(ColorSpace colorSpace);

    /**
     * Creates a new {@code Pixel} instance with the specified channel width.
     *
     * @param channelWidth the width of each channel, typically in bits (e.g., 8 for 0-255 values).
     *                     Must be a positive, non-zero integer smaller than or equal to 16
     * @return a new {@code Pixel} instance with the given channel width set.
     * @throws NumberOutOfBoundsException if the channel width is invalid.
     */
    Pixel withWidth(int channelWidth) throws NumberOutOfBoundsException;

}

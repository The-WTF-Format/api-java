package wtf.file.api.editable.data;

import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.data.Pixel;
import wtf.file.api.exception.NumberOutOfBoundsException;

import java.util.Map;

/**
 * An interface extending {@link Pixel} to provide editable functionality for individual pixels.
 * <p>
 * This interface allows modification of pixel values for different color channels and ensures that
 * values set for pixels are within the allowed range.
 * </p>
 */
public interface EditablePixel extends Pixel {

    /**
     * Sets the values for all color channels in this pixel.
     * <p>
     * This method allows you to update the pixel with a map of channel values, where the key is
     * the {@link ColorChannel} and the value is the corresponding pixel value.
     * </p>
     *
     * @param values a map of color channels with their corresponding pixel values.
     * @throws NumberOutOfBoundsException if any of the values exceed the allowed range for that channel.
     * @throws IllegalArgumentException if the map does not contain the exact channels required by the color space
     */
    void setValues(Map<ColorChannel, Short> values) throws NumberOutOfBoundsException;

    /**
     * Sets the value for a specific color channel in this pixel.
     *
     * @param channel the {@link ColorChannel} to update.
     * @param value   the new value to set for the specified channel.
     * @throws NumberOutOfBoundsException if the value is out of the allowed range for the specified channel.
     * @throws IllegalArgumentException if the channel is not part of the color space
     */
    void setValue(ColorChannel channel, short value) throws NumberOutOfBoundsException;

}

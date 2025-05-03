package wtf.file.api.editable.data;

import wtf.file.api.color.ColorSpace;
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
     * Returns the color space associated with this pixel.
     *
     * @return the {@link ColorSpace} in which this pixel is represented.
     */
    ColorSpace colorSpace();

    /**
     * Sets the values for all color channels in this pixel.
     * <p>
     * This method allows you to update the pixel with a map of channel values, where the key is
     * the {@link ColorChannel} and the value is the corresponding pixel value.
     * </p>
     *
     * @param values a map of color channels to their corresponding pixel values.
     * @throws NumberOutOfBoundsException if any of the values exceed the allowed range for that channel.
     */
    void setValues(Map<ColorChannel, Long> values) throws NumberOutOfBoundsException;

    /**
     * Sets the value for a specific color channel in this pixel.
     *
     * @param channel the {@link ColorChannel} to update.
     * @param value   the new value to set for the specified channel.
     * @throws NumberOutOfBoundsException if the value is out of the allowed range for the specified channel.
     */
    void setValue(ColorChannel channel, long value) throws NumberOutOfBoundsException;

}

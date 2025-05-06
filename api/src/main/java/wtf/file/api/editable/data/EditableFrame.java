package wtf.file.api.editable.data;

import wtf.file.api.data.Frame;
import wtf.file.api.exception.NumberOutOfBoundsException;

import java.awt.*;

/**
 * An interface extending {@link Frame} to provide editable functionality for individual image frames.
 * <p>
 * This interface allows modification of pixel data and enables setting the frame content from a {@link java.awt.Image}.
 * </p>
 */
public interface EditableFrame extends Frame {

    /**
     * Retrieves an editable pixel at the specified (x, y) coordinate.
     *
     * @param x the horizontal (column) position of the pixel, starting from 0.
     * @param y the vertical (row) position of the pixel, starting from 0.
     * @return the {@link EditablePixel} at the given coordinate.
     * @throws NumberOutOfBoundsException if the coordinates are outside the frame dimensions.
     */
    @Override
    EditablePixel at(int x, int y) throws NumberOutOfBoundsException;

    /**
     * Sets the content of this frame using a standard Java {@link Image}.
     * <p>
     * This method allows the frame to be populated with an {@link Image}, which could be used
     * to import image data or manipulate the frame based on external image sources.
     * </p>
     *
     * @param image the {@link Image} to set as the content of this frame.
     */
    void byJavaImage(Image image);

}

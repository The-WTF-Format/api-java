package wtf.file.api.data;

import wtf.file.api.exception.NumberOutOfBoundsException;

import java.awt.*;

/**
 * Represents a single image frame consisting of a 2D array of {@link Pixel} values.
 * <p>
 * A frame can be accessed programmatically by individual pixel coordinates or retrieved as
 * a full Java {@link Image} object for rendering or export.
 * </p>
 */
public interface Frame {

    /**
     * Retrieves the 2D array of pixels that make up the frame.
     *
     * @return a two-dimensional array of {@link Pixel} objects.
     */
    Pixel[][] pixels();

    /**
     * Returns the {@link Pixel} at the specified (x, y) coordinate.
     *
     * @param x the horizontal (column) position, starting from 0.
     * @param y the vertical (row) position, starting from 0.
     * @return the {@link Pixel} at the given coordinate.
     * @throws NumberOutOfBoundsException if the coordinates are outside the frame dimensions.
     */
    Pixel at(int x, int y) throws NumberOutOfBoundsException;

    /**
     * Converts the frame into a standard Java {@link Image} object.
     *
     * @return the {@link Image} representation of this frame.
     */
    Image asJavaImage();

}

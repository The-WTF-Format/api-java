package wtf.file.api.exception;

/**
 * Thrown to indicate that a required value was not set when creating an image.
 * <p>
 * This exception is typically used to enforce that all necessary values are
 * provided during the image creation process, and the absence of a value is
 * considered an error.
 * </p>
 */
public class ValueNotSetException extends RuntimeException {

    /**
     * Constructs a new {@code ValueNotSetException} with a message describing
     * the value that was not set.
     *
     * @param value the name or description of the value that was not set.
     */
    public ValueNotSetException(String value) {
        super("When creating an image with the image builder, all values must be set. The value '" + value + "' was not set.");
    }

}

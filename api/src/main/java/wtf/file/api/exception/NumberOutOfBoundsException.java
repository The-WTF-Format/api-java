package wtf.file.api.exception;

/**
 * Thrown to indicate that a numeric value is outside the expected bounds.
 * <p>
 * This exception provides access to the minimum, maximum, and actual values
 * involved in the failed validation.
 * </p>
 */
public class NumberOutOfBoundsException extends RuntimeException {

    /**
     * Constructs a new {@code NumberOutOfBoundsException} with the specified bounds and actual value.
     *
     * @param min   the minimum allowed value (inclusive).
     * @param max   the maximum allowed value (inclusive).
     * @param value the actual value that was out of bounds.
     * @param name  the name of the wrong value
     */
    public NumberOutOfBoundsException(long min, long max, long value, String name) {
        super("Value " + value + " for " + name + " is out of bounds: [" + min + ", " + max + "]");
    }

}

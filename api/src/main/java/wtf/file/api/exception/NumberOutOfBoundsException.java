package wtf.file.api.exception;

/**
 * Thrown to indicate that a numeric value is outside of the expected bounds.
 * <p>
 * This exception provides access to the minimum, maximum, and actual values
 * involved in the failed validation.
 * </p>
 */
public class NumberOutOfBoundsException extends RuntimeException {

    private final long min;
    private final long max;
    private final long value;

    /**
     * Constructs a new {@code NumberOutOfBoundsException} with the specified bounds and actual value.
     *
     * @param min   the minimum allowed value (inclusive).
     * @param max   the maximum allowed value (inclusive).
     * @param value the actual value that was out of bounds.
     */
    public NumberOutOfBoundsException(long min, long max, long value) {
        super("Value " + value + " is out of bounds: [" + min + ", " + max + "]");
        this.min = min;
        this.max = max;
        this.value = value;
    }

}

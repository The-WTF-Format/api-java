package wtf.file.api.exception;

public class NumberOutOfBoundsException extends RuntimeException {

    private final long min;
    private final long max;
    private final long value;

    public NumberOutOfBoundsException(long min, long max, long value) {
        super("Value " + value + " is out of bounds: [" + min + ", " + max + "]");
        this.min = min;
        this.max = max;
        this.value = value;
    }

}

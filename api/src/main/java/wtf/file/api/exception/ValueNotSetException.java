package wtf.file.api.exception;

public class ValueNotSetException extends RuntimeException {

    public ValueNotSetException(String value) {
        super("When creating an image with the image builder, all values must be set. The value '" + value + "' was not set.");
    }

}

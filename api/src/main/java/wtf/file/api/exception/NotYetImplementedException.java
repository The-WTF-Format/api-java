package wtf.file.api.exception;

public class NotYetImplementedException extends RuntimeException {

    public NotYetImplementedException() {
        super("This feature is not yet implemented.");
    }

    public NotYetImplementedException(String feature) {
        super("This feature is not yet implemented: " + feature);
    }

}

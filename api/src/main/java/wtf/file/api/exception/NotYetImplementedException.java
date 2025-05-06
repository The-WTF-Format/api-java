package wtf.file.api.exception;

/**
 * Exception thrown to indicate that a requested feature or functionality
 * has not yet been implemented.
 * <p>
 * This is typically used as a placeholder during development.
 * </p>
 */
public class NotYetImplementedException extends RuntimeException {

    /**
     * Constructs a new exception with a default message indicating that
     * the feature is not yet implemented.
     */
    public NotYetImplementedException() {
        super("This feature is not yet implemented.");
    }

    /**
     * Constructs a new exception with a message indicating that a specific
     * feature is not yet implemented.
     *
     * @param feature the name or description of the unimplemented feature.
     */
    public NotYetImplementedException(String feature) {
        super("This feature is not yet implemented: " + feature);
    }

}

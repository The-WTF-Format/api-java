package wtf.file.api.exception;

/**
 * Thrown to indicate that an image contains broken or corrupt data.
 * <p>
 * This exception is used specifically to signal issues encountered while processing
 * image files that are invalid, damaged, or otherwise unreadable due to corruption.
 * </p>
 */
public class WtfException extends Exception {

    /**
     * Constructs a new {@code WtfException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     */
    public WtfException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code WtfException} with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     * @param cause   the cause of the exception (which is saved for later retrieval by the {@link Throwable#getCause()} method).
     */
    public WtfException(String message, Throwable cause) {
        super(message, cause);
    }

}

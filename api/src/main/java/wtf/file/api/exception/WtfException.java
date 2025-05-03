package wtf.file.api.exception;

/**
 * A custom exception that serves as a base exception for errors within the "wtf" file API.
 * <p>
 * This exception is used for handling general errors that do not fall under specific
 * categories such as value errors or out-of-bounds errors, but still require custom exception
 * handling in the API.
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

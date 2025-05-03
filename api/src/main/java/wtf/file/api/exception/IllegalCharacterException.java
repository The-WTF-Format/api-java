package wtf.file.api.exception;

/**
 * Thrown to indicate that an illegal character was encountered during processing.
 * <p>
 * This exception provides details about the offending character and the set of allowed characters.
 * </p>
 */
public class IllegalCharacterException extends RuntimeException {

    private final char charcter;
    private final String allowed;

    /**
     * Constructs a new {@code IllegalCharacterException} with the specified illegal character
     * and a description of allowed characters.
     *
     * @param character the illegal character that caused the exception.
     * @param allowed   a string representation of all allowed characters.
     */
    public IllegalCharacterException(char character, String allowed) {
        super("Illegal character: " + character + ". Allowed: " + allowed);
        this.charcter = character;
        this.allowed = allowed;
    }

}

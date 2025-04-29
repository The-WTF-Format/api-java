package wtf.file.api.exception;

public class IllegalCharacterException extends RuntimeException {

    private final char charcter;
    private final String allowed;

    public IllegalCharacterException(char character, String allowed) {
        super("Illegal character: " + character + ". Allowed: " + allowed);
        this.charcter = character;
        this.allowed = allowed;
    }

}

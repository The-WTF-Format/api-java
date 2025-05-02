package wtf.file.api.exception;

public class WtfException extends Exception {

    public WtfException(String message) {
        super(message);
    }

    public WtfException(String message, Throwable cause) {
        super(message, cause);
    }

}

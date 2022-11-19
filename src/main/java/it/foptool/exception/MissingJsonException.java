package it.foptool.exception;

public class MissingJsonException extends RuntimeException {
    
    public MissingJsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingJsonException(String message) {
        super(message);
    }

    public MissingJsonException(Throwable cause) {
        super(cause);
    }
}

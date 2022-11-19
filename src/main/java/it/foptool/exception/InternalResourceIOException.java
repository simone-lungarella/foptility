package it.foptool.exception;

public class InternalResourceIOException extends RuntimeException {
    
    public InternalResourceIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalResourceIOException(String message) {
        super(message);
    }

    public InternalResourceIOException(Throwable cause) {
        super(cause);
    }
}

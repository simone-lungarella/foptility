package it.foptool.exception;

public class FilenameParsingException extends RuntimeException {
    
    public FilenameParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilenameParsingException(String message) {
        super(message);
    }

    public FilenameParsingException(Throwable cause) {
        super(cause);
    }
}

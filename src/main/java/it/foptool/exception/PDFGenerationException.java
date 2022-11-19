package it.foptool.exception;

public class PDFGenerationException extends RuntimeException {
    
    public PDFGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PDFGenerationException(String message) {
        super(message);
    }

    public PDFGenerationException(Throwable cause) {
        super(cause);
    }
}

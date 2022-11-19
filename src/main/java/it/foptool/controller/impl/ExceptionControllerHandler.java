package it.foptool.controller.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.foptool.exception.FilenameParsingException;
import it.foptool.exception.InternalResourceIOException;
import it.foptool.exception.MissingJsonException;
import it.foptool.exception.PDFGenerationException;

@ControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({FilenameParsingException.class, MissingJsonException.class})
    protected ResponseEntity<String> handleDocumentNotFoundException(Exception ex) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        final String errorMessage = ex.getMessage();

        return new ResponseEntity<>(errorMessage, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class, PDFGenerationException.class, InternalResourceIOException.class})
    protected ResponseEntity<String> handleBusinessException(Exception ex) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        final String errorMessage = ex.getMessage();

        return new ResponseEntity<>(errorMessage, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

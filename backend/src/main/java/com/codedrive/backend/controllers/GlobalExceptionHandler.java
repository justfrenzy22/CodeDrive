package com.codedrive.backend.controllers;

import com.codedrive.backend.exceptions.FolderPersistenceException;
import com.codedrive.backend.exceptions.InvalidFolderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public record ErrorResponse(int status, String message) {
    }

    // 400: the client sent bad data
    @ExceptionHandler(InvalidFolderException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFolder(InvalidFolderException exc) {
        return build(HttpStatus.BAD_REQUEST, exc.getMessage());
    }

    // 503: database or storage failed
    @ExceptionHandler(FolderPersistenceException.class)
    public ResponseEntity<ErrorResponse> handlePersistence(FolderPersistenceException exc) {
        log.error("Persistence failure", exc); // logs the cause
        return build(HttpStatus.SERVICE_UNAVAILABLE, exc.getMessage());
    }

    // 500: anything unexpected
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception exc) {
        log.error("Unexpected error", exc);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
    }

    private ResponseEntity<ErrorResponse> build(HttpStatus status, String msg) {
        return ResponseEntity.status(status).body(new ErrorResponse(status.value(), msg));
    }
}

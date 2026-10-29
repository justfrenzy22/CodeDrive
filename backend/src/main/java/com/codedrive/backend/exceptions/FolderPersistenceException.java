package com.codedrive.backend.exceptions;

public class FolderPersistenceException extends RuntimeException {
    public FolderPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}

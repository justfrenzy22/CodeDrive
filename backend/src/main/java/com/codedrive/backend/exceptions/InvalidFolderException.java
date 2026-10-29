package com.codedrive.backend.exceptions;

public class InvalidFolderException extends RuntimeException {
    public InvalidFolderException(String message) {
        super(message);
    }
}

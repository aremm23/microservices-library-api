package com.artsem.api.libraryservice.exception;

public class DataNotFoundedException extends RuntimeException {
    public DataNotFoundedException(String message) {
        super(message);
    }
}

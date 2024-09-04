package com.artsem.api.coreservice.exception;

public class DataNotFoundedException extends RuntimeException {
    public DataNotFoundedException(String message) {
        super(message);
    }
}

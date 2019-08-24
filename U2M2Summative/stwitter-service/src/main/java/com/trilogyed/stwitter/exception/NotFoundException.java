package com.trilogyed.stwitter.exception;

/**
 * Exception class to handle not found cases in get APIs
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
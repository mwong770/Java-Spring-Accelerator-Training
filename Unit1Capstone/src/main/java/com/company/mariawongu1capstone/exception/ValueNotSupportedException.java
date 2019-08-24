package com.company.mariawongu1capstone.exception;

/**
 * Exception class to handle values not supported by the database,
 * such as numbers that are too long
 */
public class ValueNotSupportedException extends RuntimeException {

    public ValueNotSupportedException() {
    }

    public ValueNotSupportedException(String message) {
        super(message);
    }
}

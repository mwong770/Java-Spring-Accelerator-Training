package com.company.mariawongu1capstone.exception;

public class InsufficientQuantityException  extends RuntimeException {

    public InsufficientQuantityException() {
    }

    public InsufficientQuantityException(String message) {
        super(message);
    }
}

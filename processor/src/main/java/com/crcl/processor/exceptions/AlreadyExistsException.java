package com.crcl.processor.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException() {
    }
}

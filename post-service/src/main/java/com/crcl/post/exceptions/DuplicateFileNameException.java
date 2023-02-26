package com.crcl.post.exceptions;


public class DuplicateFileNameException extends RuntimeException {
    public DuplicateFileNameException() {
    }

    public DuplicateFileNameException(String message) {
        super(message);
    }

    public DuplicateFileNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateFileNameException(Throwable cause) {
        super(cause);
    }

    public DuplicateFileNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

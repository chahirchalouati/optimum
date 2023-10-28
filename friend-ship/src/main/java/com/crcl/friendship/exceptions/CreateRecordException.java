package com.crcl.friendship.exceptions;

public class CreateRecordException extends RuntimeException {
    public CreateRecordException() {
    }

    public CreateRecordException(String message) {
        super(message);
    }

    public CreateRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateRecordException(Throwable cause) {
        super(cause);
    }

    public CreateRecordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

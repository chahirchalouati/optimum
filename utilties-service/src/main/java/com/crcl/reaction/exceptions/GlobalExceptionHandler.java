package com.crcl.reaction.exceptions;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> badCredentialsException(BadCredentialsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SizeLimitExceededException.class)
    public ResponseEntity<String> badRequestException(SizeLimitExceededException exception) {
        var actualSize = FileUtils.byteCountToDisplaySize(exception.getActualSize());
        var permittedSize = FileUtils.byteCountToDisplaySize(exception.getPermittedSize());
        var message = "The request was rejected because its size " + actualSize + " exceeds the configured maximum " + permittedSize;

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}

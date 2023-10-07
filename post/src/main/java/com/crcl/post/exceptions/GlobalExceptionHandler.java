package com.crcl.post.exceptions;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;

import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> badCredentialsException(BadCredentialsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        final Map<String, String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .collect(toMap(ObjectError::getObjectName, DefaultMessageSourceResolvable::getDefaultMessage));
        return new ResponseEntity<>(errors, BAD_REQUEST);
    }

    @ExceptionHandler(SizeLimitExceededException.class)
    public ResponseEntity<String> badRequestException(SizeLimitExceededException exception) {
        var actualSize = FileUtils.byteCountToDisplaySize(exception.getActualSize());
        var permittedSize = FileUtils.byteCountToDisplaySize(exception.getPermittedSize());
        var message = "The request was rejected because its size " + actualSize + " exceeds the configured maximum " + permittedSize;

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}

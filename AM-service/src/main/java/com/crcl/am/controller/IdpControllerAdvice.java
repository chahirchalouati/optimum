package com.crcl.am.controller;

import com.crcl.common.exceptions.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@ControllerAdvice
public class IdpControllerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> badRequestResponse(MethodArgumentNotValidException exception) {
        final Map<String, String> errorsMap = exception.getBindingResult().getFieldErrors()
                .stream()
                .filter(field -> nonNull(field.getDefaultMessage()))
                .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
        return new ResponseEntity<>(new ErrorResponse(errorsMap), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<?> bindException(BindException exception) {
        final Map<String, String> errorsMap = exception.getBindingResult().getFieldErrors()
                .stream()
                .filter(field -> nonNull(field.getDefaultMessage()))
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return new ResponseEntity<>(new ErrorResponse(errorsMap), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> runtimeException(RuntimeException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}


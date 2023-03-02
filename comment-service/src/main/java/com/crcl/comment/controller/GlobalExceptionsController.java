package com.crcl.comment.controller;

import com.crcl.common.exceptions.EntityNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionsController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entity(EntityNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
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
}

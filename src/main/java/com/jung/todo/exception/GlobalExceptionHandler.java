package com.jung.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TodoException.class)
    public ResponseEntity<ErrorResponse> handleOnstagramException(TodoException ex) {
        ExceptionEnum errorType = ex.getExceptionEnum();
        HttpStatus status = errorType.getStatus();
        return new ResponseEntity<>(new ErrorResponse(errorType.getStatus(),errorType.getMessage()), status);
    }

}
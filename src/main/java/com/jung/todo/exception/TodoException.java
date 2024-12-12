package com.jung.todo.exception;

import lombok.Getter;

//@AllArgsConstructor
@Getter
public class TodoException extends RuntimeException {
    private final ExceptionEnum exceptionEnum;

    public TodoException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.exceptionEnum = exceptionEnum;
    }
}
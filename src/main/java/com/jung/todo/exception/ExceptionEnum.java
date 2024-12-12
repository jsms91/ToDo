package com.jung.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {
    REGISTER_FAIL(HttpStatus.BAD_REQUEST, "등록 실패"),
    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "조회 실패");

    private HttpStatus status;
    private String message;
}

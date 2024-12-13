package com.jung.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {
    REGISTER_FAIL(HttpStatus.BAD_REQUEST, "등록 실패"), //1. todoRegister
    REGISTER_RETURN_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "등록 후 해당 데이터 반환 실패"), //1-1. todoRegister - todoContent
    TODO_LIST_NOT_FOUND(HttpStatus.NOT_FOUND, "목록을 가져올 수 없습니다."), //2. todoList
    TODO_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "데이터가 존재하지 않습니다."), //3. TodoInfo
    TODO_INFO_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러"), // 3. TodoInfo
    UPDATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "업데이트 실패"), //4. todoUpdate
    SETINACTIVE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "비활성화 실패"), // 4. setInactive
    DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "삭제 실패"), //5. todoDelete
    ACTIVE_RESET_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "활성화 초기화 실패"); //6. activeReset

    private HttpStatus status;
    private String message;
}

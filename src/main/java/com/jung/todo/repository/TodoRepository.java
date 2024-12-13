package com.jung.todo.repository;

import com.jung.todo.dto.TodoInfoDto;
import com.jung.todo.dto.TodoRegisterDto;
import com.jung.todo.dto.TodoUpdateDto;
import com.jung.todo.entity.TodoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoRepository {
    void todoRegister(TodoRegisterDto todoRegisterDto); //1. 할일 등록 후 등록한 정보 출력
    TodoEntity todoContent(int todoNumber); //1-1 등록한 할 일 정보
    List<TodoInfoDto> todoList(); //2. 할 일 목록
    TodoInfoDto todoInfo(int todoNumber); //3. 할 일 정보
    void todoUpdate(TodoUpdateDto todoUpdateDto); //4. 할 일 수정
    void todoSetInactive(int todoNumber); //4-1. 비활성화 하기
    void todoDelete(int todoNumber); //5. 할 일 삭제(참고: 삭제 성공하면 삭제한 행의 개수를 반환한다. 필요하면 int로 반환받으면 됨)
}
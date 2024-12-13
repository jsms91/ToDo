package com.jung.todo.service;

import com.jung.todo.dto.TodoInfoDto;
import com.jung.todo.dto.TodoRegisterDto;
import com.jung.todo.dto.TodoUpdateDto;
import com.jung.todo.entity.TodoEntity;

import java.util.List;

public interface TodoService {
    TodoEntity register(TodoRegisterDto todoRegisterDto); //1. 할 일 등록
    List<TodoInfoDto> todoList(); //2. 할 일 목록
    TodoInfoDto todoInfo(int todoNumber); //3. 할 일 정보
    TodoInfoDto todoUpdate(int todoNumber, TodoUpdateDto todoUpdateDto); //4. 할 일 수정
    void todoDelete(int todoNumber);//5. 할 일 삭제
}

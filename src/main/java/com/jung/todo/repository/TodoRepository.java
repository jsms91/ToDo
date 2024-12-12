package com.jung.todo.repository;

import com.jung.todo.dto.TodoDto;
import com.jung.todo.entity.TodoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoRepository {
    void todoRegister(TodoDto todoDto); //할일 등록 후 등록한 정보 출력
    TodoEntity todoContent(int todoNumber); //해당 할 일 정보
}
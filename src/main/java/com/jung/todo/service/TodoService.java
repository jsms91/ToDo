package com.jung.todo.service;

import com.jung.todo.dto.TodoDto;
import com.jung.todo.entity.TodoEntity;

public interface TodoService {
    TodoEntity register(TodoDto todoDto); //할일 등록
}

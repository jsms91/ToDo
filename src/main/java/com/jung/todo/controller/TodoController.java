package com.jung.todo.controller;

import com.jung.todo.dto.PostResultDto;
import com.jung.todo.dto.TodoDto;
import com.jung.todo.entity.TodoEntity;
import com.jung.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/register")
    public ResponseEntity<PostResultDto> todoRegister(@RequestBody TodoDto todoDto) {
        log.info("할일 등록");
        TodoEntity todoInfo = todoService.register(todoDto);
        log.info("성공");
        PostResultDto resultDto = new PostResultDto("ToDoRegister_Success",todoInfo);
        return new ResponseEntity<>(resultDto, HttpStatus.CREATED); //CREATED:201
    }

}
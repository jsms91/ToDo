package com.jung.todo.controller;

import com.jung.todo.dto.ResultDto;
import com.jung.todo.dto.TodoInfoDto;
import com.jung.todo.dto.TodoRegisterDto;
import com.jung.todo.dto.TodoUpdateDto;
import com.jung.todo.entity.TodoEntity;
import com.jung.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    //1. 할 일 등록
    @Operation(summary = "할 일 등록", description = "title,description,targetDate를 입력받아 등록")
    @PostMapping("/register")
    public ResponseEntity<ResultDto> todoRegister(@RequestBody TodoRegisterDto todoRegisterDto) {
        log.info("할 일 등록");

        TodoEntity todoInfo = todoService.register(todoRegisterDto);
        ResultDto<TodoEntity> resultDto = new ResultDto("ToDoRegister_Success",todoInfo);

        return new ResponseEntity<>(resultDto, HttpStatus.CREATED); //CREATED:201
    }

    //2. 할 일 목록
    @GetMapping("/list")
    public ResponseEntity<ResultDto> todoList() {
        log.info("할 일 목록");

        List<TodoInfoDto> list = todoService.todoList();
        ResultDto<TodoInfoDto> resultDto = new ResultDto("todoList_Success", list);

        return new ResponseEntity<>(resultDto, HttpStatus.OK); //OK:200
    }

    //3. 할 일 상세내용
    @GetMapping("/todoInfo/{todoNumber}")
    public ResponseEntity<ResultDto> todoInfo(@PathVariable("todoNumber") int todoNumber) {
        log.info("할 일 내용");

        TodoInfoDto todoInfo = todoService.todoInfo(todoNumber);
        ResultDto<TodoInfoDto> resultDto = new ResultDto<>("todoInfo_Success",todoInfo);

        return new ResponseEntity<>(resultDto, HttpStatus.OK); //Ok:200
    }

    //4. 할 일 수정
    @PutMapping("/todoModify/{todoNumber}")
    public ResponseEntity<ResultDto> todoModify(@PathVariable("todoNumber") int todoNumber,
                                                @RequestBody TodoUpdateDto todoUpdateDto) {
        log.info("할 일 수정(1. 내용업데이트, 2.완료처리했으면 비활성화로 수정");

        TodoInfoDto todoInfoDto = todoService.todoUpdate(todoNumber, todoUpdateDto);
        ResultDto<TodoInfoDto> resultDto = new ResultDto<>("todoUpdate_Success", todoInfoDto);

        return new ResponseEntity<>(resultDto, HttpStatus.OK); //Ok:200
    }

    //5. 할 일 삭제
    @DeleteMapping("/todoDelete/{todoNumber}")
    public ResponseEntity<ResultDto> todoDeletet(@PathVariable("todoNumber") int todoNumber) {
        log.info("할일 삭제");

        todoService.todoDelete(todoNumber);
        ResultDto<TodoInfoDto> resultDto = new ResultDto<>("todoDelete_Success",null);

        return new ResponseEntity<>(resultDto, HttpStatus.OK); //Ok:200
    }

}
package com.jung.todo.service;

import com.jung.todo.dto.TodoDto;
import com.jung.todo.entity.TodoEntity;
import com.jung.todo.exception.TodoException;
import com.jung.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jung.todo.exception.ExceptionEnum.REGISTER_FAIL;
import static com.jung.todo.exception.ExceptionEnum.TODO_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    @Override //1. 할 일 등록
    public TodoEntity register(TodoDto todoDto) {
        System.out.println("===============");
        System.out.println("제목 -> " + todoDto.getTitle());
        System.out.println("설명 -> " + todoDto.getDescription());
        System.out.println("해당 날짜 -> " + todoDto.getTargetDate());
        System.out.println("===============");
        try {
            //할 일 등록
            todoRepository.todoRegister(todoDto);
            //등록한 할 일 정보 조회
            TodoEntity todoEntity = TodoContent(todoDto.getTodoNumber()); //할 일 작성 후 고유번호 출력(todoDto에 매핑되어있음)
            return todoEntity;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ToDoRegister Error -> " + e.getMessage());
            throw new TodoException(REGISTER_FAIL); //예외처리하기
        }
    }
    
    //할 일 조회
    //할 일 상세내용
    //할 일 수정
    //할 일 삭제

    //할일 상세내용(등록 후 등록한 정보)
    private TodoEntity TodoContent(int todoNumber) {
        try {
            return todoRepository.todoContent(todoNumber);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ToDoRegister Error -> " + e.getMessage());
            throw new TodoException(TODO_NOT_FOUND); //예외처리하기(조회 실패)
        }
    }
}
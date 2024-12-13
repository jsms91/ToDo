package com.jung.todo.service;

import com.jung.todo.dto.TodoInfoDto;
import com.jung.todo.dto.TodoRegisterDto;
import com.jung.todo.dto.TodoUpdateDto;
import com.jung.todo.entity.TodoEntity;
import com.jung.todo.exception.TodoException;
import com.jung.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.jung.todo.exception.ExceptionEnum.*;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    @Override //1. 할 일 등록
    public TodoEntity register(TodoRegisterDto todoRegisterDto) {
        try {
            //할 일 등록
            todoRepository.todoRegister(todoRegisterDto);
            //등록한 할 일 정보 조회
            TodoEntity todoEntity = todoContent(todoRegisterDto.getTodoNumber()); //할 일 작성 후 고유번호 출력(todoDto에 매핑되어있음)
            return todoEntity;
        } catch (Exception e) {
//            e.printStackTrace();
            log.error("ToDoRegister Error -> " + e.getMessage());
            throw new TodoException(REGISTER_FAIL); //등록 실패 예외 처리
        }
    }

    //1-1. 할일 상세내용(등록 후 등록한 정보) -- 등록한 번호 그대로 출력할 거기 때문에 NOT_FOUND 예외 필요x
    private TodoEntity todoContent(int todoNumber) {
        try {
            return todoRepository.todoContent(todoNumber);
        } catch (Exception e) {
            log.error("ToDoRegister Data Return Error -> " + e.getMessage());
            throw new TodoException(REGISTER_RETURN_FAIL); //데이터가 없는게 아니라 오류
        }
    }
    
    //2. 할 일 목록
    @Override
    public List<TodoInfoDto> todoList() {
        try {
            List<TodoInfoDto> todoInfoDtos = todoRepository.todoList();
            return todoInfoDtos;
        } catch (Exception e) {
            log.error("todoList not found -> " + e.getMessage());
            throw new TodoException(TODO_LIST_NOT_FOUND); //NOT_FOUND(목록을 가져올 수 없습니다.)
        }
    }

    //3. 할 일 정보
    @Override
    public TodoInfoDto todoInfo(int todoNumber) {
        try {
            // todoRepository에서 todoNumber에 해당하는 할 일 정보를 가져옴
            Optional<TodoInfoDto> todoInfo = Optional.ofNullable(todoRepository.todoInfo(todoNumber));
            // 만약 값이 존재하면, 값을 반환하고 없으면 예외를 던짐
            return todoInfo.orElseThrow(() -> new TodoException(TODO_INFO_NOT_FOUND));
            /* 2안
            if (!todoInfo.isPresent()) {
                throw new TodoException(TODO_NOT_FOUND);  // 예외를 직접 던짐
            }
            return todoInfo.get();
            */
        } catch (TodoException e) {
//            e.printStackTrace();
            log.error("todoInfo not found -> " + e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("todoInfo Error -> " + e.getMessage());
            throw new TodoException(TODO_INFO_ERROR);
        }
    }

    //4. 할 일 수정
    @Override
    public TodoInfoDto todoUpdate(int todoNumber, TodoUpdateDto todoUpdateDto) {
        try {
            todoUpdateDto.setUpdateData(todoNumber, todoUpdateDto); //todoUpdateDto에 todoNumber 넣기(데이터 가공은 서비스단에서 controller x)

            todoRepository.todoUpdate(todoUpdateDto); //1. 할 일 수정
            boolean activeResult = todoUpdateDto.getStatus();//2. 할 일 수정 후 완료체크 여부 확인 후 비활성화하기

            if(activeResult) { //완료 상태이면(true)
                setInactive(todoNumber);//2-1 비활성화하기
            }
            return todoInfo(todoNumber); //3.최종 수정된 정보 반환
        } catch (Exception e) {
            log.error("update Error -> " + e.getMessage());
            throw new TodoException(UPDATE_ERROR);
        }
    }

    //4-1. 비활성화 하기
    private void setInactive(int todoNumber) {
        try {
            todoRepository.todoSetInactive(todoNumber);
        } catch (Exception e) {
            log.error("setInactive Error -> " + e.getMessage());
            throw new TodoException(SETINACTIVE_ERROR);
        }
    }

    //5. 할 일 삭제
    @Override
    public void todoDelete(int todoNumber) {
        try {
            todoRepository.todoDelete(todoNumber);
        } catch (Exception e) {
            log.error("todoInfo Error(SERVER_ERROR) -> " + e.getMessage());
            throw new TodoException(DELETE_ERROR);
        }

    }

}
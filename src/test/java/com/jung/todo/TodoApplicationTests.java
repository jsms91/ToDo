package com.jung.todo;

import com.jung.todo.dto.TodoInfoDto;
import com.jung.todo.dto.TodoRegisterDto;
import com.jung.todo.dto.TodoUpdateDto;
import com.jung.todo.entity.TodoEntity;
import com.jung.todo.repository.TodoRepository;
import com.jung.todo.service.TodoServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
class TodoApplicationTests {
	@Mock
	private TodoRepository todoRepository; // 레포지토리 Mock
	@InjectMocks
	private TodoServiceImpl todoService; // 서비스 인스턴스

	//1. todo 등록
	@DisplayName("todo_register")
	@Test
	void todoRegister() {
		// given : 할 일을 저장
		String title = "운동";
		String description = "윗가슴 운동";
		LocalDate targetDate = LocalDate.parse("2024-12-31");

		TodoRegisterDto todoRegisterDto = new TodoRegisterDto(0, title,description,targetDate);

		TodoEntity mockEntity = new TodoEntity();
		mockEntity.RegisterTest(title,description,targetDate);

		// when: todoRegister 호출이 아무 작업도 하지 않도록 설정
		Mockito.doNothing().when(todoRepository).todoRegister(any(TodoRegisterDto.class));

		// todoContent 메서드가 호출될 때 mockEntity를 반환하도록 설정
		Mockito.when(todoRepository.todoContent(anyInt())).thenReturn(mockEntity);

		// when: 실제 서비스 호출
		TodoEntity result = todoService.register(todoRegisterDto);

		// then: 결과 검증
		assertThat(result.getTitle()).isEqualTo(title); // 등록된 제목 확인
		assertThat(result.getDescription()).isEqualTo(description); // 등록된 내용 확인
		assertThat(result.getTargetDate()).isEqualTo(targetDate); // 등록된 목표일 확인

		// verify: 메서드 호출 검증
		verify(todoRepository).todoRegister(any(TodoRegisterDto.class)); // todoRegister가 호출되었는지 검증
		verify(todoRepository).todoContent(anyInt()); // todoContent가 호출되었는지 검증
	}

	//2. todo 목록
	@DisplayName("todo_list")
	@Test
	void todoList() {
		// given : 빈 리스트를 반환할 mock 객체 준비
		List<TodoInfoDto> todoInfoDtoList = new ArrayList<>();
		//todoList 메서드가 호출될 때 todoInfoDtoList를 반환
		Mockito.when(todoRepository.todoList()).thenReturn(todoInfoDtoList);

		//when : 실제 서비스 호출
		List<TodoInfoDto> todoInfos = todoService.todoList();

		// then : 결과 검증
		// 반환된 todoList가 mock 데이터와 동일한지 확인
		assertThat(todoInfos).isEqualTo(todoInfoDtoList); // 리스트가 예상한 값과 동일한지 확인

		// verify: 메서드 호출 검증
		verify(todoRepository).todoList(); // todoRepository의 todoList 메서드가 호출되었는지 검증
	}

	//3. todo 정보
	@DisplayName("todo_info")
	@Test
	void todoInfo() {
		//given: 조회할 할 일 id입력(실제 데이터를 넣어도 되고 anyInt()로 해도됨)
//		int todoNumber = 12;
		TodoInfoDto todoInfoDto = new TodoInfoDto();

		//when: todoNumber인 정보를 todoInfoDto에 반환
		Mockito.when(todoRepository.todoInfo(anyInt())).thenReturn(todoInfoDto);
		//when: 실제 서비스 호출
		TodoInfoDto todoInfo = todoService.todoInfo(anyInt());

		//then: 검증
		assertThat(todoInfo).isEqualTo(todoInfoDto);

		//verify: 메서드 호출 검증
		verify(todoRepository).todoInfo(anyInt());
	}

	//4. todo 수정
	@DisplayName("todo_update")
	@Test
	void todoUpdate() {
		//given: 수정할 번호와 데이터
		int todoNumber = 15;
		String title = "제목을 수정합니다.";
		String description = "내용을 수정합니다.";
		LocalDate targetDate = LocalDate.parse("2024-12-30");
		Boolean staus = true;
		TodoUpdateDto todoUpdateDto = new TodoUpdateDto(todoNumber,title,description,targetDate,staus);

		TodoInfoDto MockDto = new TodoInfoDto();

		//when: 1)업데이트, 2)비활성화
		Mockito.doNothing().when(todoRepository).todoUpdate(todoUpdateDto); //1) 업데이트
		Mockito.doNothing().when(todoRepository).todoSetInactive(todoUpdateDto.getTodoNumber()); //2) 비활성화
		Mockito.when(todoRepository.todoInfo(anyInt())).thenReturn(MockDto); // 수정 된 정보 반환

		// then: 서비스 호출 및 결과 검증
		TodoInfoDto todoInfoDto = todoService.todoUpdate(todoNumber, todoUpdateDto);
		assertThat(todoInfoDto).isEqualTo(MockDto);

		// verify: 메서드 호출 검증
		verify(todoRepository).todoUpdate(todoUpdateDto);
		verify(todoRepository).todoSetInactive(todoNumber);
		verify(todoRepository).todoInfo(todoNumber);
	}

	//5. todo 삭제
	@DisplayName("todo_delete")
	@Test
	void todoDelete() {
		//given: 삭제할 번호 입력
//		int todoNumber = 12;

		// when: todoRegister 호출이 아무 작업도 하지 않도록 설정(delete는 void 타입으로 반환x)
		Mockito.doNothing().when(todoRepository).todoDelete(Mockito.anyInt());
		// when: 실제 서비스 호출
		todoService.todoDelete(Mockito.anyInt());

		// verify: 메서드 호출 검증
		verify(todoRepository).todoDelete(Mockito.anyInt());
	}

}
/*
1. anyInf()
- 매개변수 검증을 위한 메서드
- Mockito.when()에서 사용하여 특정 메서드가 호출될 때 어떤 값이든 허용하도록 설정하는 데 사용
 */
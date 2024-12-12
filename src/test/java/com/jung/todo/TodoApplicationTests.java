package com.jung.todo;

import com.jung.todo.dto.TodoDto;
import com.jung.todo.entity.TodoEntity;
import com.jung.todo.service.TodoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class TodoApplicationTests {

	@Autowired
	TodoService todoService;

	@DisplayName("새로운 todo를 등록")
	@Test
	void register() {
		// given : 할 일을 저장
		String title = "운동";
		String description = "등 운동";
		LocalDate targetDate = LocalDate.parse("2024-12-25");
		TodoDto todoDto = new TodoDto(0, title,description,targetDate);

		// when : 실제로 메뉴를 저장
		TodoEntity todoEntity = todoService.register(todoDto);

		//then : 등록 후 등록한 정보 출력
		assertThat(todoEntity.getTitle()).isEqualTo(title);

	}


	@Test
	void contextLoads() {
	}
}
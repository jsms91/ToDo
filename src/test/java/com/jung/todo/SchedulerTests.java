package com.jung.todo;

import com.jung.todo.repository.TodoSchedulerRepository;
import com.jung.todo.scheduling.SchedulerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SchedulerTests {
    @Mock
    private TodoSchedulerRepository todoSchedulerRepository; // 레포지토리 Mock
    @InjectMocks
    private SchedulerServiceImpl schedulerServiceImpl; // 서비스 인스턴스

    //6. 앱 실행시 targetDate가 지난 할 일들의 상태를 비활성화로 자동 변경
    @DisplayName("ExpiredTodo_setInactive")
    @Test
    void ExpiredTodoSetInactive() {
        //1.given: 기준일
        LocalDate referenceDate = LocalDate.now().minusDays(1);

        //2. when
        //호출이 아무작업도 하지 않는 void 타입으로 doNothing()
        Mockito.doNothing().when(todoSchedulerRepository).setInactive(referenceDate);
        //실제 서비스 호출
        todoSchedulerRepository.setInactive(referenceDate);

        //3. verify: 메서드 호출 검증
        verify(todoSchedulerRepository).setInactive(referenceDate);
    }

}
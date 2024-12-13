package com.jung.todo.scheduling;

import com.jung.todo.exception.TodoException;
import com.jung.todo.repository.TodoSchedulerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import static com.jung.todo.exception.ExceptionEnum.ACTIVE_RESET_ERROR;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class SchedulerServiceImpl implements SchedulerService {

    private final TodoSchedulerRepository todoSchedulerRepository;

    @Override
    @Scheduled(initialDelay = 0, fixedDelay = Long.MAX_VALUE) // 앱 시작 후 즉시 실행
    // @Scheduled(cron = "0 0 0 * * *") // 매일 자정 실행
    // @Scheduled(cron = "0/10 * * * * *") // 매 10초마다 실행
    public void setExpiredInactive() {
        LocalDate referenceDate = LocalDate.now().minusDays(1);
        //1) 문자열 타입 String referenceDate = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //2) 문자열 변환 String refDate = referenceDate.toString();//String.valueOf(referenceDate)도 가능
        log.info("기준날짜는(어제) : " + referenceDate);
        try {
            todoSchedulerRepository.setInactive(referenceDate);
        } catch (Exception e) {
            log.error("activeReset Error -> " + e.getMessage());
            throw new TodoException(ACTIVE_RESET_ERROR);
        }
    }

}
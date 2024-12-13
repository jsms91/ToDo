package com.jung.todo.repository;

import com.jung.todo.dto.TodoInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;

@Mapper
public interface TodoSchedulerRepository {
    //자정 마다 active가 true인 할 일 목록에서 targetDate가 지난 할 일들을 비활성화(active=false)로 자동 수정
    void setInactive(LocalDate referenceDate);
    TodoInfoDto selectInfo();
}
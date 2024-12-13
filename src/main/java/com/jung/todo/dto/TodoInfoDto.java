package com.jung.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

//할 일 정보 Dto
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoInfoDto {
    private int todoNumber; //할 일 번호(기본키)
    private String title; //제목
    private String description; //설명
    private Boolean status; //상태(완료여부: 기본값 미완료)

    @JsonFormat(pattern = "yyyy년MM월dd일")
    private LocalDate targetDate; //목표 날짜(해당일)
    private Boolean active; //활성화 상태(기본값 true:활성화)

    @JsonFormat(pattern = "yyyy년MM월dd일' 'HH시mm분ss초")  // 이 부분을 추가
    private LocalDateTime createdAt; //생성일

    @JsonFormat(pattern = "yyyy년MM월dd일' 'HH시mm분ss초")  // 이 부분을 추가
    private LocalDateTime updatedAt; //수정일
}

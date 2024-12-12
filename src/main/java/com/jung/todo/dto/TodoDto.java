package com.jung.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TodoDto {
    private int todoNumber; //고유번호(1씩 자동증가 db에서 처리)
    private String title; //제목
    private String description; //내용
    private LocalDate targetDate; //해당 날짜
}

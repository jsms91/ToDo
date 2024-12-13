package com.jung.todo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoEntity {
    private int todoNumber; //할 일 번호(기본키)
    private String title; //제목
    private String description; //설명
    private Boolean status; //상태(완료여부: 기본값 미완료)

    @Schema(example = "2024-12-15", description = "목표일")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate; //목표 날짜(해당일)

    private Boolean active; //활성화 상태(기본값 true:활성화)

    private LocalDateTime createdAt; //생성일
    private LocalDateTime updatedAt; //수정일

    //테스트를 위한 메서드
    public void  RegisterTest(String title, String description, LocalDate targetDate) {
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
    }

    @Override
    public String toString() {
        return "TodoEntity{" +
                "todoNumber=" + todoNumber +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", targetDate=" + targetDate +
                ", active=" + active +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
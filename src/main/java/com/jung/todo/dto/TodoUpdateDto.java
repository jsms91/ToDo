package com.jung.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//수정할 데이터 dto
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoUpdateDto {
    private int todoNumber;
    private String title;
    private String description;
//    @Schema(example = "2024-12-15", description = "목표일")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;
    private Boolean status;

    //업데이트할 때 받은 todoNumber 값을 현재 필드값에 넣기
    public void setUpdateData(int todoNumber, TodoUpdateDto todoUpdateDto) {
        this.todoNumber = todoNumber;
        this.title = todoUpdateDto.getTitle();
        this.description = todoUpdateDto.getDescription();
        this.targetDate = todoUpdateDto.getTargetDate();
        this.status = todoUpdateDto.getStatus();
    }

    @Override
    public String toString() {
        return "TodoUpdateDto{" +
                "todoNumber=" + todoNumber +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", status=" + status +
                '}';
    }
}
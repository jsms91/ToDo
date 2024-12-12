package com.jung.todo.dto;

import com.jung.todo.entity.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostResultDto {
    private final String result = "success";
    private String messaege;
    private TodoEntity todoEntity;
}
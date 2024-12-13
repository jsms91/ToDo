package com.jung.todo.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//일괄된 결과를 전송하기 위한 dto(result, message, 정보(객체타입))
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto<T> {
    @Schema(description = "결과 상태", example = "success")
    private final String result = "success";

    @Schema(description = "응답 메시지", example = "todoList_Success")
    private String message;

    @ArraySchema(schema = @Schema(description = "리스트 데이터", implementation = TodoInfoDto.class))
    private T info;
}
package com.foodfinder.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApiResponseDto<T> {
    private boolean isSuccess;
    private String message;
    private T response;
}
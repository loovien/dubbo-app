package com.example.web.controller;

import com.example.common.response.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrController {

    @ExceptionHandler
    public Result<?> ExceptionHandler(Exception exception) {
        return Result.wrapErr(-1, exception.getMessage());
    }
}

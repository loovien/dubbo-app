package com.example.web.controller;

import com.example.common.exception.BaseException;
import com.example.common.response.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrController {
    @ExceptionHandler
    public ResponseEntity<Result<?>> exceptionHandler(Exception exception) {
        Result<Object> objectResult = Result.wrapErr(-1, exception.getMessage());
        return new ResponseEntity<>(objectResult, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Result<?>> baseExceptionHandler(BaseException baseException) {
        Result<Object> objectResult = Result.wrapErr(baseException.getCode(), baseException.getMessage());
        return new ResponseEntity<>(objectResult, HttpStatus.OK);
    }
}

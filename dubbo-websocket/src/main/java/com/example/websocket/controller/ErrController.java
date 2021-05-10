package com.example.websocket.controller;

import com.example.common.dto.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * created 5/10/2021 12:52 PM
 *
 * @author luowen <loovien@163.com>
 */
@ControllerAdvice
public class ErrController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<?>> handlerException() {
        Result<String> data = Result.wrapOK("{\"tip\":\"use websocket connect endpoint: /ws\"}");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}

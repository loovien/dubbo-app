package com.example.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Result<T> {
    Integer code;

    String message;

    T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> wrapOK(T data) {
        return new Result<T>(0, "success", data);
    }

    public static <T> Result<T> wrapErr(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> wrapErr(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> wrapErr(String message) {
        return new Result<>(-1, message, null);
    }
}

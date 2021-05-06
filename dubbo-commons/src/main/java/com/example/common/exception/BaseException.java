package com.example.common.exception;

/**
 * created 5/6/2021 12:50 PM
 *
 * @author luowen <loovien@163.com>
 */
public class BaseException extends RuntimeException {

    protected Integer code;

    protected String message;

    public BaseException() {
    }

    public BaseException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}

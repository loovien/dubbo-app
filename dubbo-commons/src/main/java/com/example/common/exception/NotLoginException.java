package com.example.common.exception;

/**
 * created 5/6/2021 12:09 PM
 *
 * @author luowen <loovien@163.com>
 */
public class NotLoginException extends BaseException {
    protected Integer code = -100;

    protected String message = "未登录";

    public NotLoginException() {
        super.setCode(code);
        super.setMessage(message);
    }
}

package com.yapp.yongyong.global.error;

public class NotDataEqualsException extends BusinessException {
    public NotDataEqualsException(String message) {
        super(message, GlobalErrorCode.NOT_EQUAL);
    }
}

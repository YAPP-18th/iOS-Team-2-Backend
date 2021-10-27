package com.yapp.user.global.error;

public class NotDataEqualsException extends BusinessException {
    public NotDataEqualsException(String message) {
        super(message, GlobalErrorCode.NOT_EQUAL);
    }
}

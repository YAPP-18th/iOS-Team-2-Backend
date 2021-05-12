package com.yapp.yongyong.global.error;

public class BadRequestException extends BusinessException {
    public BadRequestException(String message) {
        super(message, GlobalErrorCode.BAD_REQUEST);
    }
}

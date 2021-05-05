package com.yapp.yongyong.global.error;

public class NotExistException extends  BusinessException{
    public NotExistException(String message) {
        super(message,GlobalErrorCode.NOT_EXIST);
    }
}

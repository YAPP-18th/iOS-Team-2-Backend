package com.yapp.user.global.error;

public class NotDeleteException extends BusinessException {
    public NotDeleteException(String message) {
        super(message, GlobalErrorCode.NOT_DELTE_ERROR);
    }
}

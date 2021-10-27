package com.yapp.user.domain.error;


import com.yapp.user.global.error.BusinessException;

public class UserNotExistException extends BusinessException {
    public UserNotExistException(String message) {
        super(message, UserErrorCode.NOT_EXIST);
    }
}

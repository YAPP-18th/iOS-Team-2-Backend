package com.yapp.yongyong.domain.user.error;

import com.yapp.yongyong.global.error.BusinessException;

public class UserNotExistException extends BusinessException {
    public UserNotExistException(String message) {
        super(message, UserErrorCode.NOT_EXIST);
    }
}

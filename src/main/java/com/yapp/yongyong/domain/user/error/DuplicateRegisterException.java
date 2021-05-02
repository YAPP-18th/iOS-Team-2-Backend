package com.yapp.yongyong.domain.user.error;

import com.yapp.yongyong.global.error.BusinessException;

public class DuplicateRegisterException extends BusinessException {
    public DuplicateRegisterException(String message) {
        super(message, UserErrorCode.ALREADY_REGISTER);
    }
}

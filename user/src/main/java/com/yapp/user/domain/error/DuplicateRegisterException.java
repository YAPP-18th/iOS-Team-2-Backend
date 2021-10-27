package com.yapp.user.domain.error;


import com.yapp.user.global.error.BusinessException;

public class DuplicateRegisterException extends BusinessException {
    public DuplicateRegisterException(String message) {
        super(message, UserErrorCode.ALREADY_REGISTER);
    }
}

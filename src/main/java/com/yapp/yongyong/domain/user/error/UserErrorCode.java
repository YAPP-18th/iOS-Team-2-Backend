package com.yapp.yongyong.domain.user.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yapp.yongyong.global.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {

    ALREADY_REGISTER("U002", "Already Register"),
    NOT_EXIST("U003", "Data Not Exist"),
    INVALID_LOGIN_VALUE("U004", "Invalid ID or Password"),
    NOT_DELETE_ERROR("U007", "Not Delete Completed");

    private final String code;
    private final String message;
}

package com.yapp.yongyong.global.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String message;
    private String code;
    private String detail;

    private ErrorResponse(final ErrorCode code, final String detail) {
        this.message = code.getMessage();
        this.code = code.getCode();
        this.detail = detail;
    }

    public static ErrorResponse of(final ErrorCode code, final String detail) {
        return new ErrorResponse(code, detail);
    }
}
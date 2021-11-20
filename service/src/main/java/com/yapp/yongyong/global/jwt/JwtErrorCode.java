package com.yapp.yongyong.global.jwt;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.yapp.yongyong.global.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@AllArgsConstructor
public enum JwtErrorCode implements ErrorCode {

    WRONG_TOKEN_SIGNATURE("J001", "Wrong Token Signature"),
    EXPIRED_TOKEN("J002", "Expired Token"),
    UNSUPPORTED_TOKEN("J003", "Unsupported Token"),
    ILLEGAL_TOKEN("J004", "Illegal Token"),
    JWT_ERROR("J005", "JWT Error"),
    UNDER_ROLE("J006", "Not Available Role to Access");

    private final String code;
    private final String message;
}

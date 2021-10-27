package com.yapp.user.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorCode {
    NOT_EXIST("G001", "Not Exist"),
    NOT_UNIQUE("G002", "Not Unique"),
    IO_NOT_WORK("G003", "IO Not Work"),
    NOT_DELTE_ERROR("G004", "Not Delete Completed"),
    OAUTH2_NOT_WORK("G005", "OAuth2 Not Work"),
    BAD_REQUEST("G006", "Bad Request"),
    NOT_EQUAL("G007", "Not Equals");

    private final String code;
    private final String message;
}

package com.yapp.yongyong.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorCode{
    NOT_EXIST(400,"G001","Not Exist"),
    NOT_UNIQUE(400,"G002","Not Unique"),
    IO_NOT_WORK(502,"G003","IO Not Work"),
    NOT_DELTE_ERROR(503,"U007","Not Delete Completed")
    ;

    private int status;
    private final String code;
    private final String message;
}

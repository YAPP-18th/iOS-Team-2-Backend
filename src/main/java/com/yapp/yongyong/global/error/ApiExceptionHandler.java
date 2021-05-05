package com.yapp.yongyong.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ErrorResponse> handleNotUniqueException(NullPointerException e) {
        log.error("NullPointerException", e);
        final ErrorResponse response = ErrorResponse.of(GlobalErrorCode.NOT_EXIST, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotExistException.class)
    protected ResponseEntity<ErrorResponse> handleNotExistException(NotExistException e) {
        log.error("NotExistException", e);
        final ErrorResponse response = ErrorResponse.of(GlobalErrorCode.NOT_EXIST, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotDeleteException.class)
    protected ResponseEntity<ErrorResponse> handleNotDeleteException(NotDeleteException e) {
        log.error("NotDeleteException", e);
        final ErrorResponse response = ErrorResponse.of(GlobalErrorCode.NOT_DELTE_ERROR, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

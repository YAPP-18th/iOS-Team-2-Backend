package com.yapp.yongyong.global.error;

import com.yapp.yongyong.domain.user.error.UserNotExistException;
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
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        log.error("BadRequestException", e);
        final ErrorResponse response = ErrorResponse.of(GlobalErrorCode.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotDataEqualsException.class)
    protected ResponseEntity<ErrorResponse> handleNotDataEqualsException(NotDataEqualsException e) {
        log.error("NotDataEqualsException", e);
        final ErrorResponse response = ErrorResponse.of(e.getErrorCode(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotExistException.class)
    protected ResponseEntity<ErrorResponse> handleUserNotExistException(UserNotExistException e) {
        log.error("UserNotExistException", e);
        final ErrorResponse response = ErrorResponse.of(e.getErrorCode(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

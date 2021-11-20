package com.yapp.yongyong.domain.user.error;


import com.yapp.yongyong.global.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("com.yapp.yongyong.domain.user")
public class UserExceptionHandler {
    @ExceptionHandler(DuplicateRegisterException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(DuplicateRegisterException e) {
        log.error("DuplicateRegisterException", e);
        final ErrorResponse response = ErrorResponse.of(UserErrorCode.ALREADY_REGISTER, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

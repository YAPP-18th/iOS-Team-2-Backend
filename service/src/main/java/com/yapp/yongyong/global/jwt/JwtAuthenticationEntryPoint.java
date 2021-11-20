package com.yapp.yongyong.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.yapp.yongyong.global.error.ErrorCode;
import com.yapp.yongyong.global.error.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        sendErrorMessage(response, JwtErrorCode.JWT_ERROR, authException.getMessage());
    }

    private void sendErrorMessage(HttpServletResponse res, ErrorCode errorCode, String message) throws IOException {
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        res.setContentType(MediaType.APPLICATION_JSON.toString());
        res.getWriter().write(this.objectMapper
                .writeValueAsString(ErrorResponse.of(errorCode, message)));
    }
}

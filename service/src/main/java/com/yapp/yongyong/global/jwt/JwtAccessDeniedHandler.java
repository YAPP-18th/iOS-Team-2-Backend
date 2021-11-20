package com.yapp.yongyong.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yapp.yongyong.global.error.ErrorCode;
import com.yapp.yongyong.global.error.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        sendErrorMessage(response, JwtErrorCode.UNDER_ROLE, accessDeniedException.getMessage());
    }

    private void sendErrorMessage(HttpServletResponse res, ErrorCode errorCode, String message) throws IOException {
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        res.setContentType(MediaType.APPLICATION_JSON.toString());
        res.getWriter().write(this.objectMapper
                .writeValueAsString(ErrorResponse.of(errorCode, message)));
    }
}
package com.yapp.yongyong.global.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yapp.yongyong.global.error.ErrorCode;
import com.yapp.yongyong.global.error.ErrorResponse;
import com.yapp.yongyong.global.error.GlobalErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        sendErrorMessage(response, GlobalErrorCode.OAUTH2_NOT_WORK, exception.getMessage());
    }

    private void sendErrorMessage(HttpServletResponse res, ErrorCode errorCode, String message) throws IOException {
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        res.setContentType(MediaType.APPLICATION_JSON.toString());
        res.getWriter().write(this.objectMapper
                .writeValueAsString(ErrorResponse.of(errorCode, message)));
    }
}

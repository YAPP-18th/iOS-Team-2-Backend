package com.yapp.yongyong.global.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yapp.yongyong.domain.user.dto.TokenDto;
import com.yapp.yongyong.global.entity.CommonApiResponse;
import com.yapp.yongyong.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String accessToken = tokenProvider.createAccessToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);
        response.getWriter().write(this.objectMapper.writeValueAsString(new CommonApiResponse<>(new TokenDto(accessToken,refreshToken))));
    }
}

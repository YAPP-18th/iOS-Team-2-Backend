package com.yapp.yongyong.domain.user.service;

import com.yapp.yongyong.domain.user.domain.Authority;
import com.yapp.yongyong.domain.user.domain.Role;
import com.yapp.yongyong.domain.user.domain.TermsOfService;
import com.yapp.yongyong.domain.user.domain.User;
import com.yapp.yongyong.domain.user.dto.LoginDto;
import com.yapp.yongyong.domain.user.dto.SignUpDto;
import com.yapp.yongyong.domain.user.dto.TokenDto;
import com.yapp.yongyong.domain.user.error.DuplicateRegisterException;
import com.yapp.yongyong.domain.user.repository.TermsOfServiceRepository;
import com.yapp.yongyong.domain.user.repository.UserRepository;
import com.yapp.yongyong.global.error.NotExistException;
import com.yapp.yongyong.global.jwt.TokenProvider;
import com.yapp.yongyong.infra.uploader.Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private static final String PROFILE = "profile";
    private static final String GENERAL = "GENERAL";

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final Uploader uploader;

    private final UserRepository userRepository;
    private final TermsOfServiceRepository termsOfServiceRepository;

    public void signUp(SignUpDto signUpDto) {
        checkEmailDuplicated(signUpDto.getEmail());

        Authority authority = new Authority(Role.USER.getName());

        User user = User.builder()
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .authorities(Collections.singleton(authority))
                .nickname(signUpDto.getNickname())
                .imageUrl(uploader.upload(signUpDto.getProfileImage(), PROFILE))
                .introduction(signUpDto.getIntroduction())
                .provider(GENERAL)
                .build();
        User savedUser = userRepository.save(user);

        TermsOfService terms = TermsOfService.builder()
                .location(signUpDto.isLocation())
                .service(signUpDto.isService())
                .privacy(signUpDto.isPrivacy())
                .marketing(signUpDto.isMarketing())
                .user(savedUser)
                .build();
        termsOfServiceRepository.save(terms);
    }

    public TokenDto login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        return new TokenDto(jwt);
    }

    public TokenDto loginByGuest(){
        return new TokenDto(tokenProvider.createTokenByGuest());
    }

    public void checkEmailDuplicated(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateRegisterException("이미 가입되어 있는 유저입니다.");
        }
    }

    public void existUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotExistException("존재하지 않는 유저입니다.");
        }
    }
}

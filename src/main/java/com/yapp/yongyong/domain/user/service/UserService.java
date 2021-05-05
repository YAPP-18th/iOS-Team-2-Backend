package com.yapp.yongyong.domain.user.service;

import com.yapp.yongyong.domain.user.domain.Authority;
import com.yapp.yongyong.domain.user.domain.Role;
import com.yapp.yongyong.domain.user.domain.User;
import com.yapp.yongyong.domain.user.dto.LoginDto;
import com.yapp.yongyong.domain.user.dto.TokenDto;
import com.yapp.yongyong.domain.user.error.DuplicateRegisterException;
import com.yapp.yongyong.domain.user.repository.UserRepository;
import com.yapp.yongyong.global.jwt.TokenProvider;
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
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(LoginDto loginDto){
        if(userRepository.existsByEmail(loginDto.getEmail())){
            throw new DuplicateRegisterException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = new Authority(Role.USER.getName());

        User user = User.builder()
                .email(loginDto.getEmail())
                .password(passwordEncoder.encode(loginDto.getPassword()))
                .authorities(Collections.singleton(authority))
                .build();
        userRepository.save(user);
    }

    public TokenDto login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        return new TokenDto(jwt);
    }
}

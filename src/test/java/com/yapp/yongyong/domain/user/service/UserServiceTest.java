package com.yapp.yongyong.domain.user.service;

import com.yapp.yongyong.domain.user.entity.Authority;
import com.yapp.yongyong.domain.user.entity.Role;
import com.yapp.yongyong.domain.user.entity.TermsOfService;
import com.yapp.yongyong.domain.user.entity.User;
import com.yapp.yongyong.domain.user.dto.SignUpDto;
import com.yapp.yongyong.domain.user.repository.TermsOfServiceRepository;
import com.yapp.yongyong.domain.user.repository.UserRepository;
import com.yapp.yongyong.infra.uploader.Uploader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TermsOfServiceRepository termsOfServiceRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Uploader uploader;

    @Test
    public void 회원가입테스트() {
        //given
        MockMultipartFile file = new MockMultipartFile("multipartFile", "test.txt", "text/plain", "hello file".getBytes());
        Authority authority = new Authority(Role.USER.getName());
        SignUpDto signUpDto = SignUpDto.builder()
                .email("test@naver.com")
                .password("test")
                .profileImage(file)
                .nickname("nickname")
                .introduction("hi it's test")
                .location(true)
                .service(true)
                .privacy(true)
                .marketing(true)
                .build();
        User user = User.builder()
                .email(signUpDto.getEmail())
                .password("test")
                .imageUrl("xxx.com/profile/test.txt")
                .introduction(signUpDto.getIntroduction())
                .nickname(signUpDto.getNickname())
                .authorities(Collections.singleton(authority))
                .provider("GENERAL")
                .build();
        TermsOfService terms = TermsOfService.builder()
                .location(signUpDto.isLocation())
                .service(signUpDto.isService())
                .privacy(signUpDto.isPrivacy())
                .marketing(signUpDto.isMarketing())
                .user(user)
                .build();
        ReflectionTestUtils.setField(user, "id", 1L);
        ReflectionTestUtils.setField(terms, "id", 1L);

        //mocking
        given(userRepository.save(any()))
                .willReturn(user);
        given(userRepository.findById(1L))
                .willReturn(Optional.ofNullable(user));
        given(termsOfServiceRepository.save(any()))
                .willReturn(terms);
        given(termsOfServiceRepository.findById(1L))
                .willReturn(Optional.ofNullable(terms));
        given(passwordEncoder.encode(any()))
                .willReturn("test");
        given(uploader.upload(any(), any()))
                .willReturn("xxx.com/profile/test.txt");

        //when
        userService.signUp(signUpDto);

        //then
        User findUser = userRepository.findById(1L).get();
        TermsOfService findTerms = termsOfServiceRepository.findById(1L).get();


        assertEquals(user.getEmail(), findUser.getEmail());
        assertEquals(user.getPassword(), findUser.getPassword());
        assertEquals(user.getImageUrl(), findUser.getImageUrl());
        assertEquals(terms.getService(), findTerms.getService());
    }
}
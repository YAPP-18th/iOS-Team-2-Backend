package com.yapp.yongyong.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class AppleSignUpDto {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String socialId;


    @NotNull
    private String nickname;


    @NotNull
    private boolean location;

    @NotNull
    private boolean service;

    @NotNull
    private boolean privacy;

    @NotNull
    private boolean marketing;
}

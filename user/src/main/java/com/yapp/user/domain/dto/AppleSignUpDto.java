package com.yapp.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class AppleSignUpDto {

    @Email
    private String email;

    @NotNull
    private String socialId;

    private String nickname;
    private boolean location;
    private boolean service;
    private boolean privacy;
    private boolean marketing;
}

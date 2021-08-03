package com.yapp.yongyong.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AppleLoginDto {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String socialId;
}

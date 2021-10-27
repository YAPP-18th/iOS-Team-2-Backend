package com.yapp.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
public class SignUpDto {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 5, max = 20)
    private String password;

    private MultipartFile profileImage;

    @NotNull
    private String nickname;

    private String introduction;

    @NotNull
    private boolean location;

    @NotNull
    private boolean service;

    @NotNull
    private boolean privacy;

    @NotNull
    private boolean marketing;
}

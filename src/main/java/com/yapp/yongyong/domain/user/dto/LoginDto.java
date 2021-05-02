package com.yapp.yongyong.domain.user.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class LoginDto {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 5, max = 20)
    private String password;
}

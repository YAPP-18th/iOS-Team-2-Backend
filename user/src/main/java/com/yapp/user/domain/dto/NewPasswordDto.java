package com.yapp.user.domain.dto;

import lombok.Data;

@Data
public class NewPasswordDto {
    private String email;
    private String code;
    private String password;
}

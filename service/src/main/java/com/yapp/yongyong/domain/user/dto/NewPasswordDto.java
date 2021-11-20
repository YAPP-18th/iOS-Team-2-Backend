package com.yapp.yongyong.domain.user.dto;

import lombok.Data;

@Data
public class NewPasswordDto {
    private String email;
    private String code;
    private String password;
}

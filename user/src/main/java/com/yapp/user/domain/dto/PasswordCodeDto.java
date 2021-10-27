package com.yapp.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordCodeDto {
    private String email;
    private String code;
}

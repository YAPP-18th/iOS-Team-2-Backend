package com.yapp.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ServiceUserDto {
    private Long userId;
    private String email;
    private Set<String> authorities;
    private String nickname;
    private Set<Long> blockUsers;
}
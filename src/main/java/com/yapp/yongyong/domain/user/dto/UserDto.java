package com.yapp.yongyong.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String nickname;
    private String email;
    private String imageUrl;
    private String introduction;
}

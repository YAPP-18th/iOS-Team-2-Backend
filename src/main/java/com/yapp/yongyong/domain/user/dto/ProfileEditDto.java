package com.yapp.yongyong.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEditDto {
    private Long id;
    private String nickname;
    private MultipartFile image;
    private String introduction;
}

package com.yapp.yongyong.domain.post.dto;

import com.yapp.yongyong.domain.user.dto.UserDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentResponseDto {
    private UserDto user;
    private Long commentId;
    private String content;
    private LocalDate createdDate;
}

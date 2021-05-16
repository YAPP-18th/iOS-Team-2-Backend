package com.yapp.yongyong.domain.post.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentResponseDto {
    private String nickname;
    private Long commentId;
    private String content;
    private LocalDate createdDate;
}

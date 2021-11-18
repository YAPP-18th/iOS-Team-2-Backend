package com.yapp.post.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentResponseDto {
    private Long userId;
    private Long commentId;
    private String content;
    private LocalDate createdDate;
}

package com.yapp.yongyong.domain.post.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommentRequestDto {
    @NotNull
    private String content;
}

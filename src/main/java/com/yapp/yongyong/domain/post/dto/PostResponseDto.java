package com.yapp.yongyong.domain.post.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PostResponseDto {
    private Long postId;
    private String writer;
    private String content;
    private Integer likeCount;
    private List<String> images;
    private List<PostContainerDto> postContainers;
    private LocalDate createdDate;
    private String reviewBadge;
}

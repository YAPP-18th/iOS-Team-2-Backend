package com.yapp.post.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PostResponseDto {
    private Long postId;
    private Long userId;
    private Integer likeCount;
    private List<String> images;
    private List<PostContainerDto> postContainers;
    private LocalDate createdDate;
    private String content;
    private String reviewBadge;
    private Integer commentCount;
    private String placeName;
    private String placeLocation;
    private String placeLongitude;
    private String placeLatitude;
    private Boolean isLikePressed;
}

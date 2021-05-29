package com.yapp.yongyong.domain.post.dto;

import com.yapp.yongyong.domain.user.dto.UserDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PostResponseDto {
    private Long postId;
    private UserDto user;
    private Integer likeCount;
    private List<String> images;
    private List<PostContainerDto> postContainers;
    private LocalDate createdDate;
    private String reviewBadge;
    private Integer commentCount;
    private String placeName;
    private String placeLocation;
    private Boolean isLikePressed;
}

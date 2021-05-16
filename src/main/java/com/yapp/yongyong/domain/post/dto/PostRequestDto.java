package com.yapp.yongyong.domain.post.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
public class PostRequestDto {
    private List<MultipartFile> postImages;
    private List<PostContainerDto> containers;
    private String content;
    private String reviewBadge;
    private String placeName;
    private String placeLocation;
}

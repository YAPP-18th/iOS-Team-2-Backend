package com.yapp.yongyong.domain.post.dto;

import lombok.Data;

@Data
public class PostContainerDto {
    private String food;
    private String containerName;
    private String containerSize;
    private Integer foodCount;
    private Integer containerCount;
}

package com.yapp.yongyong.domain.post.dto;

import lombok.Data;

@Data
public class ContainerDto {
    private String food;
    private Long containerId;
    private Integer foodCount;
    private Integer containerCount;
}

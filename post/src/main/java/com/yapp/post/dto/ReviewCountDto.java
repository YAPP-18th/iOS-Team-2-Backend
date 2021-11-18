package com.yapp.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewCountDto {
    private String name;
    private String location;
    private Integer reviewCount;
}

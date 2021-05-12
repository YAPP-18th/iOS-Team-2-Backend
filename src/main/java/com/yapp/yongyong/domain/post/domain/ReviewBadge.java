package com.yapp.yongyong.domain.post.domain;

import lombok.Getter;

@Getter
public enum ReviewBadge {
    DISCOUNT("용기 할인"),
    KINDNESS("친절한 사장님"),
    GENEROSITY("넉넉한 인심");

    private String message;

    ReviewBadge(String message) {
        this.message = message;
    }
}

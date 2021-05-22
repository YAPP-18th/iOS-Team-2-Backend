package com.yapp.yongyong.domain.post.domain;

import com.yapp.yongyong.global.error.NotExistException;

import java.util.Arrays;

public enum ContainerSize {
    L, M, S;

    public static String validateSize(String size) {
        return Arrays.stream(ContainerSize.values())
                .filter(containerSize -> containerSize.name().equals(size))
                .findAny()
                .orElseThrow(() -> new NotExistException("아직 지원하지 않는 사이즈입니다."))
                .name();
    }
}

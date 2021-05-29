package com.yapp.yongyong.domain.post.entity;

import com.yapp.yongyong.global.error.NotExistException;

import java.util.Arrays;

public enum ContainerName {
    AIRTIGHT("밀폐용기"),
    POT("냄비"),
    TUMBLER("텀블러"),
    INSULATION("보온도시락"),
    FLYING_PAN("프라이팬");

    private final String name;

    ContainerName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String validateNmae(String name) {
        return Arrays.stream(ContainerName.values())
                .filter(containerName -> containerName.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new NotExistException("아직 지원하지 않는 용기입니다."))
                .getName();
    }

}

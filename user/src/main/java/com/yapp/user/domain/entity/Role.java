package com.yapp.user.domain.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 회원"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String name;
    private final String description;
}

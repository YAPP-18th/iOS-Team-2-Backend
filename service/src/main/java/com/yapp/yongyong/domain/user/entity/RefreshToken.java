package com.yapp.yongyong.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@AllArgsConstructor
@RedisHash("refreshToken")
public class RefreshToken {
    @Id
    private Long id;
    private String token;
}

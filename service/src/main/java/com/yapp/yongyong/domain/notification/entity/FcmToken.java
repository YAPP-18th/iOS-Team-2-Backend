package com.yapp.yongyong.domain.notification.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@AllArgsConstructor
@RedisHash("FcmToken")
public class FcmToken {
    @Id
    private Long id;
    private String token;

    public void updateToken(String token) {
        this.token = token;
    }
}

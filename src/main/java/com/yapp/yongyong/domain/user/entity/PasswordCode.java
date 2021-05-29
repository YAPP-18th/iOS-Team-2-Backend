package com.yapp.yongyong.domain.user.entity;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@RedisHash("passwordCode")
public class PasswordCode {
    @Id
    private String id;
    private String code;
    private LocalDateTime refreshTime;

    public PasswordCode(String id) {
        this.id = id;
    }

    public void refresh(String code, LocalDateTime refreshTime){
        if(refreshTime.isAfter(this.refreshTime)){
            this.code = code;
            this.refreshTime = refreshTime;
        }
    }
}

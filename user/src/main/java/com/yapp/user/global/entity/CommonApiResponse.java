package com.yapp.user.global.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommonApiResponse<T> {
    private int count;
    private T data;

    public CommonApiResponse(T data) {
        this.data = data;
        if (data instanceof List) {
            this.count = ((List<?>) data).size();
        } else {
            this.count = 1;
        }
    }
}

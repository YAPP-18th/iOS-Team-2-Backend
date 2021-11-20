package com.yapp.post.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                if (methodKey.contains("getUserData")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()),
                            "유저 정보가 옳바르지 않습니다.");
                }
                break;
            case 404:
                break;
            default:
                return new Exception(response.reason());

        }
        return null;
    }
}

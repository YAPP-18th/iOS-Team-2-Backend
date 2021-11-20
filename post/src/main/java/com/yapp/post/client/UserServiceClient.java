package com.yapp.post.client;

import com.yapp.post.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="user-service")
public interface UserServiceClient {

    @GetMapping("/data")
    UserDto getUserData();
}

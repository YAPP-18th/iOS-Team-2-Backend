package com.yapp.yongyong.domain.notification.api;

import com.yapp.yongyong.domain.notification.entity.FcmToken;
import com.yapp.yongyong.domain.notification.repository.FcmTokenRepository;
import com.yapp.yongyong.domain.user.entity.User;
import com.yapp.yongyong.global.entity.BooleanResponse;
import com.yapp.yongyong.global.jwt.LoginUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class FcmController {

    private final FcmTokenRepository fcmTokenRepository;

    @ApiOperation(value = "fcm 토큰 받기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "true")
    })
    @PostMapping("/fcm")
    @PreAuthorize("hasAnyRole('USER')")
    public BooleanResponse postFcmToken(@LoginUser User user, @RequestBody Map<String, String> map) {
        fcmTokenRepository.save(new FcmToken(user.getId(), map.get("fcmToken")));
        return new BooleanResponse(true);
    }
}

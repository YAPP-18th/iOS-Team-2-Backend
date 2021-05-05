package com.yapp.yongyong.domain.user.api;


import com.yapp.yongyong.domain.user.dto.LoginDto;
import com.yapp.yongyong.domain.user.dto.TokenDto;
import com.yapp.yongyong.domain.user.service.UserService;
import com.yapp.yongyong.global.domain.CommonApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "회원가입")
    @ApiResponses({
            @ApiResponse(code = 201, message = "success")
    })
    @PostMapping("/sign-up")
    public ResponseEntity<Void> signup(
            @Valid @RequestBody LoginDto userDto
    ) {
        userService.signUp(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "로그인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success", response = TokenDto.class)
    })
    @PostMapping("/login")
    public ResponseEntity<CommonApiResponse> login(@Valid @RequestBody LoginDto loginDto) {
        TokenDto token = userService.login(loginDto);
        return ResponseEntity.ok(new CommonApiResponse<>(token));
    }
}

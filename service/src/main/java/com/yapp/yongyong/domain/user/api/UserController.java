package com.yapp.yongyong.domain.user.api;


import com.yapp.yongyong.domain.user.dto.*;
import com.yapp.yongyong.domain.user.entity.User;
import com.yapp.yongyong.domain.user.mapper.UserMapper;
import com.yapp.yongyong.domain.user.service.UserService;
import com.yapp.yongyong.global.entity.BooleanResponse;
import com.yapp.yongyong.global.entity.CommonApiResponse;
import com.yapp.yongyong.global.jwt.JwtFilter;
import com.yapp.yongyong.global.jwt.LoginUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
//@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private static final String AUTHORIZATION = "Authorization";

    @ApiOperation(value = "회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<Void> signup(@Valid SignUpDto signUpDto) {
        userService.signUp(signUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "로그인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success", response = TokenDto.class)
    })
    @PostMapping("/login")
    public ResponseEntity<CommonApiResponse> login(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(new CommonApiResponse<>(userService.login(loginDto)));
    }

    @ApiOperation(value = "애플 로그인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success", response = TokenDto.class)
    })
    @PostMapping("/login/apple")
    public ResponseEntity<CommonApiResponse> loginByApple(@Valid @RequestBody AppleSignUpDto signUpDto) {
        return ResponseEntity.ok(new CommonApiResponse<>(userService.signUpByApple(signUpDto)));
    }

    @ApiOperation(value = "비회원 로그인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success", response = TokenDto.class)
    })
    @PostMapping("/login/guest")
    public ResponseEntity<CommonApiResponse> loginByGuest() {
        return ResponseEntity.ok(new CommonApiResponse<>(userService.loginByGuest()));
    }

    @ApiOperation(value = "엑세스 토큰 발급")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success", response = TokenDto.class)
    })
    @PostMapping("/token/{userId}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<CommonApiResponse> getAccessToken(@PathVariable Long userId, @RequestHeader(AUTHORIZATION) String jwt) {
        return ResponseEntity.ok(new CommonApiResponse<>(userService.getAccessToken(userId, jwt.substring(7))));
    }

    @ApiOperation(value = "이메일 중복 체크")
    @GetMapping("/check/email")
    public ResponseEntity<Void> checkEmailDuplicated(@RequestParam String email) {
        userService.checkEmailDuplicated(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "닉네임 중복 체크")
    @GetMapping("/check/nickname")
    public ResponseEntity<Void> checkNicknameDuplicated(@RequestParam String nickname) {
        userService.checkNicknameDuplicated(nickname);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "회원탈퇴")
    @DeleteMapping("/withdrawal")
    public ResponseEntity<Void> withdraw(@LoginUser User user) {
        userService.withdraw(user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "프로필 편집")
    @PutMapping("/profile")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Void> editProfile(ProfileEditDto profileDto, @LoginUser User user) {
        userService.editProfile(profileDto, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "프로필 조회")
    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<CommonApiResponse> getProfile(@LoginUser User user) {
        return ResponseEntity.ok(new CommonApiResponse(UserMapper.INSTANCE.toDto(user)));
    }

    @ApiOperation(value = "비밀번호 찾기 이메일 발송")
    @GetMapping("/password/email")
    public ResponseEntity<Void> sendPasswordEmail(@RequestParam String email) {
        userService.sendPasswordEmail(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "비밀번호 인증번호 매치")
    @PostMapping("/password/email")
    public ResponseEntity<CommonApiResponse> matchPasswordCode(@RequestBody PasswordCodeDto passwordCodeDto) {
        return ResponseEntity.ok(new CommonApiResponse(new BooleanResponse(userService.matchPasswordCode(passwordCodeDto))));
    }

    @ApiOperation(value = "비밀번호 재설정")
    @PutMapping("/password")
    public ResponseEntity<Void> resetPassword(@RequestBody NewPasswordDto newPasswordDto) {
        userService.resetPassword(newPasswordDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "사용자 차단")
    @PostMapping("/block/{uid}")
    public ResponseEntity<Void> resetPassword(@LoginUser User user, @PathVariable(value = "uid") Long uid) {
        userService.blockUser(user, uid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

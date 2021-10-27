package com.yapp.user.domain.api;


import com.yapp.user.domain.dto.*;
import com.yapp.user.domain.entity.User;
import com.yapp.user.domain.mapper.UserMapper;
import com.yapp.user.domain.service.UserService;
import com.yapp.user.global.entity.BooleanResponse;
import com.yapp.user.global.entity.CommonApiResponse;
import com.yapp.user.global.jwt.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private static final String AUTHORIZATION = "Authorization";

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signup(@Valid SignUpDto signUpDto) {
        userService.signUp(signUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<CommonApiResponse> login(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(new CommonApiResponse<>(userService.login(loginDto)));
    }

    @PostMapping("/login/apple")
    public ResponseEntity<CommonApiResponse> loginByApple(@Valid @RequestBody AppleSignUpDto signUpDto) {
        return ResponseEntity.ok(new CommonApiResponse<>(userService.signUpByApple(signUpDto)));
    }

    @PostMapping("/login/guest")
    public ResponseEntity<CommonApiResponse> loginByGuest() {
        return ResponseEntity.ok(new CommonApiResponse<>(userService.loginByGuest()));
    }

    @PostMapping("/token/{userId}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<CommonApiResponse> getAccessToken(@PathVariable Long userId, @RequestHeader(AUTHORIZATION) String jwt) {
        return ResponseEntity.ok(new CommonApiResponse<>(userService.getAccessToken(userId, jwt.substring(7))));
    }

    @GetMapping("/check/email")
    public ResponseEntity<Void> checkEmailDuplicated(@RequestParam String email) {
        userService.checkEmailDuplicated(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/check/nickname")
    public ResponseEntity<Void> checkNicknameDuplicated(@RequestParam String nickname) {
        userService.checkNicknameDuplicated(nickname);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/withdrawal")
    public ResponseEntity<Void> withdraw(@LoginUser User user) {
        userService.withdraw(user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/profile")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Void> editProfile(ProfileEditDto profileDto, @LoginUser User user) {
        userService.editProfile(profileDto, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<CommonApiResponse> getProfile(@LoginUser User user) {
        return ResponseEntity.ok(new CommonApiResponse(UserMapper.INSTANCE.toDto(user)));
    }

    @GetMapping("/password/email")
    public ResponseEntity<Void> sendPasswordEmail(@RequestParam String email) {
        userService.sendPasswordEmail(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/password/email")
    public ResponseEntity<CommonApiResponse> matchPasswordCode(@RequestBody PasswordCodeDto passwordCodeDto) {
        return ResponseEntity.ok(new CommonApiResponse(new BooleanResponse(userService.matchPasswordCode(passwordCodeDto))));
    }

    @PutMapping("/password")
    public ResponseEntity<Void> resetPassword(@RequestBody NewPasswordDto newPasswordDto) {
        userService.resetPassword(newPasswordDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/block/{uid}")
    public ResponseEntity<Void> resetPassword(@LoginUser User user, @PathVariable(value = "uid") Long uid) {
        userService.blockUser(user, uid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

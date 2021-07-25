package com.yapp.yongyong.domain.user.service;

import com.yapp.yongyong.domain.post.repository.LikePostRepository;
import com.yapp.yongyong.domain.post.repository.PostRepository;
import com.yapp.yongyong.domain.user.dto.*;
import com.yapp.yongyong.domain.user.entity.*;
import com.yapp.yongyong.domain.user.error.DuplicateRegisterException;
import com.yapp.yongyong.domain.user.repository.PasswordCodeRepository;
import com.yapp.yongyong.domain.user.repository.RefreshTokenRepository;
import com.yapp.yongyong.domain.user.repository.TermsOfServiceRepository;
import com.yapp.yongyong.domain.user.repository.UserRepository;
import com.yapp.yongyong.global.error.BadRequestException;
import com.yapp.yongyong.global.error.NotExistException;
import com.yapp.yongyong.global.jwt.TokenProvider;
import com.yapp.yongyong.infra.email.EmailService;
import com.yapp.yongyong.infra.uploader.Uploader;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private static final String PROFILE = "profile";
    private static final String GENERAL = "GENERAL";
    private static final String SUBJECT = "[용기내용] 비밀번호 변경 인증번호";
    private static final String MESSAGE = "인증번호 [%s]를 입력해주세요.";
    private static final String EMPTY = "";


    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final Uploader uploader;
    private final EmailService emailService;

    private final UserRepository userRepository;
    private final TermsOfServiceRepository termsOfServiceRepository;
    private final PostRepository postRepository;
    private final LikePostRepository likePostRepository;
    private final PasswordCodeRepository passwordCodeRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public void signUp(SignUpDto signUpDto) {
        checkEmailDuplicated(signUpDto.getEmail());
        checkNicknameDuplicated(signUpDto.getNickname());

        Authority authority = new Authority(Role.USER.getName());

        User user = User.builder()
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .authorities(Collections.singleton(authority))
                .nickname(signUpDto.getNickname())
                .imageUrl(uploader.upload(signUpDto.getProfileImage(), PROFILE))
                .introduction(signUpDto.getIntroduction())
                .provider(GENERAL)
                .build();
        User savedUser = userRepository.save(user);

        TermsOfService terms = TermsOfService.builder()
                .location(signUpDto.isLocation())
                .service(signUpDto.isService())
                .privacy(signUpDto.isPrivacy())
                .marketing(signUpDto.isMarketing())
                .user(savedUser)
                .build();
        termsOfServiceRepository.save(terms);
    }

    public TokenDto login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = tokenProvider.createAccessToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);

        User user = userRepository.findOneWithAuthoritiesByEmail(loginDto.getEmail())
                .orElseThrow(() -> new NotExistException("존재하지 않는 유저입니다."));

        refreshTokenRepository.save(new RefreshToken(user.getId(), refreshToken));
        return new TokenDto(accessToken, refreshToken);
    }

    public TokenDto loginByGuest() {
        return new TokenDto(tokenProvider.createTokenByGuest(), EMPTY);
    }

    public TokenDto getAccessToken(Long userId, String refreshToken) {
        RefreshToken findRefreshToken = refreshTokenRepository.findById(userId)
                .orElseThrow(() -> new NotExistException("존재하지 않는 유저입니다."));
        tokenProvider.validateToken(findRefreshToken.getToken());

        if (!refreshToken.equals(findRefreshToken.getToken())) {
            throw new UnsupportedJwtException("옳바르지 않은 리프레쉬 토큰입니다.");
        }

        Authentication authentication = tokenProvider.getAuthentication(refreshToken);
        String accessToken = tokenProvider.createAccessToken(authentication);
        return new TokenDto(accessToken, findRefreshToken.getToken());
    }

    @Transactional(readOnly = true)
    public void checkEmailDuplicated(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateRegisterException("이미 가입되어 있는 유저입니다.");
        }
    }

    @Transactional(readOnly = true)
    public void checkNicknameDuplicated(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new DuplicateRegisterException("이미 가입되어 있는 닉네임입니다.");
        }
    }

    @Transactional(readOnly = true)
    public void existUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotExistException("존재하지 않는 유저입니다.");
        }
    }

    @Transactional(readOnly = true)
    public void existUser(String nickname) {
        if (!userRepository.existsByNickname(nickname)) {
            throw new NotExistException("존재하지 않는 유저입니다.");
        }
    }

    public void withdraw(User user) {
        existUser(user.getId());
        postRepository.deleteAllByUser(user);
        termsOfServiceRepository.deleteByUser(user);
        likePostRepository.deleteAllByUser(user);
        user.getAuthorities().clear();
        userRepository.delete(user);
    }

    public void editProfile(ProfileEditDto profileDto, User user) {
        existUser(user.getId());

        Optional<MultipartFile> image = Optional.ofNullable(profileDto.getImage());

        if (!user.getId().equals(profileDto.getId())) {
            throw new BadRequestException("본인 프로필만 수정할 수 있습니다.");
        }

        image.ifPresent(img -> user.updateImage(uploader.upload(img, PROFILE)));
        user.updateNameAndIntroduction(profileDto.getNickname(), profileDto.getIntroduction());
    }

    public void sendPasswordEmail(String email) {
        String code = RandomStringUtils.randomNumeric(6);

        emailService.sendMail(email, SUBJECT, String.format(MESSAGE, code));
        PasswordCode passwordCode = passwordCodeRepository.findById(email).orElse(new PasswordCode(email, code, LocalDateTime.now()));
        passwordCode.refresh(code, LocalDateTime.now());
        passwordCodeRepository.save(passwordCode);
    }

    public Boolean matchPasswordCode(PasswordCodeDto passwordCodeDto) {
        PasswordCode passwordCode = passwordCodeRepository.findById(passwordCodeDto.getEmail())
                .orElseThrow(() -> new NotExistException("인증 번호를 보낸 적 없는 이메일입니다."));
        return passwordCode.getCode().equals(passwordCodeDto.getCode());
    }

    public void resetPassword(NewPasswordDto newPasswordDto) {
        PasswordCodeDto passwordCodeDto = new PasswordCodeDto(newPasswordDto.getEmail(), newPasswordDto.getCode());
        if (matchPasswordCode(passwordCodeDto)) {
            User user = userRepository.findOneWithAuthoritiesByEmail(passwordCodeDto.getEmail())
                    .orElseThrow(() -> new NotExistException("존재하지 않는 유저입니다."));
            user.updatePassword(passwordEncoder.encode(newPasswordDto.getPassword()));
        }
    }
}

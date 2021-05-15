package com.yapp.yongyong.domain.post.api;

import com.yapp.yongyong.domain.post.domain.Post;
import com.yapp.yongyong.domain.post.dto.PostRequestDto;
import com.yapp.yongyong.domain.post.dto.PostResponseDto;
import com.yapp.yongyong.domain.post.service.PostService;
import com.yapp.yongyong.domain.user.domain.User;
import com.yapp.yongyong.global.domain.CommonApiResponse;
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

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @ApiOperation(value = "게시물 올리기")
    @ApiResponses({
            @ApiResponse(code = 201, message = "created")
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Void> addPost(PostRequestDto postRequestDto, @LoginUser User user) {
        Post post = postService.addPost(postRequestDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "가게별 게시물 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success", response = PostResponseDto.class, responseContainer = "List" )
    })
    @GetMapping("/place")
    @PreAuthorize("hasAnyRole('GUEST','USER')")
    public ResponseEntity<CommonApiResponse> getPostsByPlace(@RequestParam String name, @RequestParam String location) {
        List<PostResponseDto> postsByPlace = postService.getPostsByPlace(name, location);
        return ResponseEntity.ok(new CommonApiResponse(postsByPlace));
    }

}

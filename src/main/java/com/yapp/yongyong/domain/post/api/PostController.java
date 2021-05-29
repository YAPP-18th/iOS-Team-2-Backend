package com.yapp.yongyong.domain.post.api;

import com.yapp.yongyong.domain.post.entity.Post;
import com.yapp.yongyong.domain.post.dto.*;
import com.yapp.yongyong.domain.post.service.PostService;
import com.yapp.yongyong.domain.user.entity.User;
import com.yapp.yongyong.global.entity.CommonApiResponse;
import com.yapp.yongyong.global.jwt.LoginUser;
import io.swagger.annotations.*;
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
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @ApiOperation(value = "게시물 생성하기")
    @PostMapping
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Void> addPost(PostRequestDto postRequestDto, @LoginUser User user) {
        Post post = postService.addPost(postRequestDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "전체 게시물 조회하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success", response = PostResponseDto.class, responseContainer = "List")
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','GUEST')")
    public ResponseEntity<CommonApiResponse> getPosts(){
        return ResponseEntity.ok(new CommonApiResponse(postService.getPosts()));
    }

    @ApiOperation(value = "가게별 게시물 전체 조회하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success", response = PostResponseDto.class, responseContainer = "List")
    })
    @GetMapping("/place")
    @PreAuthorize("hasAnyRole('GUEST','USER')")
    public ResponseEntity<CommonApiResponse> getPostsByPlace(@RequestParam String name, @RequestParam String location) {
        return ResponseEntity.ok(new CommonApiResponse(postService.getPostsByPlace(name, location)));
    }

    @ApiOperation(value = "사용자 게시물 전체 조회하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success", response = PostResponseDto.class, responseContainer = "List")
    })
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('GUEST','USER')")
    public ResponseEntity<CommonApiResponse> getPostsByName(@RequestParam Long userId) {
        return ResponseEntity.ok(new CommonApiResponse(postService.getPostsByUser(userId)));
    }

    @ApiOperation(value = "내 게시물 전체 조회하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success", response = PostResponseDto.class, responseContainer = "List")
    })
    @GetMapping("/user/mine")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<CommonApiResponse> getPostsByName(@LoginUser User user, @RequestParam(required = false) Integer month) {
        return ResponseEntity.ok(new CommonApiResponse(postService.getPostsAtMyPage(user,month)));
    }

    @ApiOperation(value = "게시물 수정하기")
    @PutMapping("/{postId}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Void> editPost(@PathVariable Long postId, PostRequestDto postRequestDto, @LoginUser User user) {
        postService.editPost(postId, postRequestDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "게시물 삭제하기")
    @DeleteMapping("/{postId}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, @LoginUser User user) {
        postService.deletePost(postId, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "댓글 생성하기")
    @PostMapping("/{postId}/comment")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Void> addComment(@PathVariable Long postId,
                                           @RequestBody @Valid CommentRequestDto commentRequestDto,
                                           @LoginUser User user) {
        postService.addComment(postId, commentRequestDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "댓글 수정하기")
    @PutMapping("/{postId}/comment/{commentId}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Void> editComment(@PathVariable Long postId, @PathVariable Long commentId,
                                            @RequestBody @Valid CommentEditRequestDto commentEditRequestDto,
                                            @LoginUser User user) {
        postService.editComment(postId, commentId, commentEditRequestDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "댓글 삭제하기")
    @DeleteMapping("/{postId}/comment/{commentId}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Void> deleteComment(@PathVariable Long postId, @PathVariable Long commentId,
                                              @LoginUser User user) {
        postService.deleteComment(postId, commentId, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "댓글들 보기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success", response = CommentResponseDto.class, responseContainer = "List")
    })
    @GetMapping("/{postId}/comment")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<CommonApiResponse> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(new CommonApiResponse(postService.getComments(postId)));
    }
}

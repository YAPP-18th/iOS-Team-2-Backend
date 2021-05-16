package com.yapp.yongyong.domain.post.api;

import com.yapp.yongyong.domain.post.domain.Post;
import com.yapp.yongyong.domain.post.dto.CommentEditRequestDto;
import com.yapp.yongyong.domain.post.dto.CommentRequestDto;
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

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @ApiOperation(value = "게시물 올리기")
    @PostMapping
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Void> addPost(PostRequestDto postRequestDto, @LoginUser User user) {
        Post post = postService.addPost(postRequestDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "가게별 게시물 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success", response = PostResponseDto.class, responseContainer = "List")
    })
    @GetMapping("/place")
    @PreAuthorize("hasAnyRole('GUEST','USER')")
    public ResponseEntity<CommonApiResponse> getPostsByPlace(@RequestParam String name, @RequestParam String location) {
        List<PostResponseDto> postsByPlace = postService.getPostsByPlace(name, location);
        return ResponseEntity.ok(new CommonApiResponse(postsByPlace));
    }

    @ApiOperation(value = "댓글 올리기")
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
    public ResponseEntity<Void> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        postService.deleteComment(postId, commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "댓글들 보기")
    @GetMapping("/{postId}/comment")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<CommonApiResponse> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(new CommonApiResponse(postService.getComments(postId)));
    }
}

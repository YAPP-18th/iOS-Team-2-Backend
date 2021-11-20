package com.yapp.post.api;

import com.yapp.post.dto.CommentEditRequestDto;
import com.yapp.post.dto.CommentRequestDto;
import com.yapp.post.dto.PostRequestDto;
import com.yapp.post.global.entity.CommonApiResponse;
import com.yapp.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> addPost(PostRequestDto postRequestDto) {
        postService.addPost(postRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<CommonApiResponse> getPost(@PathVariable(value = "postId") Long postId) {
        return ResponseEntity.ok(new CommonApiResponse(postService.getPost(postId)));
    }

    @GetMapping
    public ResponseEntity<CommonApiResponse> getPosts() {
        return ResponseEntity.ok(new CommonApiResponse(postService.getPosts()));
    }

    @GetMapping("/place")
    public ResponseEntity<CommonApiResponse> getPostsByPlace(@RequestParam String name, @RequestParam String location) {
        log.info("location {}", location);
        return ResponseEntity.ok(new CommonApiResponse(postService.getPostsByPlace(name, location)));
    }

    @GetMapping("/user")
    public ResponseEntity<CommonApiResponse> getPostsByName(@RequestParam Long userId) {
        return ResponseEntity.ok(new CommonApiResponse(postService.getPostsByUser(userId)));
    }

    @GetMapping("/user/mine")
    public ResponseEntity<CommonApiResponse> getPostsByName(@RequestParam(required = false) Integer month) {
        return ResponseEntity.ok(new CommonApiResponse(postService.getPostsAtMyPage(month)));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{postId}/comment")
    public ResponseEntity<Void> addComment(@PathVariable Long postId,
                                           @RequestBody @Valid CommentRequestDto commentRequestDto
    ) {
        postService.addComment(postId, commentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<Void> editComment(@PathVariable Long postId, @PathVariable Long commentId,
                                            @RequestBody @Valid CommentEditRequestDto commentEditRequestDto
                                            ) {
        postService.editComment(postId, commentId, commentEditRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        postService.deleteComment(postId, commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{postId}/comment")
    public ResponseEntity<CommonApiResponse> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(new CommonApiResponse(postService.getComments(postId)));
    }

    @PutMapping("/{postId}/like")
    public ResponseEntity<Void> likeOrUnlikePost(@PathVariable Long postId) {
        postService.likeOrUnLikePost(postId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/{postId}/report")
    public ResponseEntity<Void> reportPost(@PathVariable Long postId, @RequestParam(value = "reportNumber") Long reportNumber) {
        postService.reportPost(postId, reportNumber);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}

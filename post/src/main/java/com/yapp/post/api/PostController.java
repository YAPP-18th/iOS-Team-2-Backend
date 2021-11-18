package com.yapp.post.api;

import com.yapp.post.dto.*;
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
//    private final PostService postService;
//
//    @PostMapping
//    public ResponseEntity<Void> addPost(PostRequestDto postRequestDto, Long userId) {
//        postService.addPost(postRequestDto, userId);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @GetMapping("/{postId}")
//    public ResponseEntity<CommonApiResponse> getPost(@PathVariable(value = "postId") Long postId) {
//        return ResponseEntity.ok(new CommonApiResponse(postService.getPost(postId)));
//    }
//
//    @GetMapping
//    public ResponseEntity<CommonApiResponse> getPosts() {
//        return ResponseEntity.ok(new CommonApiResponse(postService.getPosts()));
//    }
//
//    @GetMapping("/place")
//    public ResponseEntity<CommonApiResponse> getPostsByPlace(@RequestParam String name, @RequestParam String location) {
//        log.info("location {}", location);
//        return ResponseEntity.ok(new CommonApiResponse(postService.getPostsByPlace(name, location)));
//    }
//
//    @GetMapping("/user")
//    public ResponseEntity<CommonApiResponse> getPostsByName(@RequestParam Long userId) {
//        return ResponseEntity.ok(new CommonApiResponse(postService.getPostsByUser(userId)));
//    }
//
//    @GetMapping("/user/mine")
//    public ResponseEntity<CommonApiResponse> getPostsByName(Long userId, @RequestParam(required = false) Integer month) {
//        return ResponseEntity.ok(new CommonApiResponse(postService.getPostsAtMyPage(userId, month)));
//    }
//
//    @PutMapping("/{postId}")
//    public ResponseEntity<Void> editPost(@PathVariable Long postId, PostRequestDto postRequestDto,
//                                         Long userId) {
//        postService.editPost(postId, postRequestDto, userId);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @DeleteMapping("/{postId}")
//    public ResponseEntity<Void> deletePost(@PathVariable Long postId, Long userId) {
//        postService.deletePost(postId, userId);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//
//    @PostMapping("/{postId}/comment")
//    public ResponseEntity<Void> addComment(@PathVariable Long postId,
//                                           @RequestBody @Valid CommentRequestDto commentRequestDto,
//                                           Long userId) {
//        postService.addComment(postId, commentRequestDto, userId);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @PutMapping("/{postId}/comment/{commentId}")
//    public ResponseEntity<Void> editComment(@PathVariable Long postId, @PathVariable Long commentId,
//                                            @RequestBody @Valid CommentEditRequestDto commentEditRequestDto,
//                                            Long userId) {
//        postService.editComment(postId, commentId, commentEditRequestDto, userId);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @DeleteMapping("/{postId}/comment/{commentId}")
//    public ResponseEntity<Void> deleteComment(@PathVariable Long postId, @PathVariable Long commentId,
//                                              Long userId) {
//        postService.deleteComment(postId, commentId, userId);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//
//    @GetMapping("/{postId}/comment")
//    public ResponseEntity<CommonApiResponse> getComments(@PathVariable Long postId) {
//        return ResponseEntity.ok(new CommonApiResponse(postService.getComments(postId)));
//    }
//
//    @PutMapping("/{postId}/like")
//    public ResponseEntity<Void> likeOrUnlikePost(@PathVariable Long postId, Long userId) {
//        postService.likeOrUnLikePost(postId, userId);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
//    }
//
//    @PostMapping("/{postId}/report")
//    public ResponseEntity<Void> reportPost(@PathVariable Long postId, @RequestParam(value = "reportNumber") Long reportNumber) {
//        postService.reportPost(postId, reportNumber);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
//    }
}

package com.yapp.yongyong.domain.post.service;

import com.yapp.yongyong.domain.post.entity.*;
import com.yapp.yongyong.domain.post.dto.*;
import com.yapp.yongyong.domain.post.mapper.CommentMapper;
import com.yapp.yongyong.domain.post.mapper.PostMapper;
import com.yapp.yongyong.domain.post.repository.*;
import com.yapp.yongyong.domain.user.entity.User;
import com.yapp.yongyong.domain.user.service.UserService;
import com.yapp.yongyong.global.error.NotDataEqualsException;
import com.yapp.yongyong.global.error.NotExistException;
import com.yapp.yongyong.infra.uploader.Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PostService {
    private static final String POST = "post";

    private final UserService userService;
    private final PostRepository postRepository;
    private final PlaceRepository placeRepository;
    private final PostContainerRepository postContainerRepository;
    private final PostImageRepository postImageRepository;
    private final CommentRepository commentRepository;
    private final Uploader uploader;

    public Post addPost(PostRequestDto postRequestDto, User user) {
        Place findPlace = placeRepository.findByNameAndLocation(postRequestDto.getPlaceName(), postRequestDto.getPlaceLocation()).orElseGet(
                () -> {
                    Place newPlace = new Place(postRequestDto.getPlaceName(), postRequestDto.getPlaceLocation());
                    return placeRepository.save(newPlace);
                });
        findPlace.addReviewCount();
        Post savePost = postRepository.save(PostMapper.INSTANCE.toEntity(postRequestDto, findPlace, user));
        addPostImages(postRequestDto, savePost);
        addPostContainers(postRequestDto, savePost);
        return savePost;
    }

    private void addPostContainers(PostRequestDto postRequestDto, Post savePost) {
        postRequestDto.getContainers().forEach(
                containerDto -> postContainerRepository.save(PostMapper.INSTANCE.toEntity(containerDto, savePost)));
    }

    private void addPostImages(PostRequestDto postRequestDto, Post savePost) {
        postRequestDto.getPostImages().forEach(
                image -> {
                    if (!image.isEmpty()) {
                        postImageRepository.save(new PostImage(uploader.upload(image, POST), savePost));
                    }
                }
        );
    }

    public List<PostResponseDto> getPostsByPlace(String name, String location) {
        Optional<Place> findPlace = placeRepository.findByNameAndLocation(name, location);
        if (findPlace.isEmpty()) {
            return Arrays.asList();
        }

        return postRepository.findAllByPlace(findPlace.get()).stream()
                .map(PostMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public List<PostResponseDto> getPostsByUser(Long userId) {
        userService.existUser(userId);

        return postRepository.findAllByUser_Id(userId).stream()
                .map(PostMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public void editPost(Long postId, PostRequestDto postRequestDto, User user) {
        Post post = existPost(postId);
    }

    public void deletePost(Long postId, User user) {
        Post post = existPost(postId);
        if (!post.getUser().equals(user)) {
            throw new NotDataEqualsException("본인 게시물만 삭제할 수 있습니다.");
        }
        postRepository.delete(post);
    }

    private Post existPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotExistException("존재 하지 않는 게시물입니다."));
    }

    public void addComment(Long postId, CommentRequestDto commentRequestDto, User user) {
        Post post = existPost(postId);
        userService.existUser(user.getId());
        Comment saveComment = commentRepository.save(new Comment(commentRequestDto.getContent(), user, post));
        post.getComments().add(saveComment);
    }

    public void editComment(Long postId, Long commentId, CommentEditRequestDto dto, User user) {
        Post post = existPost(postId);
        userService.existUser(user.getId());
        Comment findComment = getCommentById(commentId);
        if (!findComment.getUser().equals(user)) {
            throw new NotDataEqualsException("본인 게시물만 수정할 수 있습니다.");
        }
        findComment.update(dto.getContent());
    }

    public List<CommentResponseDto> getComments(Long postId) {
        return commentRepository.findAllByPostId(postId).stream()
                .map(CommentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public void deleteComment(Long postId, Long commentId, User user) {
        Post post = existPost(postId);
        Comment findComment = getCommentById(commentId);
        if (!findComment.getUser().equals(user)) {
            throw new NotDataEqualsException("본인 댓글만 수정할 수 있습니다.");
        }
        post.getComments().remove(findComment);
        commentRepository.delete(findComment);
    }

    private Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new NotExistException("존재 하지 않는 댓글입니다."));
    }
}

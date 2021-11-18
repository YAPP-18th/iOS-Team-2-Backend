package com.yapp.post.service;


import com.yapp.post.entity.*;
import com.yapp.post.dto.*;
import com.yapp.post.infra.uploader.Uploader;
import com.yapp.post.mapper.CommentMapper;
import com.yapp.post.mapper.PostMapper;
import com.yapp.post.repository.*;
import com.yapp.post.global.error.NotDataEqualsException;
import com.yapp.post.global.error.NotExistException;
import com.yapp.post.infra.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PostService {
//    private static final String POST = "post";
//    private static final String GUEST = "손님";
//
//
//    private final PostRepository postRepository;
//    private final PlaceRepository placeRepository;
//    private final PostContainerRepository postContainerRepository;
//    private final PostImageRepository postImageRepository;
//    private final CommentRepository commentRepository;
//    private final LikePostRepository likePostRepository;
//    private final Uploader uploader;
//    private final EmailService emailService;
//
//    public void addPost(PostRequestDto postRequestDto, Long userId) {
//        Place findPlace = placeRepository.findByNameAndLocation(postRequestDto.getPlaceName(), postRequestDto.getPlaceLocation()).orElseGet(
//                () -> {
//                    Place newPlace = Place.builder()
//                            .name(postRequestDto.getPlaceName())
//                            .location(postRequestDto.getPlaceLocation())
//                            .longitude(postRequestDto.getPlaceLongitude())
//                            .latitude(postRequestDto.getPlaceLatitude())
//                            .build();
//                    return placeRepository.save(newPlace);
//                });
//        findPlace.addReviewCount();
//        Post savePost = postRepository.save(PostMapper.INSTANCE.toEntity(postRequestDto, findPlace, userId));
//        addPostImages(postRequestDto, savePost);
//        addPostContainers(postRequestDto, savePost);
//    }
//
//    private void addPostContainers(PostRequestDto postRequestDto, Post savePost) {
//        postRequestDto.getContainers().forEach(
//                containerDto -> postContainerRepository.save(PostMapper.INSTANCE.toEntity(containerDto, savePost)));
//    }
//
//    private void addPostImages(PostRequestDto postRequestDto, Post savePost) {
//        postRequestDto.getPostImages().forEach(
//                image -> {
//                    if (!image.isEmpty()) {
//                        postImageRepository.save(new PostImage(uploader.upload(image, POST + "/" + savePost.getId()), savePost));
//                    }
//                }
//        );
//    }
//
//    public List<PostResponseDto> getPost(Long postId) {
//        Post post = existPost(postId);
//        return getPostResponseDtos(Arrays.asList(post));
//    }
//
//    public List<PostResponseDto> getPosts() {
//        List<Post> findPosts = postRepository.findAll();
//        return getPostResponseDtos(findPosts);
//    }
//
//    public List<PostResponseDto> getPostsByPlace(String name, String location) {
//        Optional<Place> findPlace = placeRepository.findByNameAndLocation(name, location);
//        if (findPlace.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        List<Post> findPosts = postRepository.findAllByPlace(findPlace.get());
//        return getPostResponseDtos(findPosts);
//    }
//
//    private List<PostResponseDto> getPostResponseDtos(List<Post> findPosts) {
////        String email = SecurityUtil.getCurrentUsername().orElse(GUEST);
//
//        if (email.equals(GUEST)) {
//            return findPosts.stream()
//                    .map(PostMapper.INSTANCE::toDtoForGuest)
//                    .collect(Collectors.toList());
//        }
//
//        Set<User> blocks = userService.getUser(email)
//                .getBlockUsers()
//                .stream()
//                .map(BlockUser::getTo).collect(Collectors.toSet());
//
//        return findPosts.stream()
//                .filter(post -> !blocks.contains(post.getUser()))
//                .map(post -> PostMapper.INSTANCE.toDto(post, email))
//                .collect(Collectors.toList());
//    }
//
//    public List<PostResponseDto> getPostsByUser(Long userId) {
//        userService.existUser(userId);
//        List<Post> findPosts = postRepository.findAllByUser_Id(userId);
//        return getPostResponseDtos(findPosts);
//    }
//
//    public List<PostResponseDto> getPostsAtMyPage(User user, Integer month) {
//        userService.existUser(user.getId());
//        return postRepository.getPostByMonth(user, month).stream()
//                .map(post -> PostMapper.INSTANCE.toDto(post, user.getEmail()))
//                .collect(Collectors.toList());
//    }
//
//    public void editPost(Long postId, PostRequestDto postRequestDto, User user) {
//        Post post = existPost(postId);
//    }
//
//    public void deletePost(Long postId, User user) {
//        Post post = existPost(postId);
//        if (!post.getUser().equals(user)) {
//            throw new NotDataEqualsException("본인 게시물만 삭제할 수 있습니다.");
//        }
//        post.getPlace().subReviewCount();
//        postRepository.delete(post);
//    }
//
//    private Post existPost(Long postId) {
//        return postRepository.findById(postId)
//                .orElseThrow(() -> new NotExistException("존재 하지 않는 게시물입니다."));
//    }
//
//    public void addComment(Long postId, CommentRequestDto commentRequestDto, User user) {
//        Post post = existPost(postId);
//        userService.existUser(user.getId());
//        Comment saveComment = commentRepository.save(new Comment(commentRequestDto.getContent(), user, post));
//        post.getComments().add(saveComment);
//        User writer = post.getUser();
//        Optional<FcmToken> findToken = fcmTokenRepository.findById(writer.getId());
//        findToken.ifPresent(token -> {
//            try {
//                fcmService.sendMessageTo(token.getToken(), user.getNickname() + "님이 회원님의 글에 댓글을 남겼습니다.", "/post/" + postId, user.getImageUrl());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    public void editComment(Long postId, Long commentId, CommentEditRequestDto dto, Long userId) {
//        Post post = existPost(postId);
//        userService.existUser(user.getId());
//        Comment findComment = getCommentById(commentId);
//        if (!findComment.getUser().equals(user)) {
//            throw new NotDataEqualsException("본인 게시물만 수정할 수 있습니다.");
//        }
//        findComment.update(dto.getContent());
//    }
//
//    public List<CommentResponseDto> getComments(Long postId) {
//        return commentRepository.findAllByPostId(postId).stream()
//                .map(CommentMapper.INSTANCE::toDto)
//                .collect(Collectors.toList());
//    }
//
//    public void deleteComment(Long postId, Long commentId, User user) {
//        Post post = existPost(postId);
//        Comment findComment = getCommentById(commentId);
//        if (!findComment.getUser().equals(user)) {
//            throw new NotDataEqualsException("본인 댓글만 수정할 수 있습니다.");
//        }
//        post.getComments().remove(findComment);
//        commentRepository.delete(findComment);
//    }
//
//    private Comment getCommentById(Long commentId) {
//        return commentRepository.findById(commentId)
//                .orElseThrow(() -> new NotExistException("존재 하지 않는 댓글입니다."));
//    }
//
//    public void likeOrUnLikePost(Long postId, Long userId) {
//        Post post = postRepository.findById(postId).orElseThrow(() -> new NotExistException("존재 하지 않는 게시물입니다."));
//        if (likePostRepository.existsByUserAndPost(userId, post)) {
//            likePostRepository.deleteByUserAndPost(userId, post);
//            return;
//        }
//        likePostRepository.save(new LikePost(useId, post));
//        User writer = post.getUser();
//        Optional<FcmToken> findToken = fcmTokenRepository.findById(writer.getId());
//        findToken.ifPresent(token -> {
//            try {
//                fcmService.sendMessageTo(token.getToken(), user.getNickname() + "님이 회원님의 글에 좋아요를 눌렀습니다.", "/post/" + postId, user.getImageUrl());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    public void reportPost(Long postId, Long reportNumber) {
//        existPost(postId);
//        emailService.sendMailToAmdin("게시물 신고", "게시물 번호: " + postId + "\n 신고 이유: " + reportNumber);
//    }
}

package com.yapp.yongyong.domain.post.service;

import com.yapp.yongyong.domain.post.domain.*;
import com.yapp.yongyong.domain.post.dto.PostRequestDto;
import com.yapp.yongyong.domain.post.dto.PostResponseDto;
import com.yapp.yongyong.domain.post.mapper.PostMapper;
import com.yapp.yongyong.domain.post.repository.*;
import com.yapp.yongyong.domain.user.domain.User;
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

    private final PostRepository postRepository;
    private final PlaceRepository placeRepository;
    private final PostContainerRepository postContainerRepository;
    private final ContainerRepository containerRepository;
    private final PostImageRepository postImageRepository;
    private final Uploader uploader;

    public Post addPost(PostRequestDto postRequestDto, User user) {
        Place findPlace = placeRepository.findByNameAndLocation(postRequestDto.getPlaceName(), postRequestDto.getPlaceLocation())
                .orElseGet(() -> {
                    Place newPlace = new Place(postRequestDto.getPlaceName(), postRequestDto.getPlaceLocation());
                    return placeRepository.save(newPlace);
                });
        findPlace.addReviewCount();
        Post savePost = postRepository.save(PostMapper.INSTANCE.toEntity(postRequestDto, findPlace, user));
        postRequestDto.getPostImages()
                .forEach(image -> {
                    PostImage savePostImage = postImageRepository.save(new PostImage(uploader.upload(image, POST), savePost));
                    savePost.getPostImages().add(savePostImage);
                });
        postRequestDto.getContainers()
                .forEach(containerDto -> {
                    Container findContainer = containerRepository.findById(containerDto.getContainerId()).orElse(new Container());
                    PostContainer postContainer = postContainerRepository.save(PostMapper.INSTANCE.toEntity(containerDto, findContainer, savePost));
                });
        return savePost;
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
}

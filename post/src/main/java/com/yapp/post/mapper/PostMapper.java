package com.yapp.post.mapper;

import com.yapp.post.entity.*;
import com.yapp.post.dto.ContainerDto;
import com.yapp.post.dto.PostContainerDto;
import com.yapp.post.dto.PostRequestDto;
import com.yapp.post.dto.PostResponseDto;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.factory.Mappers;

import java.util.Comparator;
import java.util.stream.Collectors;

@Mapper(imports = {Comparator.class, Collectors.class})
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "id", target = "postId")
    @Mapping(source = "post.postContainers", target = "postContainers")
    @Mapping(source = "post.place.name", target = "placeName")
    @Mapping(source = "post.place.location", target = "placeLocation")
    @Mapping(source = "post.place.latitude", target = "placeLatitude")
    @Mapping(source = "post.place.longitude", target = "placeLongitude")
    @Mapping(source = "post.content", target = "content")
    @Mapping(expression = "java(post.getPostImages().stream().sorted(Comparator.comparing(PostImage::getId)).map(PostImage::getImageUrl).collect(Collectors.toList()))", target = "images")
    @Mapping(expression = "java(post.getCreatedDate().toLocalDate())", target = "createdDate")
    @Mapping(expression = "java(post.getComments().size())", target = "commentCount")
    @Mapping(expression = "java(post.getLikePosts().size())", target = "likeCount")
    @Mapping(expression = "java(false)", target = "isLikePressed")
    PostResponseDto toDtoForGuest(Post post);

    @Mapping(source = "post.id", target = "postId")
    @Mapping(source = "post.postContainers", target = "postContainers")
    @Mapping(source = "post.place.name", target = "placeName")
    @Mapping(source = "post.place.location", target = "placeLocation")
    @Mapping(source = "post.place.latitude", target = "placeLatitude")
    @Mapping(source = "post.place.longitude", target = "placeLongitude")
    @Mapping(source = "post.content", target = "content")
    @Mapping(expression = "java(post.getPostImages().stream().sorted(Comparator.comparing(PostImage::getId)).map(PostImage::getImageUrl).collect(Collectors.toList()))", target = "images")
    @Mapping(expression = "java(post.getCreatedDate().toLocalDate())", target = "createdDate")
    @Mapping(expression = "java(post.getComments().size())", target = "commentCount")
    @Mapping(expression = "java(post.getLikePosts().size())", target = "likeCount")
    @Mapping(expression = "java(post.getLikePosts().stream().anyMatch(likePost -> likePost.getUser().getEmail().equals(email)))", target = "isLikePressed")
    PostResponseDto toDto(Post post, String email);

    @Mapping(source = "dto.content", target = "content")
    Post toEntity(PostRequestDto dto, Place place, Long userId);

    @Mapping(source = "post", target = "post")
    @Mapping(expression = "java(toContainerName(dto.getContainer()))", target = "containerName")
    @Mapping(expression = "java(toContainerSize(dto.getContainer()))", target = "containerSize")
    @Mapping(source = "dto.containerCount", target = "containerCount")
    @Mapping(source = "dto.foodCount", target = "foodCount")
    @Mapping(source = "dto.food", target = "food")
    PostContainer toEntity(PostContainerDto dto, Post post);

    @Mapping(source = "postContainer.containerName", target = "container.name")
    @Mapping(source = "postContainer.containerSize", target = "container.size")
    PostContainerDto toDto(PostContainer postContainer);

    default String toContainerSize(ContainerDto containerDto) {
        return ContainerSize.validateSize(containerDto.getSize());
    }

    default String toContainerName(ContainerDto containerDto) {
        return ContainerName.validateNmae(containerDto.getName());
    }

    default String toImage(PostImage postImage) {
        return postImage.getImageUrl();
    }

}

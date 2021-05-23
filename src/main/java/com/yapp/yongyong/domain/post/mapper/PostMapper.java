package com.yapp.yongyong.domain.post.mapper;

import com.yapp.yongyong.domain.post.domain.*;
import com.yapp.yongyong.domain.post.dto.ContainerDto;
import com.yapp.yongyong.domain.post.dto.PostContainerDto;
import com.yapp.yongyong.domain.post.dto.PostRequestDto;
import com.yapp.yongyong.domain.post.dto.PostResponseDto;
import com.yapp.yongyong.domain.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "id", target = "postId")
    @Mapping(source = "user.nickname", target = "writer")
    @Mapping(source = "post.postImages", target = "images")
    @Mapping(source = "post.postContainers", target = "postContainers")
    @Mapping(source = "post.place.name", target = "placeName")
    @Mapping(source = "post.place.location", target = "placeLocation")
    @Mapping(expression = "java(post.getCreatedDate().toLocalDate())", target = "createdDate")
    @Mapping(expression = "java(post.getComments().size())", target = "commentCount")
    PostResponseDto toDto(Post post);

    @Mapping(source = "dto.content", target = "content")
    Post toEntity(PostRequestDto dto, Place place, User user);

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

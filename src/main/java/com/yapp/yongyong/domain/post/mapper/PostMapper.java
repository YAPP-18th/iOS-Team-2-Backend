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

import java.util.List;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "user.nickname", target = "writer")
    @Mapping(source = "post.postImages", target = "images")
    @Mapping(expression = "java(post.getCreatedDate().toLocalDate())", target = "createdDate")
    PostResponseDto toDto(Post post);

    @Mapping(source = "dto.content", target = "content")
    Post toEntity(PostRequestDto dto, Place place, User user);

    @Mapping(target = "id",ignore = true)
    @Mapping(source = "dto.containerCount", target = "containerCount")
    @Mapping(source = "dto.foodCount", target = "foodCount")
    @Mapping(source = "dto.food", target = "food")
    PostContainer toEntity(ContainerDto dto, Container container, Post post);

    List<PostContainerDto> convert(List<PostContainer> postContainers);

    @Mapping(source = "container.name", target = "containerName")
    @Mapping(source = "container.size", target = "containerSize")
    PostContainerDto convert(PostContainer postContainer);

    List<String> toImageList(List<PostImage> postImages);

    default String toImage(PostImage postImage) {
        return postImage.getImageUrl();
    }


}

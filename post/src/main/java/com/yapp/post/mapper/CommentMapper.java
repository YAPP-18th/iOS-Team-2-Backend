package com.yapp.post.mapper;

import com.yapp.post.dto.CommentResponseDto;
import com.yapp.post.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "id", target = "commentId")
    @Mapping(expression = "java(comment.getCreatedDate().toLocalDate())", target = "createdDate")
    CommentResponseDto toDto(Comment comment);
}
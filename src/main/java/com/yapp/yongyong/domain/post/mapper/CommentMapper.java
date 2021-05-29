package com.yapp.yongyong.domain.post.mapper;

import com.yapp.yongyong.domain.post.entity.Comment;
import com.yapp.yongyong.domain.post.dto.CommentResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "comment.user.nickname",target = "nickname")
    @Mapping(source = "id",target = "commentId")
    @Mapping(expression = "java(comment.getCreatedDate().toLocalDate())", target = "createdDate")
    CommentResponseDto toDto(Comment comment);
}

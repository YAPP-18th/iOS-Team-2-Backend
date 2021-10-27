package com.yapp.user.domain.mapper;


import com.yapp.user.domain.dto.UserDto;
import com.yapp.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.nickname", target = "nickname")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.imageUrl", target = "imageUrl")
    @Mapping(source = "user.introduction", target = "introduction")
    UserDto toDto(User user);
}

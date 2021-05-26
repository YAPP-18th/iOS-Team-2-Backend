package com.yapp.yongyong.domain.user.mapper;

import com.yapp.yongyong.domain.user.dto.UserDto;
import com.yapp.yongyong.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.nickname",target = "nickname")
    @Mapping(source = "user.email",target = "email")
    @Mapping(source = "user.imageUrl",target = "imageUrl")
    @Mapping(source = "user.introduction",target = "introduction")
    UserDto toDto(User user);
}

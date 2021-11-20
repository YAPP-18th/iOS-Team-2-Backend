package com.yapp.user.domain.mapper;


import com.yapp.user.domain.dto.ServiceUserDto;
import com.yapp.user.domain.dto.UserDto;
import com.yapp.user.domain.entity.Authority;
import com.yapp.user.domain.entity.BlockUser;
import com.yapp.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(imports = {Authority.class, BlockUser.class, Collectors.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.nickname", target = "nickname")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.imageUrl", target = "imageUrl")
    @Mapping(source = "user.introduction", target = "introduction")
    UserDto toDto(User user);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(expression = "java(user.getAuthorities().stream().map(Authority::getAuthorityName).collect(Collectors.toSet()))", target = "authorities")
    @Mapping(expression = "java(user.getBlockUsers().stream().map(blockUser -> blockUser.getTo().getId()).collect(Collectors.toSet()))", target = "blockUsers")
    ServiceUserDto toServiceDto(User user);
}

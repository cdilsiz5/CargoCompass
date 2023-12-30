package com.transmarket.app.userservice.mapper;

import com.transmarket.app.userservice.dto.UserDto;
import com.transmarket.app.userservice.model.User;
import com.transmarket.app.userservice.request.CreateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper USER_MAPPER= Mappers.getMapper(UserMapper.class);

    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> userList);

    User createUser(CreateUserRequest request);

    void updateUser(@MappingTarget User user, CreateUserRequest request);
}

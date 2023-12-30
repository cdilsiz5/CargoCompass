package com.cargocompass.app.userservice.mapper;

import com.cargocompass.app.userservice.dto.UserDto;
import com.cargocompass.app.userservice.request.CreateUserRequest;
import com.cargocompass.app.userservice.model.User;
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

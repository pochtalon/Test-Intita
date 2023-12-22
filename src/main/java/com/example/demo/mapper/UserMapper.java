package com.example.demo.mapper;

import com.example.demo.config.MapperConfig;
import com.example.demo.dto.RegisterUserRequestDto;
import com.example.demo.dto.UpdateUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toModel(RegisterUserRequestDto requestDto);

    User toModel(UpdateUserDto requestDto);

    UserDto toDto(User user);
}

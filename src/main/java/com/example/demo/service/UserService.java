package com.example.demo.service;

import com.example.demo.dto.RegisterUserRequestDto;
import com.example.demo.dto.UpdateUserDto;
import com.example.demo.dto.UserDto;
import java.util.List;

public interface UserService {
    UserDto saveUser(RegisterUserRequestDto requestDto);

    List<UserDto> getAll();

    UserDto updateUser(Long id, UpdateUserDto requestDto);

    UserDto getById(Long id);

    void deleteById(Long id);
}

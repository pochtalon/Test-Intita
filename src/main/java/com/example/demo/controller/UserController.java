package com.example.demo.controller;

import com.example.demo.dto.RegisterUserRequestDto;
import com.example.demo.dto.UpdateUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.RegistrationException;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users management", description = "Endpoints for managing users")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    private final UserService userService;

    @SneakyThrows
    @PostMapping
    @Operation(summary = "Create new user", description = "Adding new user to db")
    public UserDto addUser(@RequestBody @Valid RegisterUserRequestDto requestDto) {
        if (!requestDto.getPassword().equals(requestDto.getPasswordRepeat())) {
            throw new RegistrationException("Can't register user with this parameters");
        }
        return userService.saveUser(requestDto);
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Get list of users")
    public List<UserDto> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id", description = "Get user by id")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @SneakyThrows
    @PutMapping("/{id}")
    @Operation(summary = "Update user info", description = "Change user info by id")
    public UserDto updateUser(@PathVariable Long id,
                              @RequestBody @Valid UpdateUserDto requestDto) {
        if (requestDto.getPassword() != null && requestDto.getPasswordRepeat() != null
                && !requestDto.getPassword().equals(requestDto.getPasswordRepeat())) {
            throw new RegistrationException("Can't update user with this parameters");
        }
        return userService.updateUser(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id", description = "Delete user by id")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}

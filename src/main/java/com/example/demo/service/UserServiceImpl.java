package com.example.demo.service;

import com.example.demo.dto.RegisterUserRequestDto;
import com.example.demo.dto.UpdateUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto saveUser(RegisterUserRequestDto requestDto) {
        User saved = userRepository.save(userMapper.toModel(requestDto));
        return userMapper.toDto(saved);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @SneakyThrows
    @Override
    public UserDto updateUser(Long id, UpdateUserDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't find task with id " + id));
        updateFields(user, requestDto);
        User updated = userRepository.save(user);
        return userMapper.toDto(updated);
    }

    @SneakyThrows
    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Can't find task with id " + id));
        return userMapper.toDto(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private void updateFields(User user, UpdateUserDto updateDto) {
        if (updateDto.getPassword() != null && updateDto.getPasswordRepeat() != null
                && updateDto.getPassword().equals(updateDto.getPasswordRepeat())) {
            user.setPassword(updateDto.getPassword());
        }
        if (updateDto.getLogin() != null) {
            user.setLogin(updateDto.getLogin());
        }
        if (updateDto.getFirstName() != null) {
            user.setFirstName(updateDto.getFirstName());
        }
        if (updateDto.getLastName() != null) {
            user.setLastName(updateDto.getLastName());
        }
        if (updateDto.getAddress() != null) {
            user.setAddress(updateDto.getAddress());
        }
        if (updateDto.getAbout() != null) {
            user.setAbout(updateDto.getAbout());
        }
    }
}

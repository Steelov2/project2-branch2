package com.example.bookstore.services;

import com.example.bookstore.DTOs.User.UserResponseDto;
import com.example.bookstore.DTOs.User.UserRequestDto;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    List<UserRequestDto> getAll();
    Optional<UserRequestDto> getByID(Long id);
    void deleteByID(Long id);
    UserResponseDto create(UserResponseDto userResponseDto);
    void update(UserResponseDto userResponseDto, Long id);
    List<UserRequestDto> getByUsernameContaining(String name);
}

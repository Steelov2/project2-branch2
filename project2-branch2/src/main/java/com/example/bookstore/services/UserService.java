package com.example.bookstore.services;

import com.example.bookstore.DTOs.User.UserRequestDto;
import com.example.bookstore.DTOs.User.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    List<UserResponseDto> getAll();
    Optional<UserResponseDto> getByID(Long id);
    void deleteByID(Long id);
    UserRequestDto create(UserRequestDto userRequestDto);
    void update(UserRequestDto userRequestDto);
    List<UserResponseDto> getByUsernameContaining(String name);
}

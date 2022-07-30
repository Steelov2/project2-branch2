package com.example.bookstore.services;

import com.example.bookstore.DTOs.User.UserDto;
import com.example.bookstore.DTOs.User.UserGetDto;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    List<UserGetDto> getAll();
    Optional<UserGetDto> getByID(Long id);
    void deleteByID(Long id);
    UserDto create(UserDto userDto);
    void update(UserDto userDto, Long id);
    List<UserGetDto> getByUsernameContaining(String name);
}

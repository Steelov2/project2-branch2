package com.example.bookstore.services;

import com.example.bookstore.DTOs.User.AdminUpdateAndSaveUserDto;
import com.example.bookstore.DTOs.User.UserUpdateAndSaveUserDto;
import com.example.bookstore.DTOs.User.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    List<UserResponseDto> getAll();
    Optional<UserResponseDto> getByID(Long id);
    void deleteByID(Long id);
    void createForUser(UserUpdateAndSaveUserDto userUpdateAndSaveUserDto);
    void createForAdmin(AdminUpdateAndSaveUserDto adminUpdateAndSaveUserDto);
    void updateForUser(UserUpdateAndSaveUserDto userUpdateAndSaveUserDto);
    void updateForAdmin(AdminUpdateAndSaveUserDto adminUpdateAndSaveUserDto);
    List<UserResponseDto> getByUsernameContaining(String name);
}

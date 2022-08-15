package com.example.bookstore.dto.user;

import com.example.bookstore.entities.Role;
import lombok.*;

@Data


public class UserResponseDto {
    private Long id;
    private String username;
    private Role role;
    private Boolean isBlocked;




}

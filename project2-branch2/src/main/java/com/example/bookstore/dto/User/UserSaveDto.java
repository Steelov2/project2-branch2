package com.example.bookstore.dto.User;

import com.example.bookstore.entities.Role;
import lombok.Data;

@Data

public class UserSaveDto {
    private Long id;
    private String username;
    private String password;
    private Role role=Role.USER;
    private Boolean isBlocked=false;




}

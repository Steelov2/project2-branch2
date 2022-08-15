package com.example.bookstore.dto.user;

import com.example.bookstore.entities.Role;
import com.example.bookstore.entities.User;
import lombok.*;

@Data

public class AdminUpdateAndSaveUserDto {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private Boolean isBlocked;

    public User convertAdminUpdatesUserDtoToEntity(){
        User user = new User();
        user.setId(this.getId());
        user.setUsername(this.getUsername());
        user.setPassword(this.getPassword());
        user.setRole(this.getRole());
        user.setIsBlocked(this.getIsBlocked());
        return user;
    }





}

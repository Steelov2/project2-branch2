package com.example.bookstore.DTOs.User;

import com.example.bookstore.entities.Role;
import com.example.bookstore.entities.User;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UserRequestDto {
    private Long id;
    private String username;
    private Role role;
    private Boolean isBlocked;

    public UserRequestDto(String username,
                          Role role,
                          Boolean isBlocked) {
        this.username = username;
        this.role = role;
        this.isBlocked = isBlocked;
    }

    public User convertUserGetDtoToEntity(){
        User user = new User();
        user.setId(this.getId());
        user.setUsername(this.getUsername());
        user.setRole(this.getRole());
        user.setIsBlocked(this.getIsBlocked());
        return user;
    }
}
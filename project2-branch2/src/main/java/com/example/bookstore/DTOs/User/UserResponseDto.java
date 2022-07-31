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
public class UserResponseDto {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private Boolean isBlocked;

    public UserResponseDto(String username,
                           String password,
                           Role role,
                           Boolean isBlocked) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isBlocked = isBlocked;
    }

    public User convertUserDtoToEntity(){
        User user = new User();
        user.setId(this.getId());
        user.setUsername(this.getUsername());
        user.setRole(this.getRole());
        user.setIsBlocked(this.getIsBlocked());
        user.setPassword(this.getPassword());
        return user;
    }
}

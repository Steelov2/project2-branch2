package com.example.bookstore.dto.User;

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
    private Role role;
    private Boolean isBlocked;



    public User convertUserResponseDtoToEntity(){
        User user = new User();
        user.setId(this.getId());
        user.setUsername(this.getUsername());
        user.setRole(this.getRole());
        user.setIsBlocked(this.getIsBlocked());
        return user;
    }
}
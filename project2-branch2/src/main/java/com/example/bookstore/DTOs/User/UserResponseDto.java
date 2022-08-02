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
    private Role role;
    private Boolean locked;
    private Boolean enabled;


    public User convertUserResponseDtoToEntity(){
        User user = new User();
        user.setId(this.getId());
        user.setUsername(this.getUsername());
        user.setRole(this.getRole());
        user.setLocked(this.getLocked());
        user.setEnabled(this.getEnabled());
        return user;
    }
}

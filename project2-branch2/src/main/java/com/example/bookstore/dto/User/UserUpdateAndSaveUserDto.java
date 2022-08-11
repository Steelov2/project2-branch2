package com.example.bookstore.dto.User;

import com.example.bookstore.entities.User;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpdateAndSaveUserDto {
    private Long id;
    private String username;
    private String password;




    public User convertUserUpdateAndSaveUserDtoToEntity(){
        User user = new User();
        user.setId(this.getId());
        user.setUsername(this.getUsername());
        return user;
    }
}

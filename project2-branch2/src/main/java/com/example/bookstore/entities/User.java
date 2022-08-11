package com.example.bookstore.entities;

import com.example.bookstore.dto.User.UserUpdateAndSaveUserDto;
import com.example.bookstore.dto.User.UserResponseDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean isBlocked;

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserResponseDto convertUserToDtoResponseDto() {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(this.getId());
        userResponseDto.setUsername(this.getUsername());
        userResponseDto.setIsBlocked(this.getIsBlocked());

        userResponseDto.setRole(this.getRole());
        return userResponseDto;
    }

    public UserUpdateAndSaveUserDto convertUserToRequestDto() {
        UserUpdateAndSaveUserDto userUpdateAndSaveUserDto = new UserUpdateAndSaveUserDto();
        userUpdateAndSaveUserDto.setId(this.getId());
        userUpdateAndSaveUserDto.setUsername(this.getUsername());
        userUpdateAndSaveUserDto.setPassword(this.getPassword());
//        userRequestDto.setIsBlocked(this.getIsBlocked());
//        userRequestDto.setRole(this.getRole());
        return userUpdateAndSaveUserDto;
    }


}

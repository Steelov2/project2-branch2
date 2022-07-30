package com.example.bookstore.entities;

import com.example.bookstore.DTOs.User.UserDto;
import com.example.bookstore.DTOs.User.UserGetDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
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

    //@Enumerated(EnumType.STRING)
    private Role role;
    private Boolean isBlocked;


    public User(String username,
                String password,
                Role role,
                Boolean isBlocked) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isBlocked = isBlocked;
    }

    public UserDto convertUserToDto(){
        UserDto userDto=new UserDto();
        userDto.setId(this.getId());
        userDto.setPassword(this.getPassword());
        userDto.setUsername(this.getUsername());
        userDto.setIsBlocked(this.getIsBlocked());
        userDto.setRole(this.getRole());
        return userDto;
    }
    public UserGetDto convertUserToGetDto(){
        UserGetDto userGetDto=new UserGetDto();
        userGetDto.setId(this.getId());
        userGetDto.setUsername(this.getUsername());
        userGetDto.setIsBlocked(this.getIsBlocked());
        userGetDto.setRole(this.getRole());
        return userGetDto;
    }



}

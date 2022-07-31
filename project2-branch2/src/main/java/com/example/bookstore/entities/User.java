package com.example.bookstore.entities;

import com.example.bookstore.DTOs.User.UserResponseDto;
import com.example.bookstore.DTOs.User.UserRequestDto;
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




}

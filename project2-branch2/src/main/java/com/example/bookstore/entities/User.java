package com.example.bookstore.entities;

import com.example.bookstore.dto.user.UserUpdateAndSaveUserDto;
import com.example.bookstore.dto.user.UserResponseDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

package com.example.bookstore.entities;

import com.example.bookstore.DTOs.User.UserRequestDto;
import com.example.bookstore.DTOs.User.UserResponseDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    //@Enumerated(EnumType.STRING)
    private Role role;
    private Boolean locked;
    private Boolean enabled;




    public UserResponseDto convertUserToDtoResponseDto(){
        UserResponseDto userResponseDto =new UserResponseDto();
        userResponseDto.setId(this.getId());
        userResponseDto.setUsername(this.getUsername());
        userResponseDto.setLocked(this.getLocked());
        userResponseDto.setEnabled(this.getEnabled());
        userResponseDto.setRole(this.getRole());
        return userResponseDto;
    }
    public UserRequestDto convertUserToRequestDto(){
        UserRequestDto userRequestDto =new UserRequestDto();
        userRequestDto.setId(this.getId());
        userRequestDto.setUsername(this.getUsername());
        userRequestDto.setPassword(this.getPassword());
        userRequestDto.setIsBlocked(this.getEnabled());
        userRequestDto.setRole(this.getRole());
        return userRequestDto;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

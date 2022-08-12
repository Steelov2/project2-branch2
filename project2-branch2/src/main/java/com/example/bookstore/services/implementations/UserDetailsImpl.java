package com.example.bookstore.services.implementations;

import com.example.bookstore.exceptions.UserIsBlockedException;
import com.example.bookstore.repository.UserRepo;
import com.example.bookstore.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {
    private final UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(foundUser.getUsername())
                .password(foundUser.getPassword())
                .authorities(foundUser.getRole().name())
                .accountLocked(foundUser.getIsBlocked())
                .build();
    }

}
package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.User.UserRequestDto;
import com.example.bookstore.DTOs.User.UserResponseDto;
import com.example.bookstore.Repos.UserRepo;
import com.example.bookstore.controllers.CreateUserRequest;
import com.example.bookstore.entities.User;
//import com.example.bookstore.security.PasswordEncoder;
import com.example.bookstore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.cert.Extension;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserResponseDto> getAll() {
        return userRepo.findAll()
                .stream()
                .map(User::convertUserToDtoResponseDto)
                .toList();
    }

    @Override
    public Optional<UserResponseDto> getByID(Long id) {
        return userRepo.findById(id)
                .map(User::convertUserToDtoResponseDto);
    }

    @Override
    public void deleteByID(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserRequestDto create(UserRequestDto userRequestDto) {
        User user = userRequestDto.convertUserRequestDtoToEntity();
        User userCreated= userRepo.save(user);
        return userCreated.convertUserToRequestDto() ;
    }
    public void createUser(CreateUserRequest userRequest) {
        userRepo.saveAndFlush(
                new User(
                        null,
                        userRequest.getLogin(),
                        passwordEncoder.encode(userRequest.getPassword())
                )
        );
    }



    @Override
    public void update(UserRequestDto userRequestDto) {
        User existingUser;
        User user = userRequestDto.convertUserRequestDtoToEntity();
        try {
            existingUser = userRepo.findById(user.getId())
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingUser.setId(user.getId());
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            //   existingUser.setIsBlocked(user.getIsBlocked());
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<UserResponseDto> getByUsernameContaining(String name) {
        return userRepo.findByUsername(name)
                .stream()
                .map(User::convertUserToDtoResponseDto)
                .toList();
    }
}



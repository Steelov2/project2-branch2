package com.example.bookstore.services.implementations;

import com.example.bookstore.dto.User.AdminUpdateAndSaveUserDto;
import com.example.bookstore.dto.User.UserUpdateAndSaveUserDto;
import com.example.bookstore.dto.User.UserResponseDto;
import com.example.bookstore.exceptions.AlreadyRegisteredException;
import com.example.bookstore.exceptions.ResourceNotFoundException;
import com.example.bookstore.repository.UserRepo;
import com.example.bookstore.entities.User;
import com.example.bookstore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        if (userRepo.existsById(id))
            return userRepo.findById(id)
                    .map(User::convertUserToDtoResponseDto);
        throw
                new ResourceNotFoundException(String.format("The user with ID: %d is not found or doesn't exist", id));
    }

    @Override
    public void deleteByID(Long id) {
        if (userRepo.existsById(id))
            userRepo.deleteById(id);
        throw
                new ResourceNotFoundException(String.format("The user with ID: %d is not found or already deleted", id));
    }


    @Override
    public void createForUser(UserUpdateAndSaveUserDto userUpdateAndSaveUserDto) {
        User user = userUpdateAndSaveUserDto.convertUserUpdateAndSaveUserDtoToEntity();
        if (userRepo.existsByUsername(user.getUsername())) {
            throw
                    new AlreadyRegisteredException(String.format("The username %s is already in use", user.getUsername()));
        } else

            userRepo.saveAndFlush(
                    new User(
                            null,
                            userUpdateAndSaveUserDto.getUsername(),
                            passwordEncoder.encode(userUpdateAndSaveUserDto.getPassword())
                    )
            );
    }

    @Override
    public void updateForUser(UserUpdateAndSaveUserDto userUpdateAndSaveUserDto) {
        User user = userUpdateAndSaveUserDto.convertUserUpdateAndSaveUserDtoToEntity();
        userRepo.findById(user.getId()).orElseThrow(() ->
                new ResourceNotFoundException(String.format("The user with ID: %d is not found or doesn't exist", user.getId())));

        userRepo.saveAndFlush(
                new User(
                        userUpdateAndSaveUserDto.getId(),
                        userUpdateAndSaveUserDto.getUsername(),
                        passwordEncoder.encode(userUpdateAndSaveUserDto.getPassword())
                )
        );

    }

    @Override
    public void createForAdmin(AdminUpdateAndSaveUserDto adminUpdateAndSaveUserDto) {
        User user = adminUpdateAndSaveUserDto.convertAdminUpdatesUserDtoToEntity();
        if (userRepo.existsByUsername(user.getUsername())) {
            throw
                    new AlreadyRegisteredException(String.format("The username %s is already in use", user.getUsername()));
        } else

        userRepo.saveAndFlush(
                new User(
                        null,
                        adminUpdateAndSaveUserDto.getUsername(),
                        passwordEncoder.encode(adminUpdateAndSaveUserDto.getPassword()),
                        adminUpdateAndSaveUserDto.getRole(),
                        adminUpdateAndSaveUserDto.getIsBlocked())
        );
    }


    @Override
    public void updateForAdmin(AdminUpdateAndSaveUserDto adminUpdateAndSaveUserDto) {
        User user = adminUpdateAndSaveUserDto.convertAdminUpdatesUserDtoToEntity();
        userRepo.findById(user.getId()).orElseThrow(() ->
                new ResourceNotFoundException(String.format("The publisher with ID: %d is not found or doesn't exist", user.getId())));

        userRepo.save(
                new User(
                        adminUpdateAndSaveUserDto.getId(),
                        adminUpdateAndSaveUserDto.getUsername(),
                        passwordEncoder.encode(adminUpdateAndSaveUserDto.getPassword()),
                        adminUpdateAndSaveUserDto.getRole(),
                        adminUpdateAndSaveUserDto.getIsBlocked()));
    }

    @Override
    public List<UserResponseDto> getByUsernameContaining(String name) {
        if (userRepo.existsByUsername(name))
            return userRepo.findByUsername(name)
                    .stream()
                    .map(User::convertUserToDtoResponseDto)
                    .toList();
        else
            throw new ResourceNotFoundException(String.format("The user with username: %s is not found or doesn't exist", name));
    }
}



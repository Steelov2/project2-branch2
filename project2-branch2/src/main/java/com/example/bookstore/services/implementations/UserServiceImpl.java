package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.User.AdminUpdateAndSaveUserDto;
import com.example.bookstore.DTOs.User.UserUpdateAndSaveUserDto;
import com.example.bookstore.DTOs.User.UserResponseDto;
import com.example.bookstore.Repos.UserRepo;
import com.example.bookstore.entities.User;
//import com.example.bookstore.security.PasswordEncoder;
import com.example.bookstore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
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
        return userRepo.findById(id)
                .map(User::convertUserToDtoResponseDto);
    }

    @Override
    public void deleteByID(Long id) {
        userRepo.deleteById(id);
    }

    //    public UserRequestDto create(UserRequestDto userRequestDto) {
//        User user = userRequestDto.convertUserRequestDtoToEntity();
//        User userCreated= userRepo.save(user);
//        return userCreated.convertUserToRequestDto() ;
//    }
    @Override
    public void createForUser(UserUpdateAndSaveUserDto userUpdateAndSaveUserDto) {
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
        userRepo.saveAndFlush(
                new User(
                        null,
                        userUpdateAndSaveUserDto.getUsername(),
                        passwordEncoder.encode(userUpdateAndSaveUserDto.getPassword())
                )
        );

    }

    @Override
    public void createForAdmin(AdminUpdateAndSaveUserDto adminUpdateAndSaveUserDto) {
        userRepo.saveAndFlush(new User(
                null,
                adminUpdateAndSaveUserDto.getUsername(),
                passwordEncoder.encode(adminUpdateAndSaveUserDto.getPassword()),
                adminUpdateAndSaveUserDto.getRole(),
                adminUpdateAndSaveUserDto.getIsBlocked())
        );
    }




    @Override
    public void updateForAdmin(AdminUpdateAndSaveUserDto adminUpdateAndSaveUserDto) {
        userRepo.saveAndFlush(new User(
                null,
                adminUpdateAndSaveUserDto.getUsername(),
                passwordEncoder.encode(adminUpdateAndSaveUserDto.getPassword()),
                adminUpdateAndSaveUserDto.getRole(),
                adminUpdateAndSaveUserDto.getIsBlocked())
        );


    }

    @Override
    public List<UserResponseDto> getByUsernameContaining(String name) {
        return userRepo.findByUsername(name)
                .stream()
                .map(User::convertUserToDtoResponseDto)
                .toList();
    }
}



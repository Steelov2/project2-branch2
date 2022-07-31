package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.User.UserResponseDto;
import com.example.bookstore.DTOs.User.UserRequestDto;
import com.example.bookstore.Repos.UserRepo;
import com.example.bookstore.converters.UserConverter;
import com.example.bookstore.entities.User;
import com.example.bookstore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;


    @Override
    public List<UserRequestDto> getAll() {
        return userRepo.findAll()
                .stream()
                .map(UserConverter::convertUserToRequestDto)
                .toList();
    }

    @Override
    public Optional<UserRequestDto> getByID(Long id) {
        return userRepo.findById(id)
                .map(UserConverter::convertUserToRequestDto);
    }

    @Override
    public void deleteByID(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserResponseDto create(UserResponseDto userResponseDto) {
        User user = UserConverter.convertUserResponseDtoToEntity(userResponseDto);
        user= userRepo.save(user);
        return UserConverter.convertUserToResponseDto(user) ;
    }

    @Override
    public void update(UserResponseDto userResponseDto, Long id) {
        User existingUser;
        User user = UserConverter.convertUserResponseDtoToEntity(userResponseDto);
        try {
            existingUser = userRepo.findById(id)
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingUser.setId(user.getId());
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            existingUser.setIsBlocked(user.getIsBlocked());
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        ;

    }

    @Override
    public List<UserRequestDto> getByUsernameContaining(String name) {
        return userRepo.findByUsernameIsContainingIgnoreCase(name)
                .stream()
                .map(UserConverter::convertUserToRequestDto)
                .toList();
    }
}

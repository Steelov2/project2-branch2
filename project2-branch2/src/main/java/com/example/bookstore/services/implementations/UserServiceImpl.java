package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.User.UserDto;
import com.example.bookstore.DTOs.User.UserGetDto;
import com.example.bookstore.Repos.UserRepo;
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
    public List<UserGetDto> getAll() {
        return userRepo.findAll()
                .stream()
                .map(User::convertUserToGetDto)
                .toList();
    }

    @Override
    public Optional<UserGetDto> getByID(Long id) {
        return userRepo.findById(id)
                .map(User::convertUserToGetDto);
    }

    @Override
    public void deleteByID(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = userDto.convertUserDtoToEntity();
        return userRepo.save(user).convertUserToDto();
    }

    @Override
    public void update(UserDto userDto, Long id) {
        User existingUser;
        User user = userDto.convertUserDtoToEntity();
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
    public List<UserGetDto> getByUsernameContaining(String name) {
        return userRepo.findByUsernameIsContainingIgnoreCase(name)
                .stream()
                .map(User::convertUserToGetDto)
                .toList();
    }
}

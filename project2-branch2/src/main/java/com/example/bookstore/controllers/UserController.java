package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.User.UserResponseDto;
import com.example.bookstore.DTOs.User.UserRequestDto;
import com.example.bookstore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public List<UserRequestDto> getAll(){
        return  userService.getAll();
    }

    @GetMapping("/user/{userID}")
    private Optional<UserRequestDto> getByID (@PathVariable("userID") Long id)
    {
        return userService.getByID(id);
    }
    @GetMapping("/user/username/{username}")
    private List<UserRequestDto> getAuthorByUserName(@PathVariable("username") String username){
        return userService.getByUsernameContaining(username);
    }
    @DeleteMapping("/user/{userID}")
    private void deleteUserById(@PathVariable("userID") Long id)
    {
        userService.deleteByID(id);
    }

    @PostMapping("/user")
    private UserResponseDto savePublisher(@RequestBody UserResponseDto userResponseDto)
    {
        return  userService.create(userResponseDto);
    }
    @PutMapping("/user/{userID}")
    private void updatePublisher(@RequestBody UserResponseDto userResponseDto, @PathVariable("userID") Long id)    {
        if(!Objects.equals(id, userResponseDto.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        userService.update(userResponseDto,id);
    }

}

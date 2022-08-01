package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.User.UserRequestDto;
import com.example.bookstore.DTOs.User.UserResponseDto;
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
    public List<UserResponseDto> getAll(){
        return  userService.getAll();
    }

    @GetMapping("/user/{userID}")
    private Optional<UserResponseDto> getByID (@PathVariable("userID") Long id)
    {
        return userService.getByID(id);
    }
    @GetMapping("/user/username/{username}")
    private List<UserResponseDto> getAuthorByUserName(@PathVariable("username") String username){
        return userService.getByUsernameContaining(username);
    }
    @DeleteMapping("/user/{userID}")
    private void deleteUserById(@PathVariable("userID") Long id)
    {
        userService.deleteByID(id);
    }

    @PostMapping("/user")
    private UserRequestDto savePublisher(@RequestBody UserRequestDto userRequestDto)
    {
        return  userService.create(userRequestDto);
    }
    @PutMapping("/user")
    private void updatePublisher(@RequestBody UserRequestDto userRequestDto)    {

        userService.update(userRequestDto);
    }

}

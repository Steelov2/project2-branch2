package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.User.UserRequestDto;
import com.example.bookstore.DTOs.User.UserResponseDto;
import com.example.bookstore.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
//    private final UserServiceimpl userServiceimpl;


    @GetMapping("/usersList")
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
    private UserRequestDto saveUser(@RequestBody UserRequestDto userRequestDto)
    {
        return  userService.create(userRequestDto);
    }
    @PutMapping("/user")
    private void updatePublisher(@RequestBody UserRequestDto userRequestDto)    {

        userService.update(userRequestDto);
    }
//    @PostMapping
//    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest userRequest) {
//        userServiceimpl.createUser(userRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
}



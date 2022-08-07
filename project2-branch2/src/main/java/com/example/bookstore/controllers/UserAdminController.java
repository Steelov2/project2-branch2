package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.User.UserRequestDto;
import com.example.bookstore.DTOs.User.UserResponseDto;
import com.example.bookstore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/users")
@AllArgsConstructor
public class UserAdminController {
    private final UserService userService;


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
    private void saveUser(@RequestBody UserRequestDto userRequestDto)
    {
        userService.create(userRequestDto);
    }
    @PutMapping("/user")
    private void updateUser(@RequestBody UserRequestDto userRequestDto)    {

        userService.update(userRequestDto);
    }
//    @PostMapping
//    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest userRequest) {
//        userServiceimpl.createUser(userRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
}



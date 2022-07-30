package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.User.UserDto;
import com.example.bookstore.DTOs.User.UserGetDto;
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
    public List<UserGetDto> getAll(){
        return  userService.getAll();
    }

    @GetMapping("/user/{userID}")
    private Optional<UserGetDto> getByID (@PathVariable("userID") Long id)
    {
        return userService.getByID(id);
    }
    @GetMapping("/user/username/{username}")
    private List<UserGetDto> getAuthorByUserName(@PathVariable("username") String username){
        return userService.getByUsernameContaining(username);
    }
    @DeleteMapping("/user/{userID}")
    private void deleteUserById(@PathVariable("userID") Long id)
    {
        userService.deleteByID(id);
    }

    @PostMapping("/user")
    private UserDto savePublisher(@RequestBody UserDto userDto)
    {
        return  userService.create(userDto);
    }
    @PutMapping("/user/{userID}")
    private void updatePublisher(@RequestBody UserDto userDto,@PathVariable("userID") Long id)    {
        if(!Objects.equals(id, userDto.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        userService.update(userDto,id);
    }

}

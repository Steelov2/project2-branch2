package com.example.bookstore.controllers;

import com.example.bookstore.dto.user.UserUpdateAndSaveUserDto;
import com.example.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/users")

public class UserUserController {
    private final UserService userService;
    @Autowired
    public UserUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    public void saveUser(@RequestBody UserUpdateAndSaveUserDto userUpdateAndSaveUserDto)
    {
        userService.createForUser(userUpdateAndSaveUserDto);
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestBody UserUpdateAndSaveUserDto userUpdateAndSaveUserDto)    {

        userService.updateForUser(userUpdateAndSaveUserDto);
    }

}

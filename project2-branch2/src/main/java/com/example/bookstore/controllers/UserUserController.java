package com.example.bookstore.controllers;

import com.example.bookstore.dto.User.UserUpdateAndSaveUserDto;
import com.example.bookstore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/users")
@AllArgsConstructor
public class UserUserController {
    private final UserService userService;
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

package com.example.bookstore.controllers;

import com.example.bookstore.dto.User.AdminUpdateAndSaveUserDto;
import com.example.bookstore.dto.User.UserResponseDto;
import com.example.bookstore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/usersList")
    public List<UserResponseDto> getAll(){
        return  userService.getAll();
    }

    @GetMapping("/user/{userID}")
    public Optional<UserResponseDto> getByID (@PathVariable("userID") Long id)
    {
        return userService.getByID(id);
    }
    @GetMapping("/user/userName/{userName}")
    public List<UserResponseDto> getAuthorByUserName(@PathVariable("userName") String username){
        return userService.getByUsernameContaining(username);
    }
    @DeleteMapping("/user/{userID}")
    public void deleteUserById(@PathVariable("userID") Long id)
    {
        userService.deleteByID(id);
    }

   @PostMapping("/saveUser")
   public void saveUser(@RequestBody AdminUpdateAndSaveUserDto adminUpdateAndSaveUserDto)
    {
        userService.createForAdmin(adminUpdateAndSaveUserDto);
    }
    @PutMapping("/updateUser")
    public void updateUser(@RequestBody AdminUpdateAndSaveUserDto adminUpdateAndSaveUserDto)    {

        userService.updateForAdmin(adminUpdateAndSaveUserDto);
    }

}



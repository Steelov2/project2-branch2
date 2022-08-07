package com.example.bookstore.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateUserRequest {
    private String login;
    private String password;
    private String role;

    public void createUser(CreateUserRequest userRequest) {

    }
}

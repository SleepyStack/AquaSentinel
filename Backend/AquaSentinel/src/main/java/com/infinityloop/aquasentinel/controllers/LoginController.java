package com.infinityloop.aquasentinel.controllers;


import com.infinityloop.aquasentinel.dto.RegisterUserRequest;
import com.infinityloop.aquasentinel.entities.User;
import com.infinityloop.aquasentinel.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterUserRequest registerUserRequest) {
        User user = new User();
        user.setName(registerUserRequest.getName());
        user.setEmail(registerUserRequest.getEmail());
        user.setPassword(registerUserRequest.getPhoneNumber());
        user.setPassword(registerUserRequest.getPassword());
        userService.createUser(user);
        return "User registered successfully";
    }
}
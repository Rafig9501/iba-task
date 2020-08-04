package com.ibabanking.login.controller;

import com.ibabanking.login.dto.UserDto;
import com.ibabanking.login.entity.UserEntity;
import com.ibabanking.login.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto user){
        return userService.login(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserDto user){
        return userService.register(user);
    }
}

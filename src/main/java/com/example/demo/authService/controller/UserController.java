package com.example.demo.authService.controller;

import com.example.demo.authService.exception.BadRequestException;
import com.example.demo.authService.model.UserEntity;
import com.example.demo.authService.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @GetMapping("/{userId}")
    UserEntity getUserById(@PathVariable Long userId) {
        if (Objects.isNull(userId)) {
            throw new BadRequestException("UserId can't be null");
        }
        return userService.getUserById(userId);
    }

    @GetMapping("/users")
    List<UserEntity> getUsers() {
        return userService.getAllUsers();
    }
}

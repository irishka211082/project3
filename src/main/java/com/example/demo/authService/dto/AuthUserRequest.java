package com.example.demo.authService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthUserRequest {
    private String login;
    private String password;
}

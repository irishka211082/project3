package com.example.demo.authService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtUser {
    private final String login;
    private final String password;
    private final Long userId;
    private final String role;
}

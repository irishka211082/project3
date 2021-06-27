package com.example.demo.authService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationUserRequest {
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}

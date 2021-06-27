package com.example.demo.authService.controller;

import com.example.demo.authService.dto.*;
import com.example.demo.utils.AuthHelper;
import com.example.demo.authService.service.AuthorizationService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthorizationService authorizationService;

    @PostMapping("/register")
    public void registerUser(@RequestBody @NotNull RegistrationUserRequest registrationUserRequest) {
        AuthHelper.checkRegistrationUserReguest(registrationUserRequest);
        authorizationService.registerUser(registrationUserRequest);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody @NotNull AuthUserRequest authUserRequest) {
        AuthHelper.checkAuthUserRequest(authUserRequest);
        return authorizationService.loginUser(authUserRequest);
    }

    @GetMapping("/logout")
    public void logout(@RequestBody @NotNull LogoutRequest logoutRequest) {
        AuthHelper.checkLogoutRequest(logoutRequest);
        authorizationService.logout(logoutRequest);
    }
}
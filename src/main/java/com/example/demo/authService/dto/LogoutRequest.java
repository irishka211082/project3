package com.example.demo.authService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogoutRequest {
    // пересмотреть
    private Long userId;
    private String jwtToken;
}

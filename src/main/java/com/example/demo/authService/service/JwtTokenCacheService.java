package com.example.demo.authService.service;

import com.example.demo.authService.model.JwtUser;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenCacheService {

    private final Map<String, JwtUser> jwtTokenCache = new HashMap<>();

    void addToken(String jwtToken, JwtUser jwtUser) {
        jwtTokenCache.put(jwtToken, jwtUser);
    }

    void removeToken(String jwtToken) {
        jwtTokenCache.remove(jwtToken);
    }
}

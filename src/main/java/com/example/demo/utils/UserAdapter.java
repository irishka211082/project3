package com.example.demo.utils;

import com.example.demo.authService.dto.RegistrationUserRequest;
import com.example.demo.authService.model.JwtUser;
import com.example.demo.authService.model.UserEntity;
import com.example.demo.authService.model.UserRole;

public class UserAdapter {
    public static UserEntity toUserEntity(RegistrationUserRequest registrationUserRequest) {
        return new UserEntity(
                registrationUserRequest.getLogin(),
                registrationUserRequest.getPassword(),
                registrationUserRequest.getEmail(),
                registrationUserRequest.getFirstName(),
                registrationUserRequest.getLastName(),
                UserRole.USER);
    }

    public static JwtUser toJwtUser(UserEntity userEntity) {
        return new JwtUser(
                userEntity.getLogin(),
                userEntity.getPassword(),
                userEntity.getUserId(),
                userEntity.getRole().getName());
    }
}

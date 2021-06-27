package com.example.demo.authService.service;

import com.example.demo.authService.dto.AuthUserRequest;
import com.example.demo.authService.dto.LogoutRequest;
import com.example.demo.authService.dto.RegistrationUserRequest;
import com.example.demo.authService.exception.BadRequestException;
import com.example.demo.authService.jwt.JwtProvider;
import com.example.demo.authService.model.JwtUser;
import com.example.demo.authService.model.UserEntity;
import com.example.demo.authService.repository.UserRepository;
import com.example.demo.utils.UserAdapter;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthorizationService {

    private UserRepository userRepository;
    private JwtTokenCacheService jwtTokenCacheService;
    private JwtProvider jwtProvider;

    public Long registerUser(RegistrationUserRequest registrationUserRequest) {
        UserEntity userEntity = UserAdapter.toUserEntity(registrationUserRequest);

        UserEntity savedEntry = userRepository.save(userEntity);

        return savedEntry.getUserId();
    }

    public String loginUser(AuthUserRequest authUserRequest) {
        String login = authUserRequest.getLogin();
        String password = authUserRequest.getPassword();

        UserEntity userEntity = userRepository.findByLoginAndPassword(login, password);

        JwtUser jwtUser = UserAdapter.toJwtUser(userEntity);
        String jwtToken = jwtProvider.generateToken(jwtUser);

        jwtTokenCacheService.addToken(jwtToken, jwtUser);

        if (Objects.nonNull(userEntity.getUserId())) {
            return jwtToken;
        } else {
            throw new BadRequestException("Incorrect login or password");
        }
    }

    public void logout(LogoutRequest logoutRequest) {
        jwtTokenCacheService.removeToken(logoutRequest.getJwtToken());
    }
}

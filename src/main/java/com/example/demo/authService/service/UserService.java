package com.example.demo.authService.service;

import com.example.demo.authService.model.UserEntity;
import com.example.demo.authService.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserEntity findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public UserEntity getUserById(Long userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isEmpty()) {
            return null;
        } else {
           return userEntityOptional.get();
        }
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}

package com.example.demo.authService.repository;

import com.example.demo.authService.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLoginAndPassword(String login, String password);

    UserEntity findByLogin(String login);
}

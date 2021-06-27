package com.example.demo.utils;

import com.example.demo.authService.dto.AuthUserRequest;
import com.example.demo.authService.dto.LogoutRequest;
import com.example.demo.authService.dto.RegistrationUserRequest;
import com.example.demo.authService.exception.BadRequestException;

import java.util.Objects;
import java.util.regex.Pattern;

public class AuthHelper {
    public static final Pattern VALID_PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_])(?=\\S+$).{5,15}$");

    public static boolean isValidPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        } else {
            return VALID_PASSWORD_PATTERN.matcher(password).matches();
        }
    }

    public static void checkRegistrationUserReguest(RegistrationUserRequest registrationUserRequest) {
        String login = registrationUserRequest.getLogin();
        String password = registrationUserRequest.getPassword();
        String email = registrationUserRequest.getEmail();
        String firstName = registrationUserRequest.getFirstName();
        String lastName = registrationUserRequest.getLastName();

        if (Objects.isNull(login)) {
            throw new BadRequestException("Login is missed");
        } else if (Objects.isNull(password)) {
            throw new BadRequestException("Password is missed");
        } else if (Objects.isNull(email)) {
            throw new BadRequestException("Email is missed");
        } else if (Objects.isNull(firstName)) {
            throw new BadRequestException("FirstName is missed");
        } else if (Objects.isNull(lastName)) {
            throw new BadRequestException("LastName is missed");
        } else if (!AuthHelper.isValidPassword(password)) {
            throw new BadRequestException("Password must contain only latin characters with at least two of three types of characters (lowercase letters, uppercase letters or digits) also specific symbol, for example: \"!\". Length must be 5-15 characters");
        }
    }

    public static void checkAuthUserRequest(AuthUserRequest authUserRequest) {
        String login = authUserRequest.getLogin();
        String password = authUserRequest.getPassword();

        if (Objects.isNull(login)) {
            throw new BadRequestException("Login is missed");
        } else if (Objects.isNull(password)) {
            throw new BadRequestException("Password is missed");
        }
    }

    public static void checkLogoutRequest(LogoutRequest logoutRequest) {
        Long userId = logoutRequest.getUserId();
        String jwtToken = logoutRequest.getJwtToken();

        if (Objects.isNull(userId)) {
            throw new BadRequestException("UserId is missed");
        } else if (Objects.isNull(jwtToken)) {
            throw new BadRequestException("JwtToken is missed");
        }
    }
}


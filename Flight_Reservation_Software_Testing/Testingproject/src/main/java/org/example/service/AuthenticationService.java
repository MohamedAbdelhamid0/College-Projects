package org.example.service;

import org.example.model.User;

import java.util.Optional;

public class AuthenticationService {
    private final UserService userService;

    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public Optional<User> authenticate(String email, String password) {
        if (email == null || password == null) return Optional.empty();

        for (User user : userService.getAllUsers()) {

            if (user.isPasscheck() && user.getEmail().equalsIgnoreCase(email) &&
                    user.checkEmail()  && user.getPassword().equals(password)) { // Plain text comparison (BAD PRACTICE!)
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public boolean emailExists(String email) {
        if (email == null) return false;
        return userService.getAllUsers().stream()
                .anyMatch(user -> user.getEmail() != null && user.getEmail().equalsIgnoreCase(email));
    }
}
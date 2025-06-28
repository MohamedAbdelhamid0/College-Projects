package org.example.service;

import org.example.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class UserService {
    private final Map<Long, User> userMap = new HashMap<>();

    public User addUser(User user) {
        Objects.requireNonNull(user, "User cannot be null");
        Objects.requireNonNull(user.getId(), "User ID cannot be null");
        Objects.requireNonNull(user.getEmail(), "User email cannot be null");

        if (userMap.containsKey(user.getId())) {
            throw new IllegalArgumentException("User with ID " + user.getId() + " already exists.");
        }
        if (findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists.");
        }
        userMap.put(user.getId(), user);
        return user;
    }

    public User getUserById(Long id) {
        if (id == null) return null;
        return userMap.get(id);
    }

    public User updateUser(User user) {
        Objects.requireNonNull(user, "User cannot be null for update");
        Objects.requireNonNull(user.getId(), "User ID cannot be null for update");
        Objects.requireNonNull(user.getEmail(), "User email cannot be null for update");
        Objects.requireNonNull(user.getPassword(), "User password cannot be null for update"); // Ensure password is not null

        if (userMap.containsKey(user.getId())) {
            // Check if the email is already used by another user
            Optional<User> existingEmailUser = findByEmail(user.getEmail());
            if (existingEmailUser.isPresent() && !existingEmailUser.get().getId().equals(user.getId())) {
                throw new IllegalArgumentException("Cannot update: Email " + user.getEmail() + " is already used by another user.");
            }

            // Check if the password is valid and meets required conditions
            if (user.getPassword().length() < 8) {
                throw new IllegalArgumentException("Password must be at least 8 characters long.");
            }

            // Check that the password starts with an uppercase letter and contains '!'
            if (!(Character.isUpperCase(user.getPassword().charAt(0)) && user.getPassword().contains("!"))) {
                throw new IllegalArgumentException("Password must begin with an uppercase letter and contain '!'.");
            }

            // Update the user information
            userMap.put(user.getId(), user);
            return user;
        }

        return null;
    }


    public User deleteUser(Long id) {
        if (id == null) return null;
        return userMap.remove(id);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    public Optional<User> findByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        String lowerCaseEmail = email.toLowerCase();
        return userMap.values().stream()
                .filter(user -> user.getEmail() != null && user.getEmail().toLowerCase().equals(lowerCaseEmail))
                .findFirst();
    }
}
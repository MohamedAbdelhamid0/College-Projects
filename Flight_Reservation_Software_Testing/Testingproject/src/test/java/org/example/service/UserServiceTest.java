package org.example.service;

import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;
    private User validUser;
    private final Long userId = 1L;
    private final String userEmail = "test@gmail.com";
    private final String userName = "test";
    private final String userPassword = "Password2!";
    @BeforeEach
    void setUp() {
        userService = new UserService();
        validUser = new User(userId, "Test User", userEmail, userPassword);
    }

    @Test
    void addUser_shouldAddValidUser() {
        User addedUser = userService.addUser(validUser);

        assertEquals(validUser, addedUser);
        assertEquals(validUser, userService.getUserById(userId));
    }

    @Test
    void addUser_shouldThrowWhenAddingDuplicateId() {
        userService.addUser(validUser);

        User duplicateIdUser = new User(userId, "Seif", "Seif2004@gmail.com", "Seif2004!");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.addUser(duplicateIdUser));

        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    void addUser_shouldThrowWhenAddingDuplicateEmail() {
        userService.addUser(validUser);

        User duplicateEmailUser = new User(02L, "Youssef", userEmail, userPassword);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.addUser(duplicateEmailUser));

        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    void getUserById_shouldReturnNullForNonExistentId() {
        assertNull(userService.getUserById(999L));
    }

    @Test
    void updateUser_shouldUpdateExistingUser() {
        userService.addUser(validUser);
        User updatedUser = new User(userId, "Seif Elhusseiny", "seif2004@gmail.com", "Seif2004!");

        User result = userService.updateUser(updatedUser);

        assertEquals(updatedUser, result);
        assertEquals(updatedUser, userService.getUserById(userId));
    }

    @Test
    void updateUser_shouldThrowWhenUpdatingToExistingEmail() {
        User existingUser = new User(023L, "Mohamed", "mohamed2@gmail.com","Mohamed2004!");
        userService.addUser(validUser);
        userService.addUser(existingUser);

        User invalidUser = new User(023L, "Mohamed", "mohamed2@gmail.com", "Mohamed200");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.updateUser(invalidUser));


    }

    @Test
    void deleteUser_shouldRemoveExistingUser() {
        userService.addUser(validUser);

        User deletedUser = userService.deleteUser(userId);

        assertEquals(validUser, deletedUser);
        assertNull(userService.getUserById(userId));
    }

    @Test
    void getAllUsers_shouldReturnAllAddedUsers() {
        User user2 = new User(2L, "User 2", "user2@gmail.com","user22004!");
        userService.addUser(validUser);
        userService.addUser(user2);

        List<User> allUsers = userService.getAllUsers();

        assertEquals(2, allUsers.size());
        assertTrue(allUsers.contains(validUser));
        assertTrue(allUsers.contains(user2));
    }

    @Test
    void findByEmail_shouldBeCaseInsensitive() {
        userService.addUser(validUser);

        Optional<User> result = userService.findByEmail("TEST@GMAIl.COM");

        assertTrue(result.isPresent());
        assertEquals(validUser, result.get());
    }

    @Test
    void findByEmail_shouldReturnEmptyForNonExistentEmail() {
        assertTrue(userService.findByEmail("nonexistent@gmail.com").isEmpty());
    }

    @Test
    void shouldThrowWhenAddingUserWithNullId() {
        assertThrows(NullPointerException.class, () -> {
            // Create a User with null ID and null password inside the lambda
            User invalidUser = new User(null, "Null ID", "nullid@example.com", null);
            userService.addUser(invalidUser); // This will throw NullPointerException
        });


    }

    @Test
    void shouldThrowWhenUpdatingNonExistentUser() {
        User nonExistentUser = new User(999L, "Ghost", "ghost@gmail.com","ghost2004!");

        assertNull(userService.updateUser(nonExistentUser));
    }
}
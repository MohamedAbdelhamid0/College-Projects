package org.example.service;

import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceTest {
    private UserService userService;
    private AuthenticationService authService;
    private final User validUser = new User(1L, "Valid User", "valid@gmail.com", "ValidPass123!");

    @BeforeEach
    void setUp() {
        userService = new UserService();
        authService = new AuthenticationService(userService);
        userService.addUser(validUser);
    }

    // Valid authentication tests
    @Test
    void authenticate_ValidCredentials_ReturnsUser() {

        validUser.setEmail("valid@gmail.com");
        validUser.setPassword("ValidPass123!");

        // When: Call the authenticate method with correct credentials
        Optional<User> result = authService.authenticate("valid@gmail.com", "ValidPass123!");

        // Then: Check if the result is present and the values are correct
        assertTrue(result.isPresent());
        assertEquals(validUser, result.get());  // Ensure validUser and the result match
    }


    @Test
    void authenticate_EmailCaseInsensitive_ReturnsUser() {
        validUser.setEmail("valid@gmail.com");
        validUser.setPassword("ValidPass123!");
        Optional<User> result = authService.authenticate("VALID@GMAIL.COM", "ValidPass123!");
        assertTrue(result.isPresent());
        assertEquals(validUser, result.get());
    }

    // Invalid password format tests
    @Test
    void authenticate_PasswordNoCapital_ReturnsEmpty() {
        Optional<User> result = authService.authenticate("valid@gmail.com", "invalid123!");
        assertTrue(result.isEmpty());
    }

    @Test
    void authenticate_PasswordNoExclamation_ReturnsEmpty() {
        Optional<User> result = authService.authenticate("valid@gmail.com", "Invalid123");
        assertTrue(result.isEmpty());
    }

    @Test
    void authenticate_PasswordTooShort_ReturnsEmpty() {
        Optional<User> result = authService.authenticate("valid@gmail.com", "Inv7!");
        assertTrue(result.isEmpty());
    }

    // Invalid email format tests
    @Test
    void authenticate_InvalidEmailDomain_ReturnsEmpty() {

        Optional<User> result = authService.authenticate("invalid@yahoo.com", "ValidPass123!");
        assertTrue(result.isEmpty());
    }

    @Test
    void authenticate_EmailMissingDomain_ReturnsEmpty() {
        Optional<User> result = authService.authenticate("invalid.com", "ValidPass123!");
        assertTrue(result.isEmpty());
    }

    // Existing tests with corrections
    @Test
    void authenticate_WrongPassword_ReturnsEmpty() {
        Optional<User> result = authService.authenticate("valid@gmail.com", "WrongPass123!");
        assertTrue(result.isEmpty());
    }

    @Test
    void authenticate_NonExistentEmail_ReturnsEmpty() {
        Optional<User> result = authService.authenticate("nonexistent@gmail.com", "AnyPass123!");
        assertTrue(result.isEmpty());
    }

    @Test
    void authenticate_NullEmail_ReturnsEmpty() {
        Optional<User> result = authService.authenticate(null, "ValidPass123!");
        assertTrue(result.isEmpty());
    }

    @Test
    void authenticate_NullPassword_ReturnsEmpty() {
        Optional<User> result = authService.authenticate("valid@gmail.com", null);
        assertTrue(result.isEmpty());
    }

    // Email existence tests
    @Test
    void emailExists_ValidFormat_ReturnsTrue() {
        assertTrue(authService.emailExists("valid@gmail.com"));
    }

    @Test
    void emailExists_CaseInsensitive_ReturnsTrue() {
        assertTrue(authService.emailExists("VALID@GMAIL.COM"));
    }

    @Test
    void emailExists_InvalidDomain_ReturnsFalse() {
        assertFalse(authService.emailExists("invalid@yahoo.com"));
    }

    @Test
    void emailExists_AfterUserRemoval_ReturnsFalse() {
        userService.deleteUser(validUser.getId());
        assertFalse(authService.emailExists("valid@gmail.com"));
    }
}
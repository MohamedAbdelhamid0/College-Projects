package org.example.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static User user;
    private long id;
    private String name;
    private String email;
    private String password;
    @BeforeAll
    static void setup() {
        user = new User();  // Initialize the static user variable
        user.setId(0l);
        user.setName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("Password!");
    }
    @Test
    @Order(1)
    void testsetId() {
        user.setId(00l);
        assertEquals(00l, user.getId());
    }
    @Test
    @Order(2)
    void testsetname(){
        user.setName("Seif");
        assertEquals("Seif", user.getName());
    }
    @Test
    @Order(3)
    void testsetemail(){
        user.setEmail("john@gmail.com");
        assertEquals("john@gmail.com", user.getEmail());
    }
    @Test
    void TestInvalidname() {


        // Test with an invalid name (non-string value, like an integer passed as a String)
        assertThrows(IllegalArgumentException.class, () -> {
            user.setName("99");  // Example: an integer as a string should be rejected if it's invalid
        });

        // Test with a null name
        assertThrows(NullPointerException.class, () -> {
            user.setName(null);
        });

        // Test with an empty name
        assertThrows(IllegalArgumentException.class, () -> {
            user.setName("");
        });
    }
    @Test
    void Testvalidname() {

        user.setName("John");
        assertEquals("John", user.getName());

    }
    @Test
    void Testvalidemail() {
        user.setEmail("seifh94@gmail.com");
        assertEquals("seifh94@gmail.com", user.getEmail());
    }
    @Test
    void Testinvalidemail() {
        // Test with an invalid email that doesn't end with '@gmail.com'
        assertThrows(IllegalArgumentException.class, () -> {
            user.setEmail("user@yahoo.com"); // Invalid email
        });

        // Test with an email that ends with '@gmail.com' but is malformed
        assertThrows(IllegalArgumentException.class, () -> {
            user.setEmail("user@gmail"); // Invalid email
        });

        // Test with a null email
        assertThrows(IllegalArgumentException.class, () -> {
            user.setEmail(null); // Invalid email
        });

        // Test with an empty email
        assertThrows(IllegalArgumentException.class, () -> {
            user.setEmail(""); // Invalid email
        });

    }
    @Test
    void testsetPassword(){

        String password = "Shuss2004!";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }
    void testwrongpassword(){

        // Test with an invalid password (no !)
        assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword("Seif2004");  // Example: an integer as a string should be rejected if it's invalid
        });
        //Test with a password not beginning with capital letter
        assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword("shuss2004!");

        });
        //Test with a password less than 8 characters
        assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword("Shu2004");
        });
        // Test with a null password
        assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword(null);
        });

        // Test with an empty password
        assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword("");
        });
    }

    @AfterAll
    static void tearDown() {
        User user = new User();
        user.setEmail("seif@gmail.com");
        user.setName("Seif");
        user.setId(12L);
        user.setPassword("Shuss2004!");
    }
}
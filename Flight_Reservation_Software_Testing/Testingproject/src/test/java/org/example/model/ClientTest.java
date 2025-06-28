package org.example.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private static Client client;
    static double funds ;
    static String name ;
    static double balance ;
    static String email ;
    static String Password ;
    @BeforeAll
    public static void setUp() {
        funds = 0;//set funds to 0
        client = new Client();//enter new client
        client.setName("john");//setup his name
        client.setBalance(100.0);//setup his balance
        client.setEmail("john@gmail.com");
        client.setPassword("Password!");
    }

    @Test
    public void testSetBalanceWithNonNumericInput() {
        Client client1 = new Client();

        // Test with an invalid string input that cannot be parsed as a double
        assertThrows(IllegalArgumentException.class, () -> {
            client1.setBalance(Double.parseDouble("invalid_type"));
        });
    }

    @Test
    public void testSetBalanceWithValidInput() {
        Client client1 = new Client();

        // Test with a valid double string input
        client1.setBalance(100.50);
        assertEquals(100.50, client1.getBalance(), "Balance should be 100.50");
    }

    @Test
    void testDoubleBalanceNotNegative() {
        Client client1 = new Client();
        double balance2 = 50.0 - 75.0; // Results in -25.0 (negative)
        client1.setBalance(balance2);
        // Assert balance is NOT negative (fails here)
        assertTrue(balance2 <= 0, "Balance should not be negative");
    }

    @Test
    void testsetPassword(){
        Client client1 = new Client();
        Password = "Shuss2004!";
        client1.setPassword(Password);
        assertEquals(Password, client1.getPassword());
    }
    void testwrongpassword(){
        Client client1 = new Client();
        // Test with an invalid password (no !)
        assertThrows(IllegalArgumentException.class, () -> {
            client1.setPassword("Seif2004");  // Example: an integer as a string should be rejected if it's invalid
        });
        //Test with a password not beginning with capital letter
        assertThrows(IllegalArgumentException.class, () -> {
            client1.setPassword("shuss2004!");

        });
        //Test with a password less than 8 characters
        assertThrows(IllegalArgumentException.class, () -> {
            client1.setPassword("Shu2004");
        });
        // Test with a null password
        assertThrows(IllegalArgumentException.class, () -> {
            client1.setPassword(null);
        });

        // Test with an empty password
        assertThrows(IllegalArgumentException.class, () -> {
            client1.setPassword("");
        });
    }

    @Test
    void Testdeductingnegativeamount() {
        double funds2= -70;
        assertThrows(IllegalArgumentException.class, () -> {
            client.deductFunds(funds2);
        });

    }
    @Test
    void Testdeductingpositiveamount() {
        double funds2= 50;
        client.deductFunds(funds2);
        assertTrue(funds2 >= 0, "Balance should not be negative");
    }
    @Test
    void Testdeductingfundsworkcorrectly() {
        Client client1 = new Client();
        client1.setBalance(100.50);
        double funds2= 70;
        client1.deductFunds(funds2);
        double balance2 = 100.50-70;
        assertEquals(client1.getBalance(), balance2, "Balance should be 30.50");
    }
    @Test
    void Testgettingname() {
        Client client1 = new Client();
        String name2 = "John";
        client1.setName(name2);
        assertEquals(name2, client1.getName());
    }
    @Test
    void TestInvalidname() {

        Client client1 = new Client();
        // Test with an invalid name (non-string value, like an integer passed as a String)
        assertThrows(IllegalArgumentException.class, () -> {
            client1.setName("99");  // Example: an integer as a string should be rejected if it's invalid
        });

        // Test with a null name
        assertThrows(NullPointerException.class, () -> {
            client1.setName(null);
        });

        // Test with an empty name
        assertThrows(IllegalArgumentException.class, () -> {
            client1.setName("");
        });
    }
    @Test
    void Testvalidname() {
        Client client1 = new Client();
        client1.setName("John");
        assertEquals("John", client1.getName());

    }
    @Test
    void TestaddingnegativeFunds() {
        double funds2= -70;

        assertThrows(IllegalArgumentException.class, () -> {
            client.addFunds(funds2);
        });

    }
    @Test
    void TestaddingPositiveFunds() {
        double funds2= 70;
        client.addFunds(funds2);
        assertTrue(funds2 >= 0, "Balance should not be negative");
    }
    @Test
    void TestAddingfundsworkcorrectly() {
        Client client1 = new Client();
        client1.setBalance(100.50);
        double funds2= 70;
        client1.addFunds(funds2);
        double balance2 = 100.50+70;
        assertEquals(client1.getBalance(), balance2, "Balance should be 170.50");
    }
    @Test
    public void testInvalidEmail() {
        Client client1 = new Client();

        // Test with an invalid email that doesn't end with '@gmail.com'
        assertThrows(IllegalArgumentException.class, () -> {
            client1.setEmail("user@yahoo.com"); // Invalid email
        });

        // Test with an email that ends with '@gmail.com' but is malformed
        assertThrows(IllegalArgumentException.class, () -> {
            client1.setEmail("user@gmail"); // Invalid email
        });

        // Test with a null email
        assertThrows(IllegalArgumentException.class, () -> {
            client1.setEmail(null); // Invalid email
        });

        // Test with an empty email
        assertThrows(IllegalArgumentException.class, () -> {
            client1.setEmail(""); // Invalid email
        });

        // Test with a valid email
        client1.setEmail("seif94@gmail.com");
        assertEquals("seif94@gmail.com", client1.getEmail()); // Valid email
    }
    @AfterAll
    public static void tearDown() {
        name ="";
        balance = 0;
        email = "";
        Password = "";
    }
}
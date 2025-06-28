package org.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/registration.fxml")); // Adjust the path if needed
        stage.setScene(new Scene(root));
        stage.show();
    }

    @BeforeEach
    public void setUp() {
        // Optional setup logic before each test
    }

    @Test
    public void testSuccessfulRegistration() {
        clickOn("#usernameField").write("newuser");
        clickOn("#passwordField").write("newpass");
        clickOn("#confirmPasswordField").write("newpass");
        clickOn("#emailField").write("newuser@example.com");
        clickOn("#registerButton");

        // Assuming there's a label that shows success message
        String success = lookup("#successLabel").queryLabeled().getText();
        assertEquals("Account created successfully", success);
    }

    @Test
    public void testMismatchedPasswords() {
        clickOn("#usernameField").write("user123");
        clickOn("#passwordField").write("pass1");
        clickOn("#confirmPasswordField").write("pass2");
        clickOn("#registerButton");

        String error = lookup("#errorLabel").queryLabeled().getText();
        assertEquals("Passwords do not match", error);
    }

    @Test
    public void testEmptyFields() {
        clickOn("#registerButton");

        String error = lookup("#errorLabel").queryLabeled().getText();
        assertEquals("Please fill in all fields", error);
    }
}
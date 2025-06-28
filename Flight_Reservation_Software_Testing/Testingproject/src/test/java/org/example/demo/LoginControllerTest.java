package org.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class LoginControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml")); // Make sure the path is correct
        stage.setScene(new Scene(root));
        stage.show();
    }

    @BeforeEach
    public void setUp() {
        // Optional setup before each test
    }

    @Test
    public void testLoginSuccess() {
        clickOn("#usernameField").write("admin");
        clickOn("#passwordField").write("adminpass");
        clickOn("#loginButton");

        // Assuming the label with fx:id="welcomeLabel" gets updated on success
        String message = lookup("#welcomeLabel").queryLabeled().getText();
        assertEquals("Welcome admin", message);
    }

    @Test
    public void testLoginEmptyFields() {
        clickOn("#loginButton");

        // Assuming there's a label with fx:id="errorLabel" for validation messages
        String error = lookup("#errorLabel").queryLabeled().getText();
        assertEquals("Please enter username and password", error);
    }

    @Test
    public void testLoginInvalidCredentials() {
        clickOn("#usernameField").write("wronguser");
        clickOn("#passwordField").write("wrongpass");
        clickOn("#loginButton");

        String error = lookup("#errorLabel").queryLabeled().getText();
        assertEquals("Invalid username or password", error);
    }
}
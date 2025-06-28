package org.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class ClientDashboardControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/client_dashboard.fxml")); // Adjust the path if needed
        stage.setScene(new Scene(root));
        stage.show();
    }

    @BeforeEach
    public void setUp() {
        // Optional reset or preparation before tests
    }

    @Test
    public void testWelcomeLabelIsDisplayed() {
        String welcome = lookup("#welcomeLabel").queryLabeled().getText();
        assertEquals("Welcome, Client!", welcome); // Adjust expected text if needed
    }

    @Test
    public void testViewMenuButton() {
        clickOn("#viewMenuButton");

        // Assuming menu table becomes visible or label updates
        String menuHeader = lookup("#menuHeaderLabel").queryLabeled().getText();
        assertEquals("Menu", menuHeader);
    }

    @Test
    public void testLogoutButton() {
        clickOn("#logoutButton");

        // You can verify if the scene changed, or if a specific label/text disappeared
        // Placeholder logic here:
        boolean loggedOut = !lookup("#clientDashboardPane").tryQuery().isPresent();
        assertTrue(loggedOut);
    }
}
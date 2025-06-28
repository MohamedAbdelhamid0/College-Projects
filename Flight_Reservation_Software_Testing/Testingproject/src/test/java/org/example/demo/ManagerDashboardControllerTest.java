package org.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerDashboardControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/manager_dashboard.fxml")); // Adjust if needed
        stage.setScene(new Scene(root));
        stage.show();
    }

    @BeforeEach
    public void setUp() {
        // Reset logic before tests if necessary
    }

    @Test
    public void testManagerWelcomeMessage() {
        String welcome = lookup("#welcomeLabel").queryLabeled().getText();
        assertEquals("Welcome, Manager!", welcome); // Update expected value if different
    }

    @Test
    public void testViewOrdersButton() {
        clickOn("#viewOrdersButton");

        // Assume label or table appears when orders are shown
        String label = lookup("#ordersLabel").queryLabeled().getText();
        assertEquals("All Orders", label);
    }

    @Test
    public void testLogout() {
        clickOn("#logoutButton");

        // Example: manager pane disappears or login pane is shown
        boolean loggedOut = !lookup("#managerDashboardPane").tryQuery().isPresent();
        assertTrue(loggedOut);
    }
}
package org.example.demo;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.model.Client;
import org.example.model.Manager;
import org.example.model.User;
import org.example.service.AuthenticationService;

import java.util.Optional;

public class LoginController {

    private TextField emailField;
    private PasswordField passwordField;
    private Label errorLabel;
    private Button loginButton;
    private Button registerButton;

    private final MainApplication mainApp;
    private final AuthenticationService authService;

    public LoginController(MainApplication mainApp) {
        this.mainApp = mainApp;
        this.authService = mainApp.getAuthenticationService();
    }

    public Parent createLoginPane() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);
        grid.setPadding(new Insets(25, 25, 25, 25));
        //grid.setGridLinesVisible(true); // For debugging layout

        Label sceneTitle = new Label("E-Commerce Login");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1); // Span 2 columns

        Label emailLabel = new Label("Email:");
        grid.add(emailLabel, 0, 1);

        emailField = new TextField();
        emailField.setPromptText("Enter your email");
        grid.add(emailField, 1, 1);

        Label pwLabel = new Label("Password:");
        grid.add(pwLabel, 0, 2);

        passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        grid.add(passwordField, 1, 2);

        loginButton = new Button("Sign in");
        registerButton = new Button("Register");

        HBox hbButtons = new HBox(10); // Spacing between buttons
        hbButtons.setAlignment(Pos.BOTTOM_RIGHT);
        hbButtons.getChildren().addAll(registerButton, loginButton);
        grid.add(hbButtons, 1, 4);

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
        grid.add(errorLabel, 0, 5, 2, 1);

        // --- Event Handlers ---
        loginButton.setOnAction(e -> handleLogin());
        registerButton.setOnAction(e -> handleRegister());
        // Allow pressing Enter in password field to trigger login
        passwordField.setOnAction(e -> handleLogin());

        // --- Initial State ---
        hideError();

        // Make the grid take up available space if needed (optional)
        VBox container = new VBox(grid);
        container.setAlignment(Pos.CENTER);
        VBox.setVgrow(grid, Priority.ALWAYS);

        return container; // Return the container holding the grid
    }

    private void handleLogin() {
        hideError();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showError("Email and password cannot be empty.");
            return;
        }

        Optional<User> userOptional = authService.authenticate(email, password);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Use Platform.runLater for UI updates triggered by service calls (good practice)
            Platform.runLater(() -> {
                if (user instanceof Client) {
                    mainApp.showClientDashboard((Client) user);
                } else if (user instanceof Manager) {
                    mainApp.showManagerDashboard((Manager) user);
                } else {
                    showError("Login successful, but user type is unknown."); // Should not happen
                }
            });
        } else {
            showError("Invalid email or password.");
        }
    }

    private void handleRegister() {
        hideError();
        mainApp.showRegistrationScreen();
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
        // errorLabel.setManaged(true); // Managed property might not be needed if always in layout
    }

    private void hideError() {
        errorLabel.setText("");
        errorLabel.setVisible(false);
        // errorLabel.setManaged(false);
    }
}
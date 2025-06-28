package org.example.demo;

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
import org.example.service.AuthenticationService;
import org.example.service.CartService;
import org.example.service.UserService;

public class RegisterationController {

    private TextField nameField;
    private TextField emailField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private Label errorLabel;
    private Button registerButton;
    private Button backButton;

    private final MainApplication mainApp;
    private final UserService userService;
    private final AuthenticationService authService;
    private final CartService cartService;

    public RegisterationController(MainApplication mainApp) {
        this.mainApp = mainApp;
        this.userService = mainApp.getUserService();
        this.authService = mainApp.getAuthenticationService();
        this.cartService = mainApp.getCartService(); // Need CartService to create cart on registration
    }

    public Parent createRegistrationPane() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label sceneTitle = new Label("Create New Account");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        grid.add(new Label("Full Name:"), 0, 1);
        nameField = new TextField();
        nameField.setPromptText("Enter your full name");
        grid.add(nameField, 1, 1);

        grid.add(new Label("Email:"), 0, 2);
        emailField = new TextField();
        emailField.setPromptText("Enter a valid email");
        grid.add(emailField, 1, 2);

        grid.add(new Label("Password:"), 0, 3);
        passwordField = new PasswordField();
        passwordField.setPromptText("Choose a password");
        grid.add(passwordField, 1, 3);

        grid.add(new Label("Confirm Password:"), 0, 4);
        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm your password");
        grid.add(confirmPasswordField, 1, 4);

        registerButton = new Button("Register");
        backButton = new Button("Back to Login");

        HBox hbButtons = new HBox(10);
        hbButtons.setAlignment(Pos.BOTTOM_RIGHT);
        hbButtons.getChildren().addAll(backButton, registerButton);
        grid.add(hbButtons, 1, 5);

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
        grid.add(errorLabel, 0, 6, 2, 1);

        // --- Event Handlers ---
        registerButton.setOnAction(e -> handleRegister());
        backButton.setOnAction(e -> handleBackToLogin());
        // Allow pressing Enter in confirm password field to trigger registration
        confirmPasswordField.setOnAction(e -> handleRegister());


        // --- Initial State ---
        hideError();

        VBox container = new VBox(grid);
        container.setAlignment(Pos.CENTER);
        VBox.setVgrow(grid, Priority.ALWAYS);

        return container;
    }

    private void handleRegister() {
        hideError();
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Basic Validation
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showError("All fields are required.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match.");
            return;
        }

        // Use Model Validation (implicitly via setters)
        Client newClient = new Client();
        try {
            newClient.setId(MainApplication.userIdGenerator.getAndIncrement()); // Use simple generator from MainApp
            newClient.setName(name);
            newClient.setEmail(email); // Will throw if format is invalid
            newClient.setPassword(password); // Will throw if format is invalid
            newClient.setBalance(0.0); // Default balance

            // Check if email already exists using AuthenticationService
            if (authService.emailExists(email)) {
                showError("Email address is already registered.");
                return;
            }

            // Add user via UserService
            userService.addUser(newClient);

            // Important: Also create a cart for the new client
            cartService.createCart(newClient);

            mainApp.showInfoAlert("Registration Successful", "Account created successfully. Please log in.");
            mainApp.showLoginScreen(); // Go back to login screen

        } catch (IllegalArgumentException e) {
            showError("Registration failed: " + e.getMessage());
        } catch (Exception e) {
            showError("An unexpected error occurred during registration.");
            System.err.println("Registration Error: " + e.getMessage());
            e.printStackTrace(); // Log unexpected errors
        }
    }

    private void handleBackToLogin() {
        hideError();
        mainApp.showLoginScreen();
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    private void hideError() {
        errorLabel.setText("");
        errorLabel.setVisible(false);
    }
}
package org.example.demo; // Adjust package if needed

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.example.model.*; // Import all models
import org.example.service.*; // Import all services



import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class MainApplication extends Application {

    private Stage primaryStage;

    // --- Service Instances (Initialized once) ---
    private UserService userService;
    private AuthenticationService authenticationService;
    private ProductService productService;
    private CategoryService categoryService;
    private CartService cartService;
    private OrderService orderService;
    private PaymentService paymentService;

    // --- Current User ---
    private User currentUser;

    // --- Simple ID Generators (Workaround for unmodifiable services) ---
    // Keep these accessible, perhaps make them package-private or provide getters if needed by controllers directly
    static final AtomicLong categoryIdGenerator = new AtomicLong(100); // Start high to avoid potential clashes
    static final AtomicLong productIdGenerator = new AtomicLong(1000);
    static final AtomicLong userIdGenerator = new AtomicLong(10); // For new registrations
    static final AtomicLong orderIdGenerator = new AtomicLong(5000);
    static final AtomicLong cartItemIdGenerator = new AtomicLong(10000);

    @Override
    public void init() throws Exception {
        // Initialize Services
        userService = new UserService();
        authenticationService = new AuthenticationService(userService);
        productService = new ProductService();
        categoryService = new CategoryService();
        cartService = new CartService();
        orderService = new OrderService();
        paymentService = new PaymentService();

        // --- Pre-populate data for demonstration ---
        populateInitialData();
    }

    private void populateInitialData() {
        // Add some categories
        try {
            Category electronics = new Category(1L, "Electronics", "Gadgets and devices");
            Category clothing = new Category(2L, "Clothing", "Apparel and accessories");
            categoryService.addCategory(electronics);
            categoryService.addCategory(clothing);

            // Add some products
            productService.addProduct(new Product(101L, "Laptop", "High-performance laptop", 1200.00, 10, electronics));
            productService.addProduct(new Product(102L, "Smartphone", "Latest model smartphone", 800.00, 25, electronics));
            productService.addProduct(new Product(103L, "T-Shirt", "Cotton T-shirt", 25.00, 50, clothing));
            productService.addProduct(new Product(104L, "Jeans", "Blue denim jeans", 60.00, 30, clothing));
        } catch (IllegalArgumentException e) {
            System.err.println("Error adding initial categories/products: " + e.getMessage());
        }

        // Add a default Manager and Client
        try {
            Manager manager = new Manager(1L, "Admin Manager", "manager@gmail.com", "Adminpass!", "Sales", 0);
            manager.setSalary(manager.getDepartment()); // Calculate salary based on department
            userService.addUser(manager);


            Client client = new Client(2L, "Test Client", "client@gmail.com", "Clientpass!", 100.0);
            userService.addUser(client);

            // Give the client a cart
            cartService.createCart(client);

        } catch (IllegalArgumentException e) {
            System.err.println("Error adding initial users: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error during initial data population: " + e.getMessage());
            e.printStackTrace(); // Log stack trace for unexpected errors
        }
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("E-Commerce Application");

        showLoginScreen();
        primaryStage.show();
    }

    // --- Scene Navigation Methods (Programmatic UI) ---

    private void switchScene(Parent rootNode, String title) {
        Scene scene = primaryStage.getScene();
        if (scene == null) {
            scene = new Scene(rootNode, 800, 600); // Default size, adjust as needed
            primaryStage.setScene(scene);
        } else {
            scene.setRoot(rootNode);
        }
        primaryStage.setTitle(title);
        primaryStage.sizeToScene(); // Adjust stage size to fit content
        primaryStage.centerOnScreen();
    }

    public void showLoginScreen() {
        LoginController controller = new LoginController(this);
        Parent root = controller.createLoginPane();
        switchScene(root, "Login");
    }

    public void showRegistrationScreen() {
        RegisterationController controller = new RegisterationController(this);
        Parent root = controller.createRegistrationPane();
        switchScene(root, "Register");
    }

    public void showClientDashboard(Client client) {
        this.currentUser = client;
        ClientDashboardController controller = new ClientDashboardController(this, client);
        Parent root = controller.createDashboardView();
        switchScene(root, "Client Dashboard - " + client.getName());
    }

    public void showManagerDashboard(Manager manager) {
        this.currentUser = manager;
        ManagerDashboardController controller = new ManagerDashboardController(this, manager);
        Parent root = controller.createDashboardView();
        switchScene(root, "Manager Dashboard - " + manager.getName());
    }


    // --- Utility Methods ---
    public void logout() {
        this.currentUser = null;
        showLoginScreen();
    }

    // Run Alerts on JavaFX Application Thread
    private void runOnFxThread(Runnable action) {
        if (Platform.isFxApplicationThread()) {
            action.run();
        } else {
            Platform.runLater(action);
        }
    }

    public void showErrorAlert(String title, String content) {
        runOnFxThread(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }

    public void showInfoAlert(String title, String content) {
        runOnFxThread(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }

    // Confirmation needs to block, so it should ideally be called from FX thread.
    // If called from another thread, runAndWait might be needed, but showAndWait usually handles it.
    public Optional<ButtonType> showConfirmationAlert(String title, String content) {
        // This method blocks, so it's tricky if called from non-FX thread.
        // Assuming it's called from event handlers which are on the FX thread.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert.showAndWait();
    }


    // --- Getters for Services (for controllers) ---
    public UserService getUserService() { return userService; }
    public AuthenticationService getAuthenticationService() { return authenticationService; }
    public ProductService getProductService() { return productService; }
    public CategoryService getCategoryService() { return categoryService; }
    public CartService getCartService() { return cartService; }
    public OrderService getOrderService() { return orderService; }
    public PaymentService getPaymentService() { return paymentService; }
    public User getCurrentUser() { return currentUser; }


    // --- Main Method ---
    public static void main(String[] args) {
        // Ensure necessary Model/Service classes are available
        // You might need to manually instantiate or load them if using dependency injection frameworks later
        System.out.println("Launching Application...");
        launch(args);
    }
}
package org.example.ui;

import org.example.model.*;
import org.example.service.*;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class EcommerceApp extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final AuthenticationService authService;
    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private Client loggedInClient;
    private Cart currentCart;

    public EcommerceApp() {
        // Initialize services
        userService = new UserService();
        authService = new AuthenticationService(userService);
        productService = new ProductService();
        cartService = new CartService();
        orderService = new OrderService();
        paymentService = new PaymentService();

        // Populate some sample data (for testing)
        initializeSampleData();

        // Set up the main window
        setTitle("E-Commerce Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Set up CardLayout for switching panels
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add panels
        mainPanel.add(createLoginPanel(), "Login");
        mainPanel.add(createProductPanel(), "Products");
        mainPanel.add(createCartPanel(), "Cart");

        // Show the login panel first
        cardLayout.show(mainPanel, "Login");

        // Add the main panel to the frame
        add(mainPanel);
    }

    private void initializeSampleData() {
        // Add a sample user (Client)
        Client client = new Client(1L, "John Doe", "john@gmail.com", "Password!123", 1000.0);
        userService.addUser(client); // Client extends User

        // Add a sample category
        Category category = new Category(1L, "Electronics", "Electronic gadgets");
        productService.addProduct(new Product(Long.valueOf(1), "Laptop", "High-performance laptop", 999.99, 10, category));
        productService.addProduct(new Product(Long.valueOf(2), "Phone", "Latest smartphone", 699.99, 20, category));
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            Optional<User> userOptional = authService.authenticate(email, password);
            if (userOptional.isPresent() && userOptional.get() instanceof Client) {
                loggedInClient = (Client) userOptional.get();
                currentCart = cartService.getOrCreateCart(loggedInClient); // Client extends User
                JOptionPane.showMessageDialog(this, "Login successful! Welcome, " + loggedInClient.getName());
                cardLayout.show(mainPanel, "Products");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password.");
            }
        });

        return panel;
    }

    private JPanel createProductPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Product list
        DefaultListModel<Product> productListModel = new DefaultListModel<>();
        JList<Product> productList = new JList<>(productListModel);
        productList.setCellRenderer(new ProductListRenderer());
        JScrollPane scrollPane = new JScrollPane(productList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Refresh product list
        refreshProductList(productListModel);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton addToCartButton = new JButton("Add to Cart");
        JButton viewCartButton = new JButton("View Cart");

        buttonPanel.add(addToCart
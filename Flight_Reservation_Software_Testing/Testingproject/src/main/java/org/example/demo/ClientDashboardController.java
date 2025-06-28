package org.example.demo;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.model.*;
import org.example.service.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientDashboardController {

    // Main Layout
    private BorderPane mainBorderPane;
    private VBox leftSidebar;
    private StackPane centerStackPane; // To switch between views

    // Sidebar Components
    private Label welcomeLabel;
    private Label clientEmailLabel;
    private Label clientIdLabel;
    private Label balanceLabel;
    private Button viewProductsButton, viewCartButton, viewOrdersButton, profileButton, logoutButton;

    // Center Views (Containers for different sections)
    private Node productsView;
    private Node cartView;
    private Node ordersView;
    private Node profileView;

    // Products View Components
    private ComboBox<Category> categoryFilterComboBox;
    private TextField productSearchField;
    private TableView<Product> productsTable;
    private Spinner<Integer> quantitySpinner;
    private Button addToCartButton;
    private Label productSelectionErrorLabel;

    // Cart View Components
    private TableView<CartItem> cartTable;
    private Label cartTotalLabel;
    private Button removeFromCartButton;
    private Button clearCartButton;
    private Button checkoutButton;
    private Label cartActionErrorLabel;

    // Orders View Components
    private TableView<Order> ordersTable;
    // (Potentially add order detail view components later)

    // Profile View Components
    private TextField profileNameField;
    private TextField profileEmailField;
    private PasswordField profileCurrentPasswordField;
    private PasswordField profileNewPasswordField;
    private PasswordField profileConfirmPasswordField;
    private Button updateProfileButton;
    private TextField depositAmountField;
    private Button depositButton;
    private Label profileErrorLabel;

    // Services and State
    private final MainApplication mainApp;
    private final Client currentClient;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CartService cartService;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final UserService userService;

    // Data Lists for Tables
    private ObservableList<Product> productMasterList = FXCollections.observableArrayList();
    private FilteredList<Product> productFilteredList;
    private ObservableList<CartItem> cartItemsList = FXCollections.observableArrayList();
    private ObservableList<Order> ordersList = FXCollections.observableArrayList();

    // Constructor
    public ClientDashboardController(MainApplication mainApp, Client client) {
        this.mainApp = mainApp;
        this.currentClient = client;
        this.productService = mainApp.getProductService();
        this.categoryService = mainApp.getCategoryService();
        this.cartService = mainApp.getCartService();
        this.orderService = mainApp.getOrderService();
        this.paymentService = mainApp.getPaymentService();
        this.userService = mainApp.getUserService();
    }

    // Method to create the entire dashboard UI
    public Parent createDashboardView() {
        mainBorderPane = new BorderPane();

        // --- Left Sidebar ---
        createLeftSidebar();
        mainBorderPane.setLeft(leftSidebar);

        // --- Center Area ---
        centerStackPane = new StackPane();
        mainBorderPane.setCenter(centerStackPane);

        // --- Create Views (but don't show them yet) ---
        productsView = createProductsView();
        cartView = createCartView();
        ordersView = createOrdersView();
        profileView = createProfileView();

        // Add all views to the StackPane, only one will be visible at a time
        centerStackPane.getChildren().addAll(productsView, cartView, ordersView, profileView);

        // --- Initial Setup ---
        updateClientInfoSidebar(); // Populate sidebar info
        switchToView(productsView); // Start with products view
        loadProducts(); // Load initial product data

        return mainBorderPane;
    }

    // --- UI Creation Methods ---

    private void createLeftSidebar() {
        leftSidebar = new VBox(15); // Spacing between elements
        leftSidebar.setPadding(new Insets(15));
        leftSidebar.setMinWidth(200);
        leftSidebar.setStyle("-fx-background-color: #f0f0f0;"); // Simple background

        welcomeLabel = new Label();
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        clientEmailLabel = new Label();
        clientIdLabel = new Label();
        balanceLabel = new Label();
        balanceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        viewProductsButton = new Button("View Products");
        viewCartButton = new Button("View Cart");
        viewOrdersButton = new Button("View Orders");
        profileButton = new Button("My Profile");
        logoutButton = new Button("Logout");

        // Set common button width
        double btnWidth = 150;
        viewProductsButton.setPrefWidth(btnWidth);
        viewCartButton.setPrefWidth(btnWidth);
        viewOrdersButton.setPrefWidth(btnWidth);
        profileButton.setPrefWidth(btnWidth);
        logoutButton.setPrefWidth(btnWidth);


        // Button Actions
        viewProductsButton.setOnAction(e -> { loadProducts(); switchToView(productsView); });
        viewCartButton.setOnAction(e -> { loadCart(); switchToView(cartView); });
        viewOrdersButton.setOnAction(e -> { loadOrders(); switchToView(ordersView); });
        profileButton.setOnAction(e -> { loadProfileData(); switchToView(profileView); });
        logoutButton.setOnAction(e -> mainApp.logout());

        // Add elements to sidebar
        leftSidebar.getChildren().addAll(
                welcomeLabel,
                clientIdLabel,
                clientEmailLabel,
                new Separator(),
                balanceLabel,
                new Separator(),
                viewProductsButton,
                viewCartButton,
                viewOrdersButton,
                profileButton,
                new Separator(),
                logoutButton
        );
    }

    private Node createProductsView() {
        VBox view = new VBox(10);
        view.setPadding(new Insets(15));

        Label title = new Label("Available Products");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // --- Filtering/Search Row ---
        HBox filterBox = new HBox(10);
        filterBox.setAlignment(Pos.CENTER_LEFT);

        categoryFilterComboBox = new ComboBox<>();
        productSearchField = new TextField();
        productSearchField.setPromptText("Search by name, description...");
        HBox.setHgrow(productSearchField, Priority.ALWAYS); // Make search field expand

        filterBox.getChildren().addAll(new Label("Category:"), categoryFilterComboBox, new Label("Search:"), productSearchField);

        // --- Products Table ---
        productsTable = new TableView<>();
        setupProductsTableColumns(); // Separate method for clarity
        VBox.setVgrow(productsTable, Priority.ALWAYS); // Table should grow vertically

        // --- Add to Cart Row ---
        HBox addBox = new HBox(10);
        addBox.setAlignment(Pos.CENTER_LEFT);
        quantitySpinner = new Spinner<>(1, 100, 1); // Min, Max, Initial
        quantitySpinner.setPrefWidth(70);
        addToCartButton = new Button("Add to Cart");
        productSelectionErrorLabel = new Label();
        productSelectionErrorLabel.setStyle("-fx-text-fill: red;");

        addBox.getChildren().addAll(new Label("Quantity:"), quantitySpinner, addToCartButton, productSelectionErrorLabel);

        // --- Setup Filtering/Event Handlers ---
        setupProductFilteringAndActions();

        view.getChildren().addAll(title, filterBox, productsTable, addBox);
        return view;
    }

    private void setupProductsTableColumns() {
        TableColumn<Product, Long> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(60);

        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(150);

        TableColumn<Product, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descCol.setPrefWidth(250);

        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                setText(empty || price == null ? null : String.format("$%.2f", price));
            }
        });
        priceCol.setPrefWidth(80);


        TableColumn<Product, Integer> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        stockCol.setPrefWidth(60);

        TableColumn<Product, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(cellData -> {
            Category category = cellData.getValue().getCategory();
            return new SimpleStringProperty(category != null ? category.getName() : "N/A");
        });
        categoryCol.setPrefWidth(100);

        productsTable.getColumns().addAll(idCol, nameCol, descCol, priceCol, stockCol, categoryCol);
        productsTable.setPlaceholder(new Label("No products found."));
    }

    private void setupProductFilteringAndActions() {
        // Load Categories for Filter
        Category allCategories = new Category(0L, "All Categories", ""); // Dummy category
        categoryFilterComboBox.getItems().add(allCategories);
        categoryFilterComboBox.getItems().addAll(categoryService.getAllCategories());
        categoryFilterComboBox.setValue(allCategories); // Default

        // Setup Filtering logic
        productMasterList.setAll(productService.getAllProducts());
        productFilteredList = new FilteredList<>(productMasterList, p -> true);
        productsTable.setItems(productFilteredList);

        // Listeners for filtering
        categoryFilterComboBox.valueProperty().addListener((obs, oldVal, newVal) -> applyProductFilters());
        productSearchField.textProperty().addListener((obs, oldVal, newVal) -> applyProductFilters());

        // Action for Add to Cart button
        addToCartButton.setOnAction(e -> handleAddToCart());

        hideProductError(); // Initial state
    }

    private Node createCartView() {
        VBox view = new VBox(10);
        view.setPadding(new Insets(15));

        Label title = new Label("Your Shopping Cart");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // --- Cart Table ---
        cartTable = new TableView<>();
        setupCartTableColumns();
        VBox.setVgrow(cartTable, Priority.ALWAYS);

        // --- Cart Summary and Actions ---
        HBox summaryBox = new HBox(20);
        summaryBox.setAlignment(Pos.CENTER_LEFT);
        cartTotalLabel = new Label("Total: $0.00");
        cartTotalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Region spacer = new Region(); // Pushes buttons to the right
        HBox.setHgrow(spacer, Priority.ALWAYS);
        removeFromCartButton = new Button("Remove Selected");
        clearCartButton = new Button("Clear Cart");
        checkoutButton = new Button("Checkout");
        checkoutButton.setStyle("-fx-font-weight: bold;");

        summaryBox.getChildren().addAll(cartTotalLabel, spacer, removeFromCartButton, clearCartButton, checkoutButton);

        cartActionErrorLabel = new Label();
        cartActionErrorLabel.setStyle("-fx-text-fill: red;");

        // --- Event Handlers ---
        removeFromCartButton.setOnAction(e -> handleRemoveFromCart());
        clearCartButton.setOnAction(e -> handleClearCart());
        checkoutButton.setOnAction(e -> handleCheckout());

        view.getChildren().addAll(title, cartTable, summaryBox, cartActionErrorLabel);
        hideCartError(); // Initial state
        return view;
    }

    private void setupCartTableColumns() {
        TableColumn<CartItem, Long> idCol = new TableColumn<>("Item ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id")); // Use CartItem ID if available
        idCol.setPrefWidth(80);

        TableColumn<CartItem, String> nameCol = new TableColumn<>("Product");
        nameCol.setCellValueFactory(cellData -> {
            Product p = cellData.getValue().getProduct();
            return new SimpleStringProperty(p != null ? p.getName() : "Unknown Product");
        });
        nameCol.setPrefWidth(200);

        TableColumn<CartItem, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityCol.setPrefWidth(80);

        TableColumn<CartItem, Double> priceCol = new TableColumn<>("Unit Price");
        priceCol.setCellValueFactory(cellData -> {
            Product p = cellData.getValue().getProduct();
            return p != null ? new SimpleDoubleProperty(p.getPrice()).asObject() : null;
        });
        priceCol.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                setText(empty || price == null ? null : String.format("$%.2f", price));
            }
        });
        priceCol.setPrefWidth(100);


        TableColumn<CartItem, Double> subtotalCol = new TableColumn<>("Subtotal");
        subtotalCol.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getSubTotal()).asObject());
        subtotalCol.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Double subtotal, boolean empty) {
                super.updateItem(subtotal, empty);
                setText(empty || subtotal == null ? null : String.format("$%.2f", subtotal));
            }
        });
        subtotalCol.setPrefWidth(100);

        cartTable.getColumns().addAll(idCol, nameCol, quantityCol, priceCol, subtotalCol);
        cartTable.setItems(cartItemsList); // Link data list
        cartTable.setPlaceholder(new Label("Your cart is empty."));
    }


    private Node createOrdersView() {
        VBox view = new VBox(10);
        view.setPadding(new Insets(15));

        Label title = new Label("Your Order History");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        ordersTable = new TableView<>();
        setupOrdersTableColumns();
        VBox.setVgrow(ordersTable, Priority.ALWAYS);

        // TODO: Add functionality to view order details if needed

        view.getChildren().addAll(title, ordersTable);
        return view;
    }

    private void setupOrdersTableColumns() {
        TableColumn<Order, Long> idCol = new TableColumn<>("Order ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(80);

        TableColumn<Order, String> dateCol = new TableColumn<>("Date Placed");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dateCol.setCellValueFactory(cellData -> {
            LocalDateTime date = cellData.getValue().getOrderDate();
            return new SimpleStringProperty(date != null ? date.format(formatter) : "N/A");
        });
        dateCol.setPrefWidth(150);

        TableColumn<Order, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(100);

        TableColumn<Order, Double> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(cellData -> {
            // Recalculate total from items if available
            Order order = cellData.getValue();
            double total = 0.0;
            if (order != null && order.getOrderItems() != null) {
                for (OrderItem item : order.getOrderItems()) {
                    if (item.getPriceAtPurchase() != null) {
                        total += item.getPriceAtPurchase().doubleValue() * item.getQuantity();
                    }
                }
            }
            return new SimpleDoubleProperty(total).asObject();
        });
        totalCol.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Double total, boolean empty) {
                super.updateItem(total, empty);
                setText(empty || total == null ? null : String.format("$%.2f", total));
            }
        });
        totalCol.setPrefWidth(100);


        ordersTable.getColumns().addAll(idCol, dateCol, statusCol, totalCol);
        ordersTable.setItems(ordersList); // Link data list
        ordersTable.setPlaceholder(new Label("You have not placed any orders yet."));
    }

    private Node createProfileView() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25));
        grid.setHgap(10);
        grid.setVgap(15);
        //grid.setGridLinesVisible(true);

        Label title = new Label("Your Profile");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        grid.add(title, 0, 0, 2, 1);

        // --- Basic Info ---
        grid.add(new Label("Name:"), 0, 1);
        profileNameField = new TextField();
        grid.add(profileNameField, 1, 1);

        grid.add(new Label("Email:"), 0, 2);
        profileEmailField = new TextField();
        grid.add(profileEmailField, 1, 2);

        updateProfileButton = new Button("Update Name/Email");
        grid.add(updateProfileButton, 1, 3);

        grid.add(new Separator(), 0, 4, 2, 1); // --- Separator ---

        // --- Change Password ---
        Label passTitle = new Label("Change Password");
        passTitle.setFont(Font.font(null, FontWeight.BOLD, 12));
        grid.add(passTitle, 0, 5, 2, 1);

        grid.add(new Label("Current Password:"), 0, 6);
        profileCurrentPasswordField = new PasswordField();
        profileCurrentPasswordField.setPromptText("Required to change password");
        grid.add(profileCurrentPasswordField, 1, 6);

        grid.add(new Label("New Password:"), 0, 7);
        profileNewPasswordField = new PasswordField();
        profileNewPasswordField.setPromptText("Leave blank to keep current");
        grid.add(profileNewPasswordField, 1, 7);

        grid.add(new Label("Confirm New Password:"), 0, 8);
        profileConfirmPasswordField = new PasswordField();
        grid.add(profileConfirmPasswordField, 1, 8);

        Button updatePasswordButton = new Button("Update Password"); // Combined update logic later
        grid.add(updatePasswordButton, 1, 9);


        grid.add(new Separator(), 0, 10, 2, 1); // --- Separator ---

        // --- Deposit Funds ---
        Label depositTitle = new Label("Deposit Funds");
        depositTitle.setFont(Font.font(null, FontWeight.BOLD, 12));
        grid.add(depositTitle, 0, 11, 2, 1);

        grid.add(new Label("Amount ($):"), 0, 12);
        depositAmountField = new TextField();
        depositAmountField.setPromptText("e.g., 50.00");
        grid.add(depositAmountField, 1, 12);

        depositButton = new Button("Deposit");
        grid.add(depositButton, 1, 13);

        // --- Error Label ---
        profileErrorLabel = new Label();
        profileErrorLabel.setStyle("-fx-text-fill: red;");
        grid.add(profileErrorLabel, 0, 14, 2, 1);


        // --- Event Handlers ---
        // Combine update logic into one button press or separate actions
        updateProfileButton.setOnAction(e -> handleUpdateProfile(false)); // false = don't update password here
        updatePasswordButton.setOnAction(e -> handleUpdateProfile(true)); // true = update password too
        depositButton.setOnAction(e -> handleDeposit());

        hideProfileError(); // Initial state

        // Set column constraints for better resizing
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setPrefWidth(150); // Label column width
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS); // Input field column grows
        grid.getColumnConstraints().addAll(col0, col1);

        return grid;
    }


    // --- Helper Methods ---

    private void updateClientInfoSidebar() {
        welcomeLabel.setText("Welcome, " + currentClient.getName());
        clientEmailLabel.setText("Email: " + currentClient.getEmail());
        clientIdLabel.setText("Client ID: " + currentClient.getId());
        updateBalanceLabel();
    }

    private void updateBalanceLabel() {
        balanceLabel.setText(String.format("Balance: $%.2f", currentClient.getBalance()));
    }

    private void switchToView(Node viewToShow) {
        // Bring the selected view to the front in the StackPane
        viewToShow.toFront();
        // Ensure only the selected view is visible (optional, toFront might be enough)
        for(Node child : centerStackPane.getChildren()) {
            child.setVisible(child == viewToShow);
        }
    }

    // --- Event Handler & Logic Methods (Products) ---

    private void applyProductFilters() {
        Category selectedCategory = categoryFilterComboBox.getValue();
        String searchText = productSearchField.getText().toLowerCase().trim();

        productFilteredList.setPredicate(product -> {
            boolean categoryMatch = (selectedCategory == null || selectedCategory.getId() == 0L || // "All" category
                    (product.getCategory() != null && product.getCategory().getId().equals(selectedCategory.getId())));

            boolean searchMatch = searchText.isEmpty() ||
                    product.getName().toLowerCase().contains(searchText) ||
                    product.getDescription().toLowerCase().contains(searchText) ||
                    (product.getCategory() != null && product.getCategory().getName().toLowerCase().contains(searchText));

            return categoryMatch && searchMatch;
        });
    }

    private void loadProducts() {
        productMasterList.setAll(productService.getAllProducts());
        applyProductFilters(); // Re-apply filters
        hideProductError();
    }

    private void handleAddToCart() {
        hideProductError();
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        int quantity = quantitySpinner.getValue(); // Spinner value should be correct type

        if (selectedProduct == null) {
            showProductError("Please select a product to add.");
            return;
        }
        if (quantity <= 0) {
            showProductError("Quantity must be positive.");
            return;
        }
        if (quantity > selectedProduct.getStock()) {
            showProductError("Not enough stock available (" + selectedProduct.getStock() + " left).");
            return;
        }

        try {
            CartItem newItem = new CartItem(
                    MainApplication.cartItemIdGenerator.getAndIncrement(), // Generate ID for the CartItem
                    selectedProduct,
                    quantity,
                    selectedProduct.getId() // Ensure ProductId is set for service logic
            );

            cartService.addItemToCart(currentClient, newItem);
            mainApp.showInfoAlert("Cart", "Added " + quantity + " x " + selectedProduct.getName() + " to cart.");
            quantitySpinner.getValueFactory().setValue(1); // Reset spinner
            productsTable.getSelectionModel().clearSelection(); // Deselect
            // Refresh product stock displayed (optional, or reload products)
            loadProducts();

        } catch (IllegalArgumentException e) {
            showProductError("Error adding to cart: " + e.getMessage());
        } catch (Exception e) {
            showProductError("An unexpected error occurred adding to cart.");
            e.printStackTrace();
        }
    }

    private void showProductError(String message) {
        productSelectionErrorLabel.setText(message);
        productSelectionErrorLabel.setVisible(true);
    }

    private void hideProductError() {
        productSelectionErrorLabel.setText("");
        productSelectionErrorLabel.setVisible(false);
    }


    // --- Event Handler & Logic Methods (Cart) ---

    private void loadCart() {
        hideCartError();
        Cart userCart = cartService.getCartByUserId(currentClient.getId());
        if (userCart != null) {
            List<CartItem> populatedItems = userCart.getItems().stream().map(item -> {
                        Product product = productService.getProductById(item.getProductId());
                        if (product != null) {
                            item.setProduct(product); // Set the full product object
                        } else {
                            System.err.println("Warning: Product with ID " + item.getProductId() + " not found for cart item.");
                            // Optionally: Handle missing products (e.g., mark item as invalid)
                        }
                        return item;
                    })
                    .filter(item -> item.getProduct() != null) // Only include items where product was found
                    .collect(Collectors.toList());
            cartItemsList.setAll(populatedItems);
        } else {
            cartItemsList.clear(); // No cart found or cart is empty
            System.out.println("No cart found for user ID: " + currentClient.getId()); // Debugging
        }
        updateCartTotal();
    }


    private void updateCartTotal() {
        double total = cartItemsList.stream()
                .mapToDouble(CartItem::getSubTotal)
                .sum();
        cartTotalLabel.setText(String.format("Total: $%.2f", total));
    }

    private void handleRemoveFromCart() {
        hideCartError();
        CartItem selectedItem = cartTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showCartError("Please select an item to remove.");
            return;
        }

        try {
            // The service likely needs the Product ID to remove
            cartService.removeItemFromCartByProductId(currentClient.getId(), selectedItem.getProductId());
            loadCart(); // Reload cart data
            mainApp.showInfoAlert("Cart", "Item removed from cart.");
        } catch (Exception e) {
            showCartError("Error removing item: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleClearCart() {
        hideCartError();
        if (cartItemsList.isEmpty()) {
            showCartError("Cart is already empty.");
            return;
        }

        Optional<ButtonType> result = mainApp.showConfirmationAlert("Clear Cart", "Are you sure you want to remove all items from your cart?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                cartService.clearCart(currentClient.getId());
                loadCart(); // Reload cart data
            } catch (Exception e) {
                showCartError("Error clearing cart: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void handleCheckout() {
        hideCartError();
        if (cartItemsList.isEmpty()) {
            showCartError("Cannot checkout with an empty cart.");
            return;
        }

        // Recalculate total just before checkout for accuracy
        double totalAmount = cartItemsList.stream().mapToDouble(CartItem::getSubTotal).sum();
        if (totalAmount <= 0) {
            showCartError("Cart total is zero or negative. Cannot checkout.");
            return;
        }


        Optional<ButtonType> result = mainApp.showConfirmationAlert("Confirm Checkout",
                String.format("Proceed to checkout with a total of $%.2f?", totalAmount));

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // 1. Check Balance
            if (currentClient.getBalance() < totalAmount) {
                showCartError(String.format("Insufficient balance. You need $%.2f more. Current balance: $%.2f",
                        totalAmount - currentClient.getBalance(), currentClient.getBalance()));
                return;
            }

            // 2. Process Payment (Deduct Funds)
            boolean paymentSuccess = paymentService.processPayment(currentClient, totalAmount);

            if (paymentSuccess) {
                try {
                    // 3. Update Client Balance in UserService (important!)
                    userService.updateUser(currentClient); // Save the updated balance

                    // 4. Create Order
                    Order newOrder = new Order();
                    newOrder.setId(MainApplication.orderIdGenerator.getAndIncrement()); // Generate order ID
                    newOrder.setUser(currentClient); // Set the user
                    newOrder.setUserid(currentClient.getId()); // Set the user ID explicitly
                    newOrder.setOrderDate(LocalDateTime.now());
                    newOrder.setStatus("PENDING"); // Initial status

                    // 5. Convert CartItems to OrderItems & Decrease Product Stock
                    for (CartItem cartItem : cartItemsList) {
                        Product product = cartItem.getProduct();
                        if (product == null) {
                            throw new IllegalStateException("Product details missing for item in cart during checkout.");
                        }
                        int quantity = cartItem.getQuantity();
                        int currentStock = product.getStock();

                        // Double-check stock again before creating OrderItem
                        if (quantity > currentStock) {
                            throw new IllegalStateException("Stock level changed. Not enough stock for " + product.getName() + " (" + currentStock + " available). Checkout cancelled.");
                        }


                        OrderItem orderItem = new OrderItem(
                                null, // OrderItem ID usually generated by DB
                                product,
                                quantity,
                                BigDecimal.valueOf(product.getPrice()) // Price at time of purchase
                        );
                        orderItem.setProductId(product.getId()); // Ensure product ID is set
                        newOrder.addOrderItem(orderItem);

                        // Decrease stock
                        product.setStock(currentStock - quantity);
                        productService.updateProduct(product); // Update stock in product service
                    }

                    orderService.createOrder(newOrder); // Save the order

                    // 6. Clear Cart
                    cartService.clearCart(currentClient.getId());

                    // 7. Update UI
                    loadCart(); // Refresh cart (should be empty)
                    loadProducts(); // Refresh products (to show updated stock)
                    updateBalanceLabel(); // Show new balance in sidebar
                    mainApp.showInfoAlert("Checkout Successful", "Your order #" + newOrder.getId() + " has been placed successfully!");
                    loadOrders(); // Refresh orders view
                    switchToView(ordersView); // Switch to orders view

                } catch (IllegalArgumentException | IllegalStateException e) {
                    showCartError("Checkout failed: " + e.getMessage());
                    // Attempt to refund if order creation failed after payment
                    paymentService.refundPayment(currentClient, totalAmount);
                    userService.updateUser(currentClient); // Save refunded balance
                    updateBalanceLabel();
                    // Reload products as stock might have been partially decremented before failure
                    loadProducts();
                    e.printStackTrace();
                } catch (Exception e) {
                    showCartError("An unexpected error occurred during checkout.");
                    // Attempt refund on unexpected error
                    paymentService.refundPayment(currentClient, totalAmount);
                    userService.updateUser(currentClient);
                    updateBalanceLabel();
                    // Reload products as stock might have been partially decremented before failure
                    loadProducts();
                    e.printStackTrace();
                }
            } else {
                // This case might indicate an issue within processPayment if balance check passed
                showCartError("Payment processing failed unexpectedly. Please try again.");
            }
        }
    }


    private void showCartError(String message) {
        cartActionErrorLabel.setText(message);
        cartActionErrorLabel.setVisible(true);
    }

    private void hideCartError() {
        cartActionErrorLabel.setText("");
        cartActionErrorLabel.setVisible(false);
    }


    // --- Event Handler & Logic Methods (Orders) ---

    private void loadOrders() {
        try {
            List<Order> userOrders = orderService.getOrdersByUserId(currentClient.getId());
            // Populate order items AFTER fetching the order list if they aren't included
            // This assumes OrderService.getOrdersByUserId returns orders *without* items
            // Or modify OrderService to include items if possible
            for (Order order : userOrders) {
                // If order items are not loaded by getOrdersByUserId, load them here.
                // This might require an additional method in OrderService, e.g., getOrderByIdWithItems(order.getId())
                // For now, assume items might be loaded or the total calculation handles null items.
                if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
                    // Example: Fetch items if needed (requires service changes)
                    // Order fullOrder = orderService.getOrderByIdWithItems(order.getId());
                    // if (fullOrder != null) order.setOrderItems(fullOrder.getOrderItems());
                    System.out.println("Order " + order.getId() + " items might not be loaded for total calculation.");
                }
            }

            ordersList.setAll(userOrders);
        } catch (Exception e) {
            mainApp.showErrorAlert("Load Orders Failed", "Could not load order history: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // --- Event Handler & Logic Methods (Profile) ---

    private void loadProfileData() {
        hideProfileError();
        profileNameField.setText(currentClient.getName());
        profileEmailField.setText(currentClient.getEmail());
        // Clear password fields every time profile is viewed
        profileCurrentPasswordField.clear();
        profileNewPasswordField.clear();
        profileConfirmPasswordField.clear();
        depositAmountField.clear();
    }

    private void handleUpdateProfile(boolean updatePassword) {
        hideProfileError();
        String name = profileNameField.getText().trim();
        String email = profileEmailField.getText().trim();
        String currentPassword = profileCurrentPasswordField.getText(); // Needed only if updatePassword is true
        String newPassword = profileNewPasswordField.getText();
        String confirmPassword = profileConfirmPasswordField.getText();

        // Validate basic fields
        if (name.isEmpty() || email.isEmpty()) {
            showProfileError("Name and Email cannot be empty.");
            return;
        }

        boolean changesMade = false;
        Client clientToUpdate = currentClient; // Work on the current client object

        try {
            // --- Update Name/Email (Always check) ---
            if (!name.equals(clientToUpdate.getName())) {
                clientToUpdate.setName(name); // Validate via setter
                changesMade = true;
            }
            if (!email.equalsIgnoreCase(clientToUpdate.getEmail())) {
                // Check if the new email is already taken by another user
                Optional<User> existingUser = userService.findByEmail(email);
                if (existingUser.isPresent() && !existingUser.get().getId().equals(clientToUpdate.getId())) {
                    showProfileError("Email address is already in use by another account.");
                    return; // Stop update
                }
                clientToUpdate.setEmail(email); // Validate via setter
                changesMade = true;
            }


            // --- Update Password (Only if requested) ---
            if (updatePassword) {
                // Password change requires current password and non-empty new password
                if (currentPassword.isEmpty()) {
                    showProfileError("Current password is required to change password.");
                    return;
                }
                // Verify current password
                if (!clientToUpdate.getPassword().equals(currentPassword)) { // Compare with stored password
                    showProfileError("Incorrect current password.");
                    return;
                }
                if (newPassword.isEmpty()) {
                    showProfileError("New password cannot be empty.");
                    return;
                }
                if (!newPassword.equals(confirmPassword)) {
                    showProfileError("New passwords do not match.");
                    return;
                }
                // Set the new password (will validate format via setter)
                clientToUpdate.setPassword(newPassword);
                changesMade = true;
                System.out.println("Password updated for user " + clientToUpdate.getId()); // Debug log
            }


            // --- Persist Changes ---
            if (changesMade) {
                userService.updateUser(clientToUpdate); // Save changes using UserService

                // Update UI immediately
                updateClientInfoSidebar(); // Update sidebar info
                mainApp.showInfoAlert("Profile Update", "Profile updated successfully.");
                // Clear password fields after successful update
                profileCurrentPasswordField.clear();
                profileNewPasswordField.clear();
                profileConfirmPasswordField.clear();
            } else {
                mainApp.showInfoAlert("Profile Update", "No changes detected.");
            }

        } catch (IllegalArgumentException e) {
            showProfileError("Update failed: " + e.getMessage());
            // Reload data to revert any invalid changes made directly to the object before validation failed
            loadProfileData();
        } catch (Exception e) {
            showProfileError("An unexpected error occurred during profile update.");
            loadProfileData(); // Revert UI
            e.printStackTrace();
        }
    }


    private void handleDeposit() {
        hideProfileError();
        String amountText = depositAmountField.getText().trim();
        if (amountText.isEmpty()) {
            showProfileError("Please enter an amount to deposit.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                showProfileError("Deposit amount must be positive.");
                return;
            }

            // Use the Client's deposit method
            currentClient.deposit(amount);

            // Persist the change
            userService.updateUser(currentClient);

            // Update UI
            updateBalanceLabel(); // Update balance display in sidebar
            depositAmountField.clear();
            mainApp.showInfoAlert("Deposit Successful", String.format("$%.2f deposited successfully. New balance: $%.2f", amount, currentClient.getBalance()));

        } catch (NumberFormatException e) {
            showProfileError("Invalid amount format. Please enter a number (e.g., 50.00).");
        } catch (IllegalArgumentException e) {
            showProfileError("Deposit failed: " + e.getMessage());
        } catch (Exception e) {
            showProfileError("An unexpected error occurred during deposit.");
            e.printStackTrace();
        }
    }

    private void showProfileError(String message) {
        profileErrorLabel.setText(message);
        profileErrorLabel.setVisible(true);
    }

    private void hideProfileError() {
        profileErrorLabel.setText("");
        profileErrorLabel.setVisible(false);
    }

}
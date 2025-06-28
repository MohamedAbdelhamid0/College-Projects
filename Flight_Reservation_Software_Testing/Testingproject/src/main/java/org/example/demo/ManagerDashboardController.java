package org.example.demo;

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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class ManagerDashboardController {

    // Main Layout
    private BorderPane mainBorderPane;
    private VBox leftSidebar;
    private StackPane centerStackPane;

    // Sidebar Components
    private Label welcomeLabel;
    private Button manageProductsButton, manageCategoriesButton, manageUsersButton, viewAllOrdersButton, logoutButton;

    // Center Views (Containers)
    private Node productsManagementView;
    private Node categoriesManagementView;
    private Node usersManagementView;
    private Node ordersView;

    // Product Management Components
    private TableView<Product> productsTable;
    private TextField productIdField, productNameField, productPriceField, productStockField;
    private TextArea productDescriptionArea;
    private ComboBox<Category> productCategoryComboBox;
    private Button clearProductFormButton, addProductButton, updateProductButton, deleteProductButton;
    private Label productErrorLabel;

    // Category Management Components
    private TableView<Category> categoriesTable;
    private TextField categoryIdField, categoryNameField;
    private TextArea categoryDescriptionArea;
    private Button clearCategoryFormButton, addCategoryButton, updateCategoryButton, deleteCategoryButton;
    private Label categoryErrorLabel;

    // User Management Components
    private TableView<User> usersTable;
    private Button deleteUserButton;
    private Label userErrorLabel;

    // Order Management Components
    private TableView<Order> ordersTable;
    private ComboBox<String> orderStatusFilterComboBox;
    private Label orderErrorLabel;

    // Services and State
    private final MainApplication mainApp;
    private final Manager currentManager;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final OrderService orderService;
    private final CartService cartService; // Needed for user deletion check

    // Data Lists
    private ObservableList<Product> productsMasterList = FXCollections.observableArrayList();
    private ObservableList<Category> categoriesMasterList = FXCollections.observableArrayList();
    private ObservableList<User> usersMasterList = FXCollections.observableArrayList();
    private ObservableList<Order> ordersMasterList = FXCollections.observableArrayList();
    private FilteredList<Order> ordersFilteredList;

    // Constructor
    public ManagerDashboardController(MainApplication mainApp, Manager manager) {
        this.mainApp = mainApp;
        this.currentManager = manager;
        this.productService = mainApp.getProductService();
        this.categoryService = mainApp.getCategoryService();
        this.userService = mainApp.getUserService();
        this.orderService = mainApp.getOrderService();
        this.cartService = mainApp.getCartService();
    }

    // Create the main view
    public Parent createDashboardView() {
        mainBorderPane = new BorderPane();

        // --- Left Sidebar ---
        createLeftSidebar();
        mainBorderPane.setLeft(leftSidebar);

        // --- Center Area ---
        centerStackPane = new StackPane();
        mainBorderPane.setCenter(centerStackPane);

        // --- Create Views ---
        productsManagementView = createProductManagementView();
        categoriesManagementView = createCategoryManagementView();
        usersManagementView = createUserManagementView();
        ordersView = createOrderManagementView();

        // Add views to stack pane
        centerStackPane.getChildren().addAll(productsManagementView, categoriesManagementView, usersManagementView, ordersView);

        // --- Initial Setup ---
        welcomeLabel.setText("Welcome, Manager " + currentManager.getName());
        switchToView(productsManagementView); // Default view
        loadProducts();
        loadCategories(); // Load categories for product form dropdown

        return mainBorderPane;
    }

    // --- UI Creation Methods ---

    private void createLeftSidebar() {
        leftSidebar = new VBox(15);
        leftSidebar.setPadding(new Insets(15));
        leftSidebar.setMinWidth(200);
        leftSidebar.setStyle("-fx-background-color: #e8e8e8;"); // Slightly different grey

        welcomeLabel = new Label();
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        manageProductsButton = new Button("Manage Products");
        manageCategoriesButton = new Button("Manage Categories");
        manageUsersButton = new Button("Manage Users");
        viewAllOrdersButton = new Button("View All Orders");
        logoutButton = new Button("Logout");

        double btnWidth = 170;
        manageProductsButton.setPrefWidth(btnWidth);
        manageCategoriesButton.setPrefWidth(btnWidth);
        manageUsersButton.setPrefWidth(btnWidth);
        viewAllOrdersButton.setPrefWidth(btnWidth);
        logoutButton.setPrefWidth(btnWidth);

        // Button Actions
        manageProductsButton.setOnAction(e -> { loadProducts(); switchToView(productsManagementView); });
        manageCategoriesButton.setOnAction(e -> { loadCategories(); switchToView(categoriesManagementView); });
        manageUsersButton.setOnAction(e -> { loadUsers(); switchToView(usersManagementView); });
        viewAllOrdersButton.setOnAction(e -> { loadOrders(); switchToView(ordersView); });
        logoutButton.setOnAction(e -> mainApp.logout());

        leftSidebar.getChildren().addAll(
                welcomeLabel,
                new Separator(),
                manageProductsButton,
                manageCategoriesButton,
                manageUsersButton,
                viewAllOrdersButton,
                new Separator(),
                logoutButton
        );
    }

    private Node createProductManagementView() {
        BorderPane viewLayout = new BorderPane();
        viewLayout.setPadding(new Insets(15));

        Label title = new Label("Product Management");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        BorderPane.setAlignment(title, Pos.CENTER);
        viewLayout.setTop(title);

        // --- Product Table ---
        productsTable = new TableView<>();
        setupProductTableColumns();
        viewLayout.setCenter(productsTable);
        BorderPane.setMargin(productsTable, new Insets(10, 0, 0, 0)); // Margin top

        // --- Product Form ---
        GridPane formGrid = createProductFormGrid();
        viewLayout.setRight(formGrid);
        BorderPane.setMargin(formGrid, new Insets(10, 0, 0, 15)); // Margin top/left

        // --- Load Initial Data & Setup Listeners ---
        setupProductFormListenersAndActions();

        return viewLayout;
    }

    private void setupProductTableColumns() {
        TableColumn<Product, Long> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(60);

        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(150);

        // Description column (optional, might make table wide)
        // TableColumn<Product, String> descCol = new TableColumn<>("Description");
        // descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        // descCol.setPrefWidth(200);

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
        categoryCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCategory() != null ?
                        cellData.getValue().getCategory().getName() : "N/A")
        );
        categoryCol.setPrefWidth(100);

        productsTable.getColumns().addAll(idCol, nameCol, /*descCol,*/ priceCol, stockCol, categoryCol);
        productsTable.setItems(productsMasterList); // Link data
        productsTable.setPlaceholder(new Label("No products available."));
        productsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Adjust columns to fit width
    }

    private GridPane createProductFormGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);
        grid.setPadding(new Insets(10));
        grid.setStyle("-fx-border-color: lightgrey; -fx-border-width: 1; -fx-border-radius: 5;");
        grid.setMinWidth(300); // Ensure form has some width

        Label formTitle = new Label("Product Details");
        formTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
        grid.add(formTitle, 0, 0, 2, 1);

        grid.add(new Label("ID:"), 0, 1);
        productIdField = new TextField();
        productIdField.setEditable(false); // ID is generated or comes from selection
        productIdField.setDisable(true);
        grid.add(productIdField, 1, 1);

        grid.add(new Label("Name:"), 0, 2);
        productNameField = new TextField();
        grid.add(productNameField, 1, 2);

        grid.add(new Label("Price ($):"), 0, 3);
        productPriceField = new TextField();
        grid.add(productPriceField, 1, 3);

        grid.add(new Label("Stock:"), 0, 4);
        productStockField = new TextField();
        grid.add(productStockField, 1, 4);

        grid.add(new Label("Category:"), 0, 5);
        productCategoryComboBox = new ComboBox<>();
        productCategoryComboBox.setMaxWidth(Double.MAX_VALUE); // Allow combo box to expand
        grid.add(productCategoryComboBox, 1, 5);

        grid.add(new Label("Description:"), 0, 6);
        productDescriptionArea = new TextArea();
        productDescriptionArea.setPrefRowCount(4);
        productDescriptionArea.setWrapText(true);
        grid.add(productDescriptionArea, 0, 7, 2, 1); // Span 2 columns

        // --- Buttons ---
        clearProductFormButton = new Button("Clear");
        addProductButton = new Button("Add New");
        updateProductButton = new Button("Update");
        deleteProductButton = new Button("Delete");

        HBox buttonBox1 = new HBox(10, addProductButton, updateProductButton);
        HBox buttonBox2 = new HBox(10, deleteProductButton, clearProductFormButton);
        VBox buttonContainer = new VBox(5, buttonBox1, buttonBox2);
        grid.add(buttonContainer, 0, 8, 2, 1);

        // --- Error Label ---
        productErrorLabel = new Label();
        productErrorLabel.setStyle("-fx-text-fill: red;");
        productErrorLabel.setWrapText(true);
        grid.add(productErrorLabel, 0, 9, 2, 1);

        // Set column constraints
        ColumnConstraints col0 = new ColumnConstraints(80); // Fixed width for labels
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS); // Fields grow
        grid.getColumnConstraints().addAll(col0, col1);


        return grid;
    }

    private void setupProductFormListenersAndActions() {
        // ComboBox setup
        productCategoryComboBox.setConverter(new javafx.util.StringConverter<Category>() {
            @Override public String toString(Category category) { return category != null ? category.getName() + " (ID:" + category.getId() + ")" : ""; }
            @Override public Category fromString(String string) { return null; } // Not needed for selection
        });
        // Load categories initially (will be refreshed by loadCategories)
        loadProductCategories();

        // Table selection listener
        productsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateProductForm(newSelection);
                updateProductButton.setDisable(false);
                deleteProductButton.setDisable(false);
                addProductButton.setDisable(true); // Disable Add when editing
            } else {
                clearProductForm(); // Clear form if no selection
            }
        });

        // Button actions
        clearProductFormButton.setOnAction(e -> clearProductForm());
        addProductButton.setOnAction(e -> handleAddProduct());
        updateProductButton.setOnAction(e -> handleUpdateProduct());
        deleteProductButton.setOnAction(e -> handleDeleteProduct());

        clearProductForm(); // Initial state (buttons disabled)
        hideProductError();
    }

    private Node createCategoryManagementView() {
        BorderPane viewLayout = new BorderPane();
        viewLayout.setPadding(new Insets(15));

        Label title = new Label("Category Management");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        BorderPane.setAlignment(title, Pos.CENTER);
        viewLayout.setTop(title);

        // --- Category Table ---
        categoriesTable = new TableView<>();
        setupCategoryTableColumns();
        viewLayout.setCenter(categoriesTable);
        BorderPane.setMargin(categoriesTable, new Insets(10, 0, 0, 0));

        // --- Category Form ---
        GridPane formGrid = createCategoryFormGrid();
        viewLayout.setRight(formGrid);
        BorderPane.setMargin(formGrid, new Insets(10, 0, 0, 15));

        // --- Setup Listeners & Actions ---
        setupCategoryFormListenersAndActions();

        return viewLayout;
    }

    private void setupCategoryTableColumns() {
        TableColumn<Category, Long> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(60);

        TableColumn<Category, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(150);

        TableColumn<Category, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descCol.setPrefWidth(250);

        categoriesTable.getColumns().addAll(idCol, nameCol, descCol);
        categoriesTable.setItems(categoriesMasterList); // Link data
        categoriesTable.setPlaceholder(new Label("No categories defined."));
        categoriesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private GridPane createCategoryFormGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);
        grid.setPadding(new Insets(10));
        grid.setStyle("-fx-border-color: lightgrey; -fx-border-width: 1; -fx-border-radius: 5;");
        grid.setMinWidth(300);

        Label formTitle = new Label("Category Details");
        formTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
        grid.add(formTitle, 0, 0, 2, 1);

        grid.add(new Label("ID:"), 0, 1);
        categoryIdField = new TextField();
        categoryIdField.setEditable(false);
        categoryIdField.setDisable(true);
        grid.add(categoryIdField, 1, 1);

        grid.add(new Label("Name:"), 0, 2);
        categoryNameField = new TextField();
        grid.add(categoryNameField, 1, 2);

        grid.add(new Label("Description:"), 0, 3);
        categoryDescriptionArea = new TextArea();
        categoryDescriptionArea.setPrefRowCount(4);
        categoryDescriptionArea.setWrapText(true);
        grid.add(categoryDescriptionArea, 0, 4, 2, 1);

        // --- Buttons ---
        clearCategoryFormButton = new Button("Clear");
        addCategoryButton = new Button("Add New");
        updateCategoryButton = new Button("Update");
        deleteCategoryButton = new Button("Delete");

        HBox buttonBox1 = new HBox(10, addCategoryButton, updateCategoryButton);
        HBox buttonBox2 = new HBox(10, deleteCategoryButton, clearCategoryFormButton);
        VBox buttonContainer = new VBox(5, buttonBox1, buttonBox2);
        grid.add(buttonContainer, 0, 5, 2, 1);


        // --- Error Label ---
        categoryErrorLabel = new Label();
        categoryErrorLabel.setStyle("-fx-text-fill: red;");
        categoryErrorLabel.setWrapText(true);
        grid.add(categoryErrorLabel, 0, 6, 2, 1);

        // Set column constraints
        ColumnConstraints col0 = new ColumnConstraints(80); // Fixed width for labels
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS); // Fields grow
        grid.getColumnConstraints().addAll(col0, col1);


        return grid;
    }

    private void setupCategoryFormListenersAndActions() {
        // Table selection listener
        categoriesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                populateCategoryForm(newVal);
                updateCategoryButton.setDisable(false);
                deleteCategoryButton.setDisable(false);
                addCategoryButton.setDisable(true); // Disable Add when editing
            } else {
                clearCategoryForm();
            }
        });

        // Button actions
        clearCategoryFormButton.setOnAction(e -> clearCategoryForm());
        addCategoryButton.setOnAction(e -> handleAddCategory());
        updateCategoryButton.setOnAction(e -> handleUpdateCategory());
        deleteCategoryButton.setOnAction(e -> handleDeleteCategory());

        clearCategoryForm(); // Initial state
        hideCategoryError();
    }

    private Node createUserManagementView() {
        VBox view = new VBox(10);
        view.setPadding(new Insets(15));

        Label title = new Label("User Management");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        usersTable = new TableView<>();
        setupUserTableColumns();
        VBox.setVgrow(usersTable, Priority.ALWAYS);

        // --- Actions ---
        HBox actionBox = new HBox(10);
        actionBox.setAlignment(Pos.CENTER_LEFT);
        deleteUserButton = new Button("Delete Selected User");
        userErrorLabel = new Label();
        userErrorLabel.setStyle("-fx-text-fill: red;");

        actionBox.getChildren().addAll(deleteUserButton, userErrorLabel);

        // --- Event Handlers ---
        deleteUserButton.setOnAction(e -> handleDeleteUser());
        usersTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            deleteUserButton.setDisable(nv == null); // Enable button only if a user is selected
            hideUserError();
        });

        view.getChildren().addAll(title, usersTable, actionBox);
        deleteUserButton.setDisable(true); // Initially disabled
        hideUserError();
        return view;
    }

    private void setupUserTableColumns() {
        TableColumn<User, Long> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(60);

        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(150);

        TableColumn<User, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setPrefWidth(200);

        TableColumn<User, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(cellData -> {
            User user = cellData.getValue();
            String type = "Unknown";
            if (user instanceof Client) type = "Client";
            else if (user instanceof Manager) type = "Manager";
            return new SimpleStringProperty(type);
        });
        typeCol.setPrefWidth(80);

        TableColumn<User, String> detailsCol = new TableColumn<>("Details");
        detailsCol.setCellValueFactory(cellData -> {
            User user = cellData.getValue();
            String details = "";
            if (user instanceof Client) {
                details = String.format("Balance: $%.2f", ((Client) user).getBalance());
            } else if (user instanceof Manager) {
                details = "Dept: " + ((Manager) user).getDepartment() + String.format(" / Sal: $%.2f", ((Manager) user).getSalary());
            }
            return new SimpleStringProperty(details);
        });
        detailsCol.setPrefWidth(200);

        usersTable.getColumns().addAll(idCol, nameCol, emailCol, typeCol, detailsCol);
        usersTable.setItems(usersMasterList); // Link data
        usersTable.setPlaceholder(new Label("No users found."));
        usersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private Node createOrderManagementView() {
        VBox view = new VBox(10);
        view.setPadding(new Insets(15));

        Label title = new Label("All Orders");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // --- Filter Bar ---
        HBox filterBox = new HBox(10);
        filterBox.setAlignment(Pos.CENTER_LEFT);
        orderStatusFilterComboBox = new ComboBox<>();
        filterBox.getChildren().addAll(new Label("Filter by Status:"), orderStatusFilterComboBox);

        // --- Orders Table ---
        ordersTable = new TableView<>();
        setupOrderTableColumns();
        VBox.setVgrow(ordersTable, Priority.ALWAYS);

        // --- Error Label ---
        orderErrorLabel = new Label();
        orderErrorLabel.setStyle("-fx-text-fill: red;");

        // --- Setup Filtering & Load ---
        setupOrderFiltering();

        view.getChildren().addAll(title, filterBox, ordersTable, orderErrorLabel);
        hideOrderError(); // Initial state
        return view;
    }

    private void setupOrderTableColumns() {
        TableColumn<Order, Long> idCol = new TableColumn<>("Order ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(80);

        TableColumn<Order, Long> userIdCol = new TableColumn<>("User ID");
        userIdCol.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getUserid()).asObject());
        userIdCol.setPrefWidth(80);

        TableColumn<Order, String> userNameCol = new TableColumn<>("User Name");
        userNameCol.setCellValueFactory(cellData -> {
            Order order = cellData.getValue();
            User orderUser = userService.getUserById(order.getUserid()); // Fetch user by ID
            return new SimpleStringProperty(orderUser != null ? orderUser.getName() : "Unknown/Deleted User");
        });
        userNameCol.setPrefWidth(150);

        TableColumn<Order, String> dateCol = new TableColumn<>("Date Placed");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dateCol.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getOrderDate() != null ? cellData.getValue().getOrderDate().format(formatter) : "N/A")
        );
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
                // Ensure items are loaded (might need explicit loading step in loadOrders)
                for (OrderItem item : order.getOrderItems()) {
                    if (item.getPriceAtPurchase() != null) {
                        total += item.getPriceAtPurchase().doubleValue() * item.getQuantity();
                    }
                }
            } else {
                // If items aren't loaded, we can't calculate total accurately
                // Maybe display N/A or fetch items on demand (less efficient)
                // System.out.println("Order items not available for order " + order.getId());
            }
            return new SimpleDoubleProperty(total).asObject();
        });
        totalCol.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Double total, boolean empty) {
                super.updateItem(total, empty);
                // Display N/A if total is 0 and items might be missing, or just display 0.00
                setText(empty || total == null ? null : String.format("$%.2f", total));
            }
        });
        totalCol.setPrefWidth(100);


        ordersTable.getColumns().addAll(idCol, userIdCol, userNameCol, dateCol, statusCol, totalCol);
        // Filtered list will be set in setupOrderFiltering
        ordersTable.setPlaceholder(new Label("No orders found matching the criteria."));
        ordersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // TODO: Add context menu or button column for actions like 'Update Status' or 'View Details'
    }

    private void setupOrderFiltering() {
        // Setup Filter ComboBox
        orderStatusFilterComboBox.getItems().addAll("All Statuses", "PENDING", "COMPLETED", "CANCELLED", "SHIPPED", "FAILED"); // Match Order model statuses
        orderStatusFilterComboBox.setValue("All Statuses");

        // Setup FilteredList
        // Initial data load happens in loadOrders, which calls applyOrderFilter
        ordersFilteredList = new FilteredList<>(ordersMasterList, p -> true);
        ordersTable.setItems(ordersFilteredList); // Link filtered list to table

        // Add listener to filter combo box
        orderStatusFilterComboBox.valueProperty().addListener((obs, oldV, newV) -> applyOrderFilter());
    }

    // --- Helper Methods ---

    private void switchToView(Node viewToShow) {
        viewToShow.toFront();
        // Ensure only the selected view is visible
        for(Node child : centerStackPane.getChildren()) {
            child.setVisible(child == viewToShow);
        }
    }

    // --- Load Data Methods ---

    private void loadProducts() {
        productsMasterList.setAll(productService.getAllProducts());
        productsTable.getSelectionModel().clearSelection(); // Deselect after reload
        clearProductForm();
        hideProductError();
    }

    private void loadCategories() {
        categoriesMasterList.setAll(categoryService.getAllCategories());
        categoriesTable.getSelectionModel().clearSelection();
        clearCategoryForm();
        hideCategoryError();
        // Also refresh category list used in product form
        loadProductCategories();
    }

    private void loadProductCategories() {
        // Keep track of currently selected category in product form if any
        Category selectedCategory = productCategoryComboBox.getValue();
        productCategoryComboBox.getItems().setAll(categoryService.getAllCategories());
        // Try to re-select the previously selected category if it still exists
        if (selectedCategory != null) {
            productCategoryComboBox.getItems().stream()
                    .filter(c -> c.getId().equals(selectedCategory.getId()))
                    .findFirst()
                    .ifPresent(productCategoryComboBox::setValue);
        }
    }


    private void loadUsers() {
        usersMasterList.setAll(userService.getAllUsers());
        usersTable.getSelectionModel().clearSelection();
        deleteUserButton.setDisable(true); // Disable delete button after loading
        hideUserError();
    }

    private void loadOrders() {
        try {
            List<Order> allOrders = orderService.getAllOrders();
            // OPTIONAL: Explicitly load order items here if needed for total calculation
            // and if OrderService doesn't load them by default.
            for (Order order : allOrders) {
                // Example: Fetch items if needed (requires service changes)
                // Order fullOrder = orderService.getOrderByIdWithItems(order.getId());
                // if (fullOrder != null) order.setOrderItems(fullOrder.getOrderItems());
                if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
                    System.out.println("Order " + order.getId() + " items may need explicit loading for accurate total.");
                }
            }

            ordersMasterList.setAll(allOrders);
            applyOrderFilter(); // Apply filter after loading/reloading
            hideOrderError();
        } catch (Exception e) {
            showOrderError("Failed to load orders: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void applyOrderFilter() {
        String selectedStatus = orderStatusFilterComboBox.getValue();
        ordersFilteredList.setPredicate(order -> {
            if (selectedStatus == null || selectedStatus.equals("All Statuses")) {
                return true; // Show all
            }
            // Compare order status (case-insensitive)
            return order.getStatus() != null && order.getStatus().equalsIgnoreCase(selectedStatus);
        });
    }

    // --- Form Handling Methods (Product) ---

    private void populateProductForm(Product product) {
        productIdField.setText(product.getId().toString());
        productNameField.setText(product.getName());
        productDescriptionArea.setText(product.getDescription());
        productPriceField.setText(String.format("%.2f", product.getPrice()));
        productStockField.setText(Integer.toString(product.getStock()));
        // Find and set the category in the ComboBox based on ID match
        productCategoryComboBox.getItems().stream()
                .filter(c -> c.getId().equals(product.getCategory().getId()))
                .findFirst()
                .ifPresent(productCategoryComboBox::setValue);
        hideProductError();
    }

    private void clearProductForm() {
        productsTable.getSelectionModel().clearSelection(); // Deselect table row
        productIdField.clear();
        productNameField.clear();
        productDescriptionArea.clear();
        productPriceField.clear();
        productStockField.clear();
        productCategoryComboBox.getSelectionModel().clearSelection();
        // productCategoryComboBox.setValue(null); // Keep placeholder text if needed
        updateProductButton.setDisable(true);
        deleteProductButton.setDisable(true);
        addProductButton.setDisable(false); // Enable Add when form is clear
        hideProductError();
    }

    private void handleAddProduct() {
        hideProductError();
        String name = productNameField.getText().trim();
        String description = productDescriptionArea.getText().trim();
        String priceStr = productPriceField.getText().trim();
        String stockStr = productStockField.getText().trim();
        Category category = productCategoryComboBox.getValue();

        if (name.isEmpty() || description.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty() || category == null) {
            showProductError("All fields (Name, Price, Stock, Category, Description) are required.");
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            int stock = Integer.parseInt(stockStr);
            long newId = MainApplication.productIdGenerator.getAndIncrement(); // Generate ID

            // Use constructor and setters for validation
            Product newProduct = new Product(newId, name, description, price, stock, category);

            productService.addProduct(newProduct);
            loadProducts(); // Refresh table
            clearProductForm(); // Clear form
            mainApp.showInfoAlert("Success", "Product '" + name + "' added successfully (ID: " + newId + ")");

        } catch (NumberFormatException e) {
            showProductError("Invalid price or stock format. Please enter numbers.");
        } catch (IllegalArgumentException e) {
            showProductError("Add failed: " + e.getMessage()); // Catch model/service validation errors
        } catch (Exception e) {
            showProductError("An unexpected error occurred while adding the product.");
            e.printStackTrace();
        }
    }

    private void handleUpdateProduct() {
        hideProductError();
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null || productIdField.getText().isEmpty()) {
            // Should not happen if button is enabled correctly, but check defensively
            showProductError("Select a product from the table to update.");
            return;
        }

        String name = productNameField.getText().trim();
        String description = productDescriptionArea.getText().trim();
        String priceStr = productPriceField.getText().trim();
        String stockStr = productStockField.getText().trim();
        Category category = productCategoryComboBox.getValue();

        if (name.isEmpty() || description.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty() || category == null) {
            showProductError("All fields (Name, Price, Stock, Category, Description) are required.");
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            int stock = Integer.parseInt(stockStr);

            // Update the selected product object directly using setters for validation
            selectedProduct.setName(name);
            selectedProduct.setDescription(description);
            selectedProduct.setPrice(price);
            selectedProduct.setStock(stock);
            selectedProduct.setCategory(category);

            Product updated = productService.updateProduct(selectedProduct);
            if (updated != null) {
                loadProducts(); // Refresh table (will clear form and re-select if needed)
                // Find and re-select the updated item after refresh
                productsTable.getItems().stream()
                        .filter(p -> p.getId().equals(updated.getId()))
                        .findFirst()
                        .ifPresent(p -> {
                            productsTable.getSelectionModel().select(p);
                            productsTable.scrollTo(p); // Ensure it's visible
                        });
                mainApp.showInfoAlert("Success", "Product updated successfully.");
            } else {
                // This might happen if the product was deleted by another manager between selection and update attempt
                showProductError("Update failed: Product not found (ID: " + selectedProduct.getId() + "). Please refresh the list.");
                loadProducts(); // Refresh to show the current state
            }

        } catch (NumberFormatException e) {
            showProductError("Invalid price or stock format.");
        } catch (IllegalArgumentException e) {
            showProductError("Update failed: " + e.getMessage()); // Catch model/service validation errors
        } catch (Exception e) {
            showProductError("An unexpected error occurred while updating the product.");
            e.printStackTrace();
        }
    }


    private void handleDeleteProduct() {
        hideProductError();
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            showProductError("Select a product to delete.");
            return;
        }

        // Basic Dependency Check (Orders/Carts - more complex check needed for real app)
        // This is a simplified check. A real app would need to query OrderItem/CartItem tables.
        boolean inOrders = orderService.getAllOrders().stream()
                .anyMatch(o -> o.getOrderItems() != null && o.getOrderItems().stream()
                        .anyMatch(oi -> oi.getProductId() != null && oi.getProductId().equals(selectedProduct.getId())));
        // boolean inCarts = cartService.isProductInAnyCart(selectedProduct.getId()); // Assumes such a method exists

        if (inOrders /*|| inCarts*/) {
            showProductError("Cannot delete product: It exists in existing orders or carts.");
            // More specific message if you check carts separately
            return;
        }


        Optional<ButtonType> result = mainApp.showConfirmationAlert("Delete Product",
                "Are you sure you want to delete product '" + selectedProduct.getName() + "' (ID: " + selectedProduct.getId() + ")? This action cannot be undone.");

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Product deleted = productService.deleteProduct(selectedProduct.getId());
                if (deleted != null) {
                    loadProducts(); // Refreshes list and clears form
                    mainApp.showInfoAlert("Success", "Product deleted successfully.");
                } else {
                    showProductError("Delete failed: Product not found. Refresh list.");
                    loadProducts();
                }
            } catch (Exception e) {
                showProductError("An error occurred during product deletion.");
                e.printStackTrace();
            }
        }
    }

    // --- Form Handling Methods (Category) ---

    private void populateCategoryForm(Category category) {
        categoryIdField.setText(category.getId().toString());
        categoryNameField.setText(category.getName());
        categoryDescriptionArea.setText(category.getDescription());
        hideCategoryError();
    }

    private void clearCategoryForm() {
        categoriesTable.getSelectionModel().clearSelection();
        categoryIdField.clear();
        categoryNameField.clear();
        categoryDescriptionArea.clear();
        updateCategoryButton.setDisable(true);
        deleteCategoryButton.setDisable(true);
        addCategoryButton.setDisable(false); // Enable Add when form is clear
        hideCategoryError();
    }

    private void handleAddCategory() {
        hideCategoryError();
        String name = categoryNameField.getText().trim();
        String description = categoryDescriptionArea.getText().trim(); // Allow empty description

        if (name.isEmpty()) {
            showCategoryError("Category Name is required.");
            return;
        }
        // Check for duplicate name (optional but good practice)
        if (categoryService.getAllCategories().stream().anyMatch(c -> c.getName().equalsIgnoreCase(name))) {
            showCategoryError("A category with this name already exists.");
            return;
        }


        try {
            long newId = MainApplication.categoryIdGenerator.getAndIncrement(); // Generate ID
            Category newCategory = new Category(newId, name, description);

            categoryService.addCategory(newCategory);
            loadCategories(); // Refresh table and product form dropdown
            clearCategoryForm();
            mainApp.showInfoAlert("Success", "Category '" + name + "' added successfully (ID: " + newId + ")");

        } catch (IllegalArgumentException e) {
            showCategoryError("Add failed: " + e.getMessage());
        } catch (Exception e) {
            showCategoryError("An unexpected error occurred while adding the category.");
            e.printStackTrace();
        }
    }

    private void handleUpdateCategory() {
        hideCategoryError();
        Category selected = categoriesTable.getSelectionModel().getSelectedItem();
        if (selected == null || categoryIdField.getText().isEmpty()) {
            showCategoryError("Select a category from the table to update.");
            return;
        }

        String name = categoryNameField.getText().trim();
        String description = categoryDescriptionArea.getText().trim();

        if (name.isEmpty()) {
            showCategoryError("Category Name is required.");
            return;
        }

        // Check for duplicate name (excluding the category being edited)
        if (categoryService.getAllCategories().stream()
                .anyMatch(c -> !c.getId().equals(selected.getId()) && c.getName().equalsIgnoreCase(name))) {
            showCategoryError("Another category with this name already exists.");
            return;
        }

        try {
            selected.setName(name); // Use setter for validation
            selected.setDescription(description);

            Category updated = categoryService.updateCategory(selected);
            if (updated != null) {
                loadCategories(); // Refresh table & product dropdown
                // Find and re-select the updated item
                categoriesTable.getItems().stream()
                        .filter(c -> c.getId().equals(updated.getId()))
                        .findFirst()
                        .ifPresent(c -> {
                            categoriesTable.getSelectionModel().select(c);
                            categoriesTable.scrollTo(c);
                        });
                mainApp.showInfoAlert("Success", "Category updated successfully.");
            } else {
                showCategoryError("Update failed: Category not found (ID: " + selected.getId() + "). Refresh list.");
                loadCategories();
            }
        } catch (IllegalArgumentException e) {
            showCategoryError("Update failed: " + e.getMessage());
        } catch (Exception e) {
            showCategoryError("An unexpected error occurred while updating category.");
            e.printStackTrace();
        }
    }

    private void handleDeleteCategory() {
        hideCategoryError();
        Category selected = categoriesTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showCategoryError("Select a category to delete.");
            return;
        }

        // **Dependency Check (Crucial)**
        boolean isInUse = productService.getAllProducts().stream()
                .anyMatch(p -> p.getCategory() != null && p.getCategory().getId().equals(selected.getId()));

        if (isInUse) {
            showCategoryError("Cannot delete category '" + selected.getName() + "': It is assigned to one or more products.");
            return;
        }

        Optional<ButtonType> result = mainApp.showConfirmationAlert("Delete Category",
                "Are you sure you want to delete category '" + selected.getName() + "' (ID: " + selected.getId() + ")? This action cannot be undone.");

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Category deleted = categoryService.deleteCategory(selected.getId());
                if (deleted != null) {
                    loadCategories(); // Refresh table & product dropdown
                    mainApp.showInfoAlert("Success", "Category deleted successfully.");
                } else {
                    showCategoryError("Delete failed: Category not found. Refresh list.");
                    loadCategories();
                }
            } catch (Exception e) {
                showCategoryError("An error occurred during category deletion.");
                e.printStackTrace();
            }
        }
    }

    // --- User Management Logic ---

    private void handleDeleteUser() {
        hideUserError();
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showUserError("Please select a user to delete.");
            return;
        }

        // Prevent deleting the currently logged-in manager
        if (selectedUser.getId().equals(currentManager.getId())) {
            showUserError("Cannot delete your own manager account.");
            return;
        }

        // **Dependency Check (Orders/Carts)**
        boolean hasOrders = !orderService.getOrdersByUserId(selectedUser.getId()).isEmpty();
        // Check cart existence (more robust check might be needed depending on CartService)
        Cart userCart = (selectedUser instanceof Client) ? cartService.getCartByUserId(selectedUser.getId()) : null;
        boolean hasActiveCart = userCart != null && !userCart.getItems().isEmpty(); // Check if cart exists and is not empty

        if (hasOrders) {
            showUserError("Cannot delete user '" + selectedUser.getName() + "': User has existing orders. Orders must be resolved first.");
            return;
        }
        if (hasActiveCart) {
            showUserError("Cannot delete user '" + selectedUser.getName() + "': User has items in their cart. Cart must be empty.");
            return;
        }

        Optional<ButtonType> result = mainApp.showConfirmationAlert("Delete User",
                "Are you sure you want to permanently delete user '" + selectedUser.getName() + "' (ID: " + selectedUser.getId() + ")? This will also remove their cart if applicable. This action cannot be undone.");

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                User deleted = userService.deleteUser(selectedUser.getId());

                // Also remove their cart if it exists (even if empty)
                if (selectedUser instanceof Client) {
                    cartService.removeCartByUserId(selectedUser.getId());
                    System.out.println("Attempted removal of cart for deleted user ID: " + selectedUser.getId());
                }

                if (deleted != null) {
                    loadUsers(); // Refresh user list
                    mainApp.showInfoAlert("Success", "User '" + selectedUser.getName() + "' deleted successfully.");
                } else {
                    showUserError("Delete failed: User not found. Refresh list.");
                    loadUsers();
                }
            } catch (Exception e) {
                // Catch potential exceptions during cart removal as well
                showUserError("An error occurred during user or cart deletion: " + e.getMessage());
                e.printStackTrace();
                loadUsers(); // Refresh list even on error
            }
        }
    }


    // --- Error Label Handling ---

    private void showProductError(String message) {
        productErrorLabel.setText(message);
        productErrorLabel.setVisible(true);
    }
    private void hideProductError() {
        productErrorLabel.setText("");
        productErrorLabel.setVisible(false);
    }

    private void showCategoryError(String message) {
        categoryErrorLabel.setText(message);
        categoryErrorLabel.setVisible(true);
    }
    private void hideCategoryError() {
        categoryErrorLabel.setText("");
        categoryErrorLabel.setVisible(false);
    }

    private void showUserError(String message) {
        userErrorLabel.setText(message);
        userErrorLabel.setVisible(true);
    }
    private void hideUserError() {
        userErrorLabel.setText("");
        userErrorLabel.setVisible(false);
    }

    private void showOrderError(String message) {
        orderErrorLabel.setText(message);
        orderErrorLabel.setVisible(true);
    }
    private void hideOrderError() {
        orderErrorLabel.setText("");
        orderErrorLabel.setVisible(false);
    }

} // End of ManagerDashboardController class
package org.example.service;

import org.example.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects; // Added for null checks

public class ProductService {
    private final Map<Long, Product> productMap = new HashMap<>();

    /**
     * Adds a new product to the service.
     * @param product The product to add. Must not be null and must have an ID.
     * @return The added product.
     * @throws IllegalArgumentException if product or product ID is null, or if ID already exists.
     */
    public Product addProduct(Product product) {
        Objects.requireNonNull(product, "Product cannot be null");
        Objects.requireNonNull(product.getId(), "Product ID cannot be null");

        if (productMap.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product with ID " + product.getId() + " already exists.");
        }
        productMap.put(product.getId(), product);
        return product;
    }

    /**
     * Retrieves a product by its ID.
     * @param id The ID of the product to retrieve.
     * @return The product if found, otherwise null.
     */
    public Product getProductById(Long id) {
        if (id == null) return null;
        return productMap.get(id);
    }

    /**
     * Updates an existing product.
     * @param product The product with updated details. Must not be null and must have an ID.
     * @return The updated product if found and updated, otherwise null.
     * @throws IllegalArgumentException if product or product ID is null.
     */
    public Product updateProduct(Product product) {
        Objects.requireNonNull(product, "Product cannot be null for update");
        Objects.requireNonNull(product.getId(), "Product ID cannot be null for update");

        if (productMap.containsKey(product.getId())) {
            productMap.put(product.getId(), product);
            return product;
        }
        return null; // Or throw ProductNotFoundException
    }

    /**
     * Removes a product by its ID.
     * @param id The ID of the product to remove.
     * @return The removed product, or null if no product was found with that ID.
     */
    public Product deleteProduct(Long id) {
        if (id == null) return null;
        return productMap.remove(id);
    }

    // --- Added Method for UI ---
    /**
     * Retrieves all products currently stored.
     * @return A new list containing all products. Returns an empty list if none exist.
     */
    public List<Product> getAllProducts() {
        // Return a defensive copy
        return new ArrayList<>(productMap.values());
    }
    // --- End Added Method ---
}
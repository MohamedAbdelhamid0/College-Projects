package org.example.service;

import org.example.model.Category;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects; // Added for null checks

public class CategoryService {
    private final Map<Long, Category> categoryMap = new HashMap<>();

    /**
     * Adds a new category.
     * @param category The category to add. Must not be null and must have an ID.
     * @return The added category.
     * @throws IllegalArgumentException if category or category ID is null, or if ID already exists.
     */
    public Category addCategory(Category category) {
        Objects.requireNonNull(category, "Category cannot be null");
        Objects.requireNonNull(category.getId(), "Category ID cannot be null");

        if (categoryMap.containsKey(category.getId())) {
            throw new IllegalArgumentException("Category with ID " + category.getId() + " already exists.");
        }
        categoryMap.put(category.getId(), category);
        return category;
    }

    /**
     * Retrieves a category by its ID.
     * @param id The ID of the category to retrieve.
     * @return The category if found, otherwise null.
     */
    public Category getCategoryById(Long id) {
        if (id == null) return null;
        return categoryMap.get(id);
    }

    /**
     * Updates an existing category.
     * @param category The category with updated details. Must not be null and must have an ID.
     * @return The updated category if found and updated, otherwise null.
     * @throws IllegalArgumentException if category or category ID is null.
     */
    public Category updateCategory(Category category) {
        Objects.requireNonNull(category, "Category cannot be null for update");
        Objects.requireNonNull(category.getId(), "Category ID cannot be null for update");

        if (categoryMap.containsKey(category.getId())) {
            categoryMap.put(category.getId(), category);
            return category;
        }
        return null; // Or throw CategoryNotFoundException
    }

    /**
     * Removes a category by its ID.
     * @param id The ID of the category to remove.
     * @return The removed category, or null if no category was found with that ID.
     */
    public Category deleteCategory(Long id) {
        if (id == null) return null;
        return categoryMap.remove(id);
    }

    // --- Added Method for UI ---
    /**
     * Retrieves all categories currently stored.
     * @return A new list containing all categories. Returns an empty list if none exist.
     */
    public List<Category> getAllCategories() {
        // Return a defensive copy
        return new ArrayList<>(categoryMap.values());
    }
    // --- End Added Method ---
}
package org.example.service;

import org.example.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {
    private CategoryService categoryService;
    private Category testCategory;
    private final Long categoryId = 1L;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService();
        testCategory = new Category(categoryId, "Electronics", "Electronics categories");
    }

    @Test
    void addCategory_ValidCategory_AddsToService() {
        Category added = categoryService.addCategory(testCategory);
        assertEquals(testCategory, added);
        assertEquals(testCategory, categoryService.getCategoryById(categoryId));
    }

    @Test
    void addCategory_DuplicateId_ThrowsException() {
        categoryService.addCategory(testCategory);
        Category duplicate = new Category(categoryId, "Computers","computers categories");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> categoryService.addCategory(duplicate));

        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    void getCategoryById_ExistingId_ReturnsCategory() {
        categoryService.addCategory(testCategory);
        Category found = categoryService.getCategoryById(categoryId);
        assertEquals(testCategory, found);
    }

    @Test
    void getCategoryById_NonExistentId_ReturnsNull() {
        assertNull(categoryService.getCategoryById(999L));
    }

    @Test
    void updateCategory_ExistingCategory_UpdatesSuccessfully() {
        categoryService.addCategory(testCategory);
        Category updated = new Category(categoryId, "Updated Electronics","Updated Electronics category");

        Category result = categoryService.updateCategory(updated);
        assertEquals(updated, result);
        assertEquals("Updated Electronics",
                categoryService.getCategoryById(categoryId).getName());
    }

    @Test
    void updateCategory_NonExistentCategory_ReturnsNull() {
        Category nonExistent = new Category(999L, "Non-existent","non-existent category");
        assertNull(categoryService.updateCategory(nonExistent));
    }

    @Test
    void deleteCategory_ExistingCategory_RemovesFromService() {
        categoryService.addCategory(testCategory);
        Category deleted = categoryService.deleteCategory(categoryId);

        assertEquals(testCategory, deleted);
        assertNull(categoryService.getCategoryById(categoryId));
    }

    @Test
    void getAllCategories_ReturnsAllAddedCategories() {
        Category category2 = new Category(2L, "Books","Books categories");
        categoryService.addCategory(testCategory);
        categoryService.addCategory(category2);

        List<Category> allCategories = categoryService.getAllCategories();
        assertEquals(2, allCategories.size());
        assertTrue(allCategories.contains(testCategory));
        assertTrue(allCategories.contains(category2));
    }

    @Test
    void addCategory_NullCategory_ThrowsException() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> categoryService.addCategory(null));

        assertTrue(exception.getMessage().contains("cannot be null"));
    }

    @Test
    void addCategory_NullId_ThrowsException() {
        Category invalidCategory = new Category(null, "Invalid","invalid category");
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> categoryService.addCategory(invalidCategory));

        assertTrue(exception.getMessage().contains("ID cannot be null"));
    }

    @Test
    void getAllCategories_ReturnsDefensiveCopy() {
        categoryService.addCategory(testCategory);
        List<Category> categories = categoryService.getAllCategories();
        categories.clear();

        assertEquals(1, categoryService.getAllCategories().size());
    }

    @Test
    void deleteCategory_NonExistentId_ReturnsNull() {
        assertNull(categoryService.deleteCategory(999L));
    }

    @Test
    void getCategoryById_NullId_ReturnsNull() {
        assertNull(categoryService.getCategoryById(null));
    }
}
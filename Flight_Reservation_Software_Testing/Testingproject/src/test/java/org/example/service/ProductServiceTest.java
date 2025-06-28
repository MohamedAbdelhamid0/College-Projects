package org.example.service;

import org.example.model.Category;
import org.example.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private ProductService productService;
    private Product testProduct;
    private final Long productId = 1L;
    private Category category;
    private Category anotherCategory;
    private Category category2;
    @BeforeEach
    void setUp() {
        productService = new ProductService();
        testProduct = new Product(productId, "Test Product","Test 2023" ,99.99,100,anotherCategory);
    }

    @Test
    void addProduct_ValidProduct_AddsToService() {
        Product added = productService.addProduct(testProduct);
        assertEquals(testProduct, added);
        assertEquals(testProduct, productService.getProductById(productId));
    }

    @Test
    void addProduct_DuplicateId_ThrowsException() {
        productService.addProduct(testProduct);
        Product duplicate = new Product(productId, "Test Product","Test 2023" ,99.99,100,anotherCategory);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct(duplicate));

        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    void getProductById_ExistingId_ReturnsProduct() {
        productService.addProduct(testProduct);
        Product found = productService.getProductById(productId);
        assertEquals(testProduct, found);
    }

    @Test
    void getProductById_NonExistentId_ReturnsNull() {
        assertNull(productService.getProductById(999L));
    }

    @Test
    void updateProduct_ExistingProduct_UpdatesSuccessfully() {
        productService.addProduct(testProduct);
        Product updated = new Product(productId, "Updated Product","Updated product 2025" ,149.99,200,anotherCategory);

        Product result = productService.updateProduct(updated);
        assertEquals(updated, result);
        assertEquals("Updated Product", productService.getProductById(productId).getName());
    }

    @Test
    void updateProduct_NonExistentProduct_ReturnsNull() {
        Product nonExistent = new Product(999L, "Ghost Product", "Ghost product 2025",0.99,1000,category);
        assertNull(productService.updateProduct(nonExistent));
    }

    @Test
    void deleteProduct_ExistingProduct_RemovesFromService() {
        productService.addProduct(testProduct);
        Product deleted = productService.deleteProduct(productId);

        assertEquals(testProduct, deleted);
        assertNull(productService.getProductById(productId));
    }

    @Test
    void getAllProducts_ReturnsAllAddedProducts() {
        Product product2 = new Product(2L, "Second Product","Second product f50 2024" ,199.99,200,category);
        productService.addProduct(testProduct);
        productService.addProduct(product2);

        List<Product> allProducts = productService.getAllProducts();
        assertEquals(2, allProducts.size());
        assertTrue(allProducts.contains(testProduct));
        assertTrue(allProducts.contains(product2));
    }

    @Test
    void addProduct_NullProduct_ThrowsException() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> productService.addProduct(null));

        assertTrue(exception.getMessage().contains("cannot be null"));
    }

    @Test
    void addProduct_NullId_ThrowsException() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            // Create the invalid Product with null name inside the lambda
            productService.addProduct(new Product(null, "Invalid", "null product", 0.0, 0, category2));
        });

        assertTrue(exception.getMessage().contains("ID cannot be null"));
    }

    @Test
    void getAllProducts_ReturnsDefensiveCopy() {
        productService.addProduct(testProduct);
        List<Product> products = productService.getAllProducts();
        products.clear();

        assertEquals(1, productService.getAllProducts().size());
    }
}
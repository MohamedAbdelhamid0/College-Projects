package org.example.model;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private static Product product;
    private long id = product.getId();
    private String name = product.getName();
    private String description = product.getDescription();
    private double price = product.getPrice();
    private int stock = product.getStock();
    private Category category = product.getCategory();

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        product = new Product();
        product.setId(1l);
        product.setName("test");
        product.setDescription("test");
        product.setPrice(1d);
        product.setStock(10);
        product.setCategory(new Category());
    }
    @Test
    @Order(1)
    public void testSetId() {
        Product newProduct = new Product();
        newProduct.setId(2l);
        assertEquals(2l, newProduct.getId());
    }
    @Test
    @Order(2)
    public void testSetName() {
        Product newProduct = new Product();
        newProduct.setName("adidas F50");
        assertEquals("adidas F50", newProduct.getName());
    }
    @Test
    @Order(3)
    public void testSetDescription() {
        Product newProduct = new Product();
        newProduct.setDescription("adidas 2025 F50 premium quality");
        assertEquals("adidas 2025 F50 premium quality", newProduct.getDescription());
    }
    @Test
    @Order(4)
    public void testSetPrice() {
        Product newProduct = new Product();
        newProduct.setPrice(150.0);
        assertEquals(150.0, newProduct.getPrice());
    }
    @Test
    @Order(5)
    public void testSetStock() {
        Product newProduct = new Product();
        newProduct.setStock(250);
        assertEquals(250, newProduct.getStock());
    }
    @Test
    @Order(6)
    public void testSetCategory() {
        Product newProduct = new Product();
        newProduct.setCategory(new Category());
        assertEquals(new Category(), newProduct.getCategory());
    }
    @Test
    public void testPrice() {
        Product product = new Product();

        // Test with a valid price
        assertDoesNotThrow(() -> {
            product.setPrice(100.0); // Should not throw an exception
        });

        // Test with a negative price
        assertThrows(IllegalArgumentException.class, () -> {
            product.setPrice(-10.0); // Should throw IllegalArgumentException
        });
    }
    @Test
    public void testId() {
        Product entity = new Product();

        // Test with a valid id
        assertDoesNotThrow(() -> {
            entity.setId(123L); // Should not throw an exception
        });

        // Test with a null id
        assertThrows(IllegalArgumentException.class, () -> {
            entity.setId(null); // Should throw IllegalArgumentException
        });

        // Test with a negative id
        assertThrows(IllegalArgumentException.class, () -> {
            entity.setId(-1L); // Should throw IllegalArgumentException
        });
    }
    @Test
    public void teststockWithwrongNumericInput() {
        Product product1 = new Product();
        double stock = 100.0;

        assertThrows(IllegalArgumentException.class, () -> {
            product1.setStock(Integer.parseInt(String.valueOf(stock)));
        });
    }
    @Test
    public void testStock() {
        Product product = new Product();

        // Test with a valid stock
        assertDoesNotThrow(() -> {
            product.setStock(100); // Should not throw an exception
        });

        // Test with a negative stock
        assertThrows(IllegalArgumentException.class, () -> {
            product.setStock(-10); // Should throw IllegalArgumentException
        });
    }
    @Test
    void TestInvalidname() {

        Product product1 = new Product();

        // Test with a null description
        assertThrows(IllegalArgumentException.class, () -> {
            product1.setDescription(null);
        });

        // Test with an empty description
        assertThrows(IllegalArgumentException.class, () -> {
            product1.setDescription("");
        });
    }
    @AfterAll
    static void tearDownAfterClass() {
        // Reset the fields of the product object without setting it to null
        if (product != null) {  // Make sure product is not null before performing operations
            product.setCategory(null);
            product.setDescription("No description");
            product.setName("");
            product.setPrice(0);
            product.setStock(0);
        }
    }

}
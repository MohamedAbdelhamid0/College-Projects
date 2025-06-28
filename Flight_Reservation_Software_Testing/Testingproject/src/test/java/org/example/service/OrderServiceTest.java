package org.example.service;

import org.example.model.Order;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    private OrderService orderService;
    private User validUser;
    private Order testOrder;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
        validUser = new User(1L, "Test User", "test@gmail.com","Seif2004!");
        testOrder = new Order(validUser);
    }

    @Test
    void createOrder_AssignsUniqueId() {
        Order created = orderService.createOrder(testOrder);
        assertNotNull(created.getId());
        assertEquals(1L, created.getId());
    }

    @Test
    void createOrder_WithExistingId_GeneratesNewId() {
        testOrder.setId(1L);
        Order created = orderService.createOrder(testOrder);
        assertEquals(1L, created.getId());

        Order secondOrder = new Order(validUser);
        secondOrder.setId(1L);
        Order createdSecond = orderService.createOrder(secondOrder);
        assertEquals(2L, createdSecond.getId());
    }

    @Test
    void getOrderById_ReturnsCorrectOrder() {
        Order created = orderService.createOrder(testOrder);
        Order retrieved = orderService.getOrderById(created.getId());
        assertEquals(created, retrieved);
    }

    @Test
    void updateOrder_UpdatesExistingOrder() {
        Order created = orderService.createOrder(testOrder);
        created.setStatus("SHIPPED");

        Order updated = orderService.updateOrder(created);
        assertEquals("shipped", updated.getStatus());
    }

    @Test
    void deleteOrder_RemovesFromSystem() {
        Order created = orderService.createOrder(testOrder);
        Order deleted = orderService.deleteOrder(created.getId());

        assertEquals(created, deleted);
        assertNull(orderService.getOrderById(created.getId()));
    }

    @Test
    void getOrdersByUserId_FiltersCorrectOrders() {
        User testUser = new User(1L, "Test User", "test@gmail.com", "TestPassword");
        User secondUser = new User(2L, "Second User", "second@gmail.com", "SecondPassword");

        // Create two orders with different users
        orderService.createOrder(new Order(testUser));  // Order for testUser
        orderService.createOrder(new Order(secondUser)); // Order for secondUser

        // Fetch orders for the testUser
        List<Order> results = orderService.getOrdersByUserId(1L);

        // Assert that there is only 1 order for testUser
        assertEquals(1, results.size());
        assertEquals(1L, results.get(0).getUserid());
    }

    @Test
    void getAllOrders_ReturnsAllCreated() {
        orderService.createOrder(testOrder);
        orderService.createOrder(new Order(validUser));

        List<Order> allOrders = orderService.getAllOrders();
        assertEquals(2, allOrders.size());
    }

    @Test
    void shouldThrowWhenCreatingInvalidOrder() {

        assertThrows(NullPointerException.class, () -> {
            // Create the invalid Order with null User inside the lambda
            orderService.createOrder(new Order(null));
        });
    }

    @Test
    void shouldThrowWhenUpdatingNonExistentOrder() {
        Order nonExistent = new Order(validUser);
        nonExistent.setId(999L);
        assertThrows(IllegalArgumentException.class,
                () -> orderService.updateOrder(nonExistent));
    }
}
package org.example.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class OrderTest {
    private static Long id;
    private static User user;
    private static List<OrderItem> orderItems = new ArrayList<>();
    private static LocalDateTime orderDate;
    private static String status; // e.g., "PENDING", "COMPLETED", "CANCELLED"
    private static long userid ;
    public static Order order;
    @BeforeAll
    static void setUp() {
        order = new Order();  // Assign directly to the static variable
        order.setId(123L);
        order.setOrderItems(new ArrayList<>());
        orderDate = LocalDateTime.now();
        order.setStatus("pending");
        order.setUserid(10L);
    }

    @Test

    void testsetid(){
        Order order2 = new Order();
        long id2= 1909L ;
        order2.setId(id2);
        assertEquals(id2, order2.getId());
    }
    @Test
    void testsetuserid(){
        Order order2 = new Order();
        User user2= new User();

        user2.setId(19090l);
        long userid2= user2.getId() ;
        order2.setUserid(userid2);
        assertEquals(userid2, order2.getUserid());
    }
    @Test
    void testsetstatus(){
        Order order2 = new Order();
        String status2 = "Pending";
        order2.setStatus(status2);
        assertEquals("pending", order2.getStatus());
    }

    @Test
    void setOrderItems_NullList_ThrowsException() {
        Order order2 = new Order();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> order2.setOrderItems(null)
        );
        assertEquals("orderItems cannot be null", exception.getMessage());
    }

    @Test
    void setOrderItems_Over20Items_ThrowsException() {
        Order order2 = new Order();
        // Create list with 20 items (not 21 to avoid the full cart exception)
        for (int i = 0; i < 20; i++) {
            order2.addOrderItem(new OrderItem());
        }

        // Now try to add more items to the order
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> order2.addOrderItem(new OrderItem())  // This should throw the exception
        );
        assertEquals("Cart is full cannot add more than 20 items", exception.getMessage());
    }


    @Test
    void setOrderItems_20Items_Success() {
        Order order2 = new Order();
        // Create a list of 20 items
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            orderItems.add(new OrderItem());  // Add 20 OrderItems to the list
        }

        // Now set the orderItems to the order
        assertDoesNotThrow(() -> order2.setOrderItems(orderItems));
        assertEquals(20, order2.getOrderItems().size());  // Ensure the size is 20
    }

    @Test
    void setOrderItems_EmptyList_Success() {
        Order order2 = new Order();
        assertDoesNotThrow(() -> order2.setOrderItems(orderItems));
        assertTrue(order2.getOrderItems().isEmpty());
    }



    @Test
    void setOrderDate_Before2020_ThrowsException() {
        LocalDateTime invalidDate = LocalDateTime.of(2019, 12, 31, 23, 59);
        Order order2 = new Order();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> order2.setOrderDate(invalidDate)
        );
        assertEquals("Order date cannot be before January 1, 2020", exception.getMessage());
    }

    @Test
    void setOrderDate_ExactlyCutoff_Success() {
        LocalDateTime cutoffDate = LocalDateTime.of(2020, 1, 1, 0, 0);
        Order order2 = new Order();
        assertDoesNotThrow(() -> order2.setOrderDate(cutoffDate));
        assertEquals(cutoffDate, order2.getOrderDate());
    }

    @Test
    void setOrderDate_AfterCutoff_Success() {
        LocalDateTime validDate = LocalDateTime.of(2023, 5, 15, 14, 30);
        Order order2 = new Order();
        assertDoesNotThrow(() -> order2.setOrderDate(validDate));
        assertEquals(validDate, order2.getOrderDate());
    }

    @Test
    void setOrderDate_Null_Success() {
        Order order2 = new Order();
        assertDoesNotThrow(() -> order2.setOrderDate(null));
        assertNull(order2.getOrderDate());
    }

    @Test
    void setOrderDate_CurrentDate_Success() {
        LocalDateTime now = LocalDateTime.now();
        Order order2 = new Order();
        assertDoesNotThrow(() -> order2.setOrderDate(now));
        assertEquals(now, order2.getOrderDate());
    }
    @Test
    void testinvalidid(){
        Order order2 = new Order();
        // Test with a null name
        assertThrows(IllegalArgumentException.class, () -> {
            order2.setId(null);
        });

        // Test with an empty name
        assertThrows(IllegalArgumentException.class, () -> {
            order2.setId(Long.valueOf(""));
        });
    }
    void testinvalidStatus(){
        Order order2 = new Order();
        //test with null status invalid
        assertThrows(IllegalArgumentException.class, () -> {
            order2.setStatus(null);
        });
        //test with invalid status no status waiting
        assertThrows(IllegalArgumentException.class, () -> {
            order2.setStatus("waiting");
        });
    }
    @AfterAll
    static void afterAll(){
        orderItems.clear();
        order.setOrderItems(orderItems);
        order.setUserid(10L);
        order.setStatus("completed");
        order.setOrderDate(null);
        order.setId(12L);

    }
}

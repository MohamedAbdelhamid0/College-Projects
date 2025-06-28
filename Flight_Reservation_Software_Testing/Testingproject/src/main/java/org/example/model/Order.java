package org.example.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private Long id;
    private User user;
    private List<OrderItem> orderItems = new ArrayList<>();
    private LocalDateTime orderDate;
    private String status; // e.g., "PENDING", "COMPLETED", "CANCELLED"
    private long userid ;
    public Order() {
    }

    public Order(Long id, long userid, LocalDateTime orderDate, String status) {
        this.id = id;
        this.userid = user != null ? user.getId() : null; // Set userid based on user.getId()
        this.orderDate = orderDate;
        this.status = status;

    }

    public Order(User user){

            this.user = user;
            this.userid = user.getId();

    }
    // Getters & Setters
    public Long getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        if(user == null) {
            this.user = null;
            throw new IllegalArgumentException("user cannot be null");
        }
        this.user = user;
    }

    public void setId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        } else if (id < 0) {
            throw new IllegalArgumentException("id cannot be negative");
        } else {
            this.id = id;
        }
    }
    public long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        if (userid != null && userid < 0) {
            throw new IllegalArgumentException("userid cannot be negative");
        }
        this.userid = userid;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        if (orderItems == null) {
            throw new IllegalArgumentException("orderItems cannot be null");
        } else if (orderItems.size() > 20) {
            throw new IllegalArgumentException("orderItems cannot have more than 20 items");
        } else {
            this.orderItems = orderItems;
        }
    }

    public void setOrderDate(LocalDateTime orderDate) {
        LocalDateTime cutoff = LocalDateTime.of(2020, 1, 1, 0, 0);

        if (orderDate != null && orderDate.isBefore(cutoff)) {
            throw new IllegalArgumentException("Order date cannot be before January 1, 2020");
        }else {
            this.orderDate = orderDate;
        }
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("status cannot be null");
        }

        // Check if the status is one of the acceptable values, case-insensitive
        if (status.equalsIgnoreCase("pending") ||
                status.equalsIgnoreCase("canceled") ||
                status.equalsIgnoreCase("completed") ||
                status.equalsIgnoreCase("failed") ||
                status.equalsIgnoreCase("shipped")) {

            this.status = status.toLowerCase();  // Assign the normalized lowercase value
        } else {
            throw new IllegalArgumentException("status must be either 'pending', 'canceled', 'completed', 'failed', or 'shipped'");
        }
    }


    // Helper methods
    public void addOrderItem(OrderItem item) {
        if (orderItems.size() < 20) {
            this.orderItems.add(item);
        }else{
            throw new IllegalArgumentException("Cart is full cannot add more than 20 items");
        }

    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", orderItems=" + orderItems +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
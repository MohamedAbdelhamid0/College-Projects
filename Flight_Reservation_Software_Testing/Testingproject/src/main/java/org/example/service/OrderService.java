package org.example.service;

import org.example.model.Order;
import org.example.model.User; // Still needed

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicLong;

public class OrderService {

    private final Map<Long, Order> orderMap = new HashMap<>();
    private final AtomicLong currentId = new AtomicLong(1);

    /**
     * Creates a new order, assigning a unique ID if necessary.
     * The Order object passed in must have its User set correctly.
     * @param order The order to create. Must not be null and must have a non-null User with a non-null ID.
     * @return The created order with its assigned ID.
     * @throws NullPointerException if order is null.
     * @throws IllegalArgumentException | NullPointerException if the order's User or User ID is null or invalid (via Order's setters/getters).
     */
    public Order createOrder(Order order) {
        Objects.requireNonNull(order, "Order cannot be null");

        // These checks now use the newly added Order.getUser() method implicitly via Order.getUserid() or explicitly below.
        // Or rely on the Order constructor/setter validation. Adding checks here is defensive.
        Objects.requireNonNull(order.getUser(), "Order must have a User associated"); // Uses the new getUser()
        Objects.requireNonNull(order.getUser().getId(), "User associated with Order must have an ID"); // Uses getUser() then getId()

        // Assign a unique ID if not already set or if ID already exists/is invalid
        if (order.getId() == null || order.getId() <= 0 || orderMap.containsKey(order.getId())) {
            long newId = currentId.getAndIncrement();
            // Ensure the new ID doesn't collide (highly unlikely with AtomicLong, but safe)
            while (orderMap.containsKey(newId)) {
                newId = currentId.getAndIncrement();
            }
            order.setId(newId); // Use the setter which has validation
        } else {
            // If a valid, unique ID is provided externally, update the counter state
            // Check if this ID might clash with future IDs from the sequence
            currentId.accumulateAndGet(order.getId() + 1, Math::max);
        }

        orderMap.put(order.getId(), order);
        try {
            System.out.println("Order created: " + order.getId() + " for User ID: " + order.getUserid()); // Debugging uses refined getUserid()
        } catch (IllegalStateException e) {
            System.out.println("Order created: " + order.getId() + " but User details are invalid/missing.");
        }
        return order;
    }

    /**
     * Retrieves an order by its ID.
     * @param id The ID of the order to retrieve. Must not be null.
     * @return The order if found, otherwise null.
     * @throws NullPointerException if id is null.
     */
    public Order getOrderById(Long id) {
        Objects.requireNonNull(id, "ID cannot be null for getOrderById");
        return orderMap.get(id);
    }

    /**
     * Updates an existing order. Ensures the order and its ID are not null, and the User is valid.
     * @param order The order with updated details. Must have ID and valid User.
     * @return The updated order if found and updated, otherwise null (or consider throwing exception).
     * @throws NullPointerException if order, order ID, order User, or User ID is null.
     * @throws IllegalArgumentException if the order to update is not found in the map.
     */
    public Order updateOrder(Order order) {
        Objects.requireNonNull(order, "Order cannot be null for update");
        Objects.requireNonNull(order.getId(), "Order ID cannot be null for update");

        // Re-check user consistency during update using the new methods/logic
        Objects.requireNonNull(order.getUser(), "Order must retain a User association during update");
        Objects.requireNonNull(order.getUser().getId(), "User associated with Order must have an ID during update");

        // Use computeIfPresent for atomic-like update check
        Order updatedOrder = orderMap.computeIfPresent(order.getId(), (key, existingOrder) -> order);

        if (updatedOrder != null) {
            try{
                System.out.println("Order updated: " + order.getId() + " for User ID: " + order.getUserid()); // Debugging
            } catch(IllegalStateException e){
                System.out.println("Order updated: " + order.getId() + " but User details invalid.");
            }
            return updatedOrder; // Return the order that was put in the map
        } else {
            System.err.println("Update failed: Order not found with ID " + order.getId()); // Debugging
            // Consider throwing an exception instead of returning null
            throw new IllegalArgumentException("Update failed: Order not found with ID " + order.getId());
            // return null;
        }
    }

    /**
     * Removes an order by its ID.
     * @param id The ID of the order to remove. Must not be null.
     * @return The removed order, or null if no order was found with that ID.
     * @throws NullPointerException if id is null.
     */
    public Order deleteOrder(Long id) {
        Objects.requireNonNull(id, "ID cannot be null for deleteOrder");
        Order removedOrder = orderMap.remove(id);
        if (removedOrder != null) {
            System.out.println("Order deleted: " + id); // Debugging
        } else {
            System.err.println("Deletion failed: Order not found with ID " + id); // Debugging
        }
        return removedOrder;
    }

    /**
     * Retrieves all orders currently stored.
     * @return A new list containing all orders. Returns an empty list if none exist.
     */
    public List<Order> getAllOrders() {
        // Return a defensive copy
        return new ArrayList<>(orderMap.values());
    }

    /**
     * Retrieves all orders placed by a specific user ID.
     * @param userId The ID of the user whose orders are to be retrieved. Must be positive.
     * @return A new list containing the orders for the specified user. Returns an empty list if none found.
     * @throws IllegalArgumentException if userId is not positive.
     */
    public List<Order> getOrdersByUserId(long userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be positive.");
        }
        // Uses the refined Order.getUserid() method which derives ID from the User object
        return orderMap.values().stream()
                .filter(order -> {
                    try {
                        // Check if the order's associated user ID matches the requested userId
                        // This now correctly uses the getter that derives from the User object
                        return order.getUser() != null && order.getUserid() == userId;
                    } catch (IllegalStateException e) {
                        // This catch handles cases where getUserid() fails (e.g., user or user.id is null)
                        System.err.println("Filtering orders warning: Could not get valid User ID for Order ID "
                                + (order.getId() != null ? order.getId() : "UNKNOWN") + ". Skipping. Error: " + e.getMessage());
                        return false; // Exclude orders with invalid users from the result
                    } catch (Exception e) {
                        // Catch any other unexpected error during filtering
                        System.err.println("Unexpected error filtering order ID "
                                + (order.getId() != null ? order.getId() : "UNKNOWN") + ": " + e.getMessage());
                        // Consider logging the stack trace for debugging
                        // e.printStackTrace();
                        return false; // Exclude on error
                    }
                })
                .collect(Collectors.toList());
    }
}
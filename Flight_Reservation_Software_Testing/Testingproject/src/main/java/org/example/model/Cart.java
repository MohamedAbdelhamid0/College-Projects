package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {
    private Long id;
    private User user;
    private List<CartItem> items = new ArrayList<>();


    public Long userid;

    public Cart(Long id, Long userid, List<CartItem> items) {
        this.id = id;
        this.userid = userid; // Use the provided userid directly
        this.items = new ArrayList<>(); // Initialize items list before adding
        if (items != null) { // Avoid NullPointerException if items is null
            items.forEach(item -> this.items.add(item)); // Add each item to the list
        }
    }

    public Cart(){

    }
    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null) {
            this.id = id;
            throw new IllegalArgumentException("id cannot be null");
        } else if (id < 0) {
            this.id = null;
            throw new IllegalArgumentException("id cannot be negative");
        } else {
            this.id = id;
        }
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        if (userid == null){
            throw new IllegalArgumentException("userid cannot be null");
        }else if (userid < 0)
        {
            throw new IllegalArgumentException("userid cannot be negative");
        }
        this.userid = userid;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        if (items == null) {
            throw new IllegalArgumentException("Items list cannot be null");
        }
        else if (items.size() > 20) {
            throw new IllegalArgumentException("Items list cannot contain more than 20 items");
        }else{
        this.items = items;
        }
    }

    // Helper methods
    public void addItem(CartItem item) {
        if (items.size() < 20) {
            this.items.add(item);
        } else {
            throw new IllegalArgumentException("Cart is full cannot add more than 20 items");
        }
    }

    public void removeItem(CartItem item) {
        if (items != null) {
            this.items.remove(item);
        } else {
            throw new IllegalArgumentException("Cart is empty cannot remove item");
        }
    }

    public void addOrUpdateItem(CartItem item) {
        boolean found = false;
        int totalQuantity = 0;

        // Check if the product already exists in the cart
        for (CartItem existingItem : items) {
            totalQuantity += existingItem.getQuantity();  // Add the quantity of existing items

            if (Objects.equals(existingItem.getProductId(), item.getProductId())) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                found = true;
                break;
            }
            if(totalQuantity > 20){
                throw new IllegalArgumentException("Cart is full cannot add more than 20 items");
            }
        }

        // Check if adding this item would exceed the total limit of 20 items
        if (!found) {
            totalQuantity += item.getQuantity();  // Include the quantity of the new item

            if (totalQuantity > 20) {
                throw new IllegalArgumentException("Adding this item would exceed the limit of 20 items in the cart.");
            }
            // Add the new item if total quantity is within limit
            addItem(item);
        }
    }

    public void removeItemByProductIdAndQuantity(Long productId, int quantityToRemove) {
        // Ensure that the quantity to remove is positive
        if (quantityToRemove <= 0) {
            throw new IllegalArgumentException("Quantity to remove must be greater than 0");
        }

        // Loop through the items and update the quantity or remove the item if quantity reaches zero
        items.removeIf(item -> {
            if (Objects.equals(item.getProductId(), productId)) {
                int newQuantity = item.getQuantity() - quantityToRemove;

                if (newQuantity <= 0) {
                    // Remove the item if the quantity becomes zero or less
                    return true;
                } else {
                    // Update the quantity of the item if it's still positive
                    item.setQuantity(newQuantity);
                    return false;  // Do not remove the item
                }
            }
            return false;  // Do not remove items that don't match the productId
        });
    }



    public void removeItemByProductId(Long productId) {
        items.removeIf(item -> Objects.equals(item.getProductId(), productId));
    }

    public void clearItems() {
        items.clear();
    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

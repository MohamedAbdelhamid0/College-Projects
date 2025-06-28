package org.example.model;


import java.math.BigDecimal;
import java.util.Objects;

public class CartItem {
    private Long id;
    private Product product;
    private int quantity;
    private Long Productid ;
    public CartItem() {
    }

    public CartItem(Long id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }
    public CartItem(Long id, Product product, int quantity, Long productid) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.Productid = product != null ? product.getId() : null;
        this.Productid = productid;
    }
    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if(id == null) {
            this.id = id;
            throw new IllegalArgumentException("Id cannot be null");
        }else if(id < 0) {
            this.id = null;
            throw new IllegalArgumentException("Id cannot be negative");
        }
        this.id = id;
    }
    public long getProductId() {
        return Productid;
    }
    public void setProductId(Long productId) {
        if(productId == null) {
            this.Productid = null;
            throw new IllegalArgumentException("ProductId cannot be null");
        }else if(productId < 0) {
            this.Productid = null;
            throw new IllegalArgumentException("ProductId cannot be negative");
        }else{
            this.Productid = productId;
        }
    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        if(quantity < 0){
            quantity = 0;
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    // Helper: calculate subtotal for this item
    public double getSubTotal() {
        return product.getPrice() * quantity;
    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(id, cartItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


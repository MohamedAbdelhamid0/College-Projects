package org.example.model;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem {
    private Long id;
    private Product product;
    private int quantity;
    private BigDecimal priceAtPurchase; // Price at the time of order
    private Long productId;
    public OrderItem() {
    }

    public OrderItem(Long id, Product product, int quantity, BigDecimal priceAtPurchase) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if(id != null) {
            this.id = id;
            throw new IllegalArgumentException("id cannot be null");
        }
        this.id = id;
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

    public void setQuantity(int quantity) {
        if(quantity < 0) {
            throw new IllegalArgumentException("quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public BigDecimal getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(BigDecimal priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    // Helper: calculate subtotal for this order item
    public BigDecimal getSubTotal() {
        return priceAtPurchase.multiply(BigDecimal.valueOf(quantity));
    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", priceAtPurchase=" + priceAtPurchase +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem that = (OrderItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}


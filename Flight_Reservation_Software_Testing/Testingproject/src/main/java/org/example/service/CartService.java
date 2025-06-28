package org.example.service;

import org.example.model.Cart;
import org.example.model.CartItem;
import org.example.model.User;
import org.example.model.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CartService {
    private final Map<Long, Cart> cartMap = new HashMap<>();
    private final Map<Long, CartItem> cartItemMap = new HashMap<>();
    private List cartitems=new ArrayList();

    public Cart getOrCreateCart(User user) {
        Objects.requireNonNull(user, "User cannot be null");
        Objects.requireNonNull(user.getId(), "User ID cannot be null");
        Long userId = user.getId();
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be positive to get or create cart.");
        }

        return cartMap.computeIfAbsent(userId, id -> new Cart(id, userId,cartitems));
    }

    public Cart createCart(Client client) {
        Objects.requireNonNull(client, "Client cannot be null");
        Objects.requireNonNull(client.getId(), "Client ID cannot be null");
        Long clientId = client.getId();
        if (clientId <= 0) {
            throw new IllegalArgumentException("Client ID must be positive to create cart.");
        }

        if (cartMap.containsKey(clientId)) {
            return cartMap.get(clientId);
        }

        Cart cart = new Cart(023l,clientId, cartitems);
        cartMap.put(clientId, cart);
        return cart;
    }

    public Cart getCartByUserId(Long userId) {
        if (userId == null || userId <= 0) return null;
        return cartMap.get(userId);
    }

    public Cart addItemToCart(User user, CartItem item) {
        Cart cart = getOrCreateCart(user);cart.addOrUpdateItem(item);
        return cart;
    }

    public Cart removeItemFromCart(Long userId, CartItem item) {
        Objects.requireNonNull(userId, "User ID cannot be null");
        Objects.requireNonNull(item, "CartItem cannot be null");

        Cart cart = cartMap.get(userId);
        if (cart != null) {
            cart.removeItem(item);
        }
        return cart;
    }

    public Cart removeItemFromCartByProductId(Long userId, Long productId) {
        Objects.requireNonNull(userId, "User ID cannot be null");
        Objects.requireNonNull(productId, "Product ID cannot be null");

        Cart cart = cartMap.get(userId);
        if (cart != null) {
            cart.removeItemByProductId(productId);
        }
        return cart;
    }

    public Cart clearCart(Long userId) {
        Objects.requireNonNull(userId, "User ID cannot be null");
        Cart cart = cartMap.get(userId);
        if (cart != null) {
            cart.clearItems();
        }
        return cart;
    }

    public Cart removeCartByUserId(Long userId) {
        if (userId == null || userId <= 0) return null;
        return cartMap.remove(userId);
    }

    public List<Cart> getAllCarts() {
        return new ArrayList<>(cartMap.values());
    }
}
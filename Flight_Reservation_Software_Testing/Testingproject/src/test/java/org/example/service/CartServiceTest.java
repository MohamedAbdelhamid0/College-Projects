package org.example.service;

import org.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest {
    private CartService cartService;
    private User testUser;
    private Client testClient;
    private CartItem testItem1;
    private CartItem testItem2;
    public Product testProduct1;
    public Product testProduct2;
    private Category testCategory;
    private CartItem updatedItem;
    @BeforeEach
    void setUp() {
        testCategory = new Category();
        cartService = new CartService();
        testUser = new User(1L, "Test User", "user@gmail.com","User222!");
        testClient = new Client(2L, "Test Client", "client@gmail.com","Client222!",1000.0);
        testItem1 = new CartItem(101L, testProduct1, 2,012L);
        testItem2 = new CartItem(102L, testProduct2, 1,013L);
        updatedItem = new CartItem(101L, testProduct2, 3,012L);
        testProduct1 = new Product(012L,"Nike Phantom ","Nike Phantom neymar 2017",150.0,200,testCategory);
        testProduct2 = new Product(013L,"Adidas Real Madrid kit","2024/2025 Real Madrid kit",180.0,900,testCategory);
    }

    @Test
    void getOrCreateCart_ShouldCreateNewCartForNewUser() {
        Cart cart = cartService.getOrCreateCart(testUser);
        assertNotNull(cart);
        assertEquals(testUser.getId(), cart.getId());
        assertTrue(cart.getItems().isEmpty());
    }

    @Test
    void getOrCreateCart_ShouldReturnExistingCart() {
        Cart firstCart = cartService.getOrCreateCart(testUser);
        Cart secondCart = cartService.getOrCreateCart(testUser);
        assertSame(firstCart, secondCart);
    }

    @Test
    void createCart_ShouldCreateClientSpecificCart() {
        Cart clientCart = cartService.createCart(testClient);
        assertNotNull(clientCart);

        assertEquals(testClient.getId(), clientCart.getUserid());
    }

    @Test
    void getCartByUserId_ShouldReturnNullForNonExistentUser() {
        assertNull(cartService.getCartByUserId(999L));
    }

    @Test
    void addItemToCart_ShouldAddNewItems() {
        Cart cart = cartService.addItemToCart(testUser, testItem1);
        assertEquals(1, cart.getItems().size());
        assertTrue(cart.getItems().contains(testItem1));
    }

    @Test
    void addItemToCart_ShouldUpdateExistingItems() {

        cartService.addItemToCart(testUser, testItem1);



        // Now update the cart with the new quantity
        Cart cart = cartService.addItemToCart(testUser, updatedItem);

        assertEquals(1, cart.getItems().size());
        assertEquals(5, cart.getItems().get(0).getQuantity());
    }

    @Test
    void removeItemFromCart_ShouldRemoveExistingItem() {
        cartService.addItemToCart(testUser, testItem1);
        Cart cart = cartService.removeItemFromCart(testUser.getId(), testItem1);

        assertTrue(cart.getItems().isEmpty());
    }

    @Test
    void removeItemFromCartByProductId_ShouldRemoveCorrectItem() {
        cartService.addItemToCart(testUser, testItem1);
        cartService.addItemToCart(testUser, testItem2);

        Cart cart = cartService.removeItemFromCartByProductId(testUser.getId(), 012L);
        assertEquals(1, cart.getItems().size());

    }

    @Test
    void clearCart_ShouldRemoveAllItems() {
        cartService.addItemToCart(testUser, testItem1);
        cartService.addItemToCart(testUser, testItem2);

        Cart cart = cartService.clearCart(testUser.getId());
        assertTrue(cart.getItems().isEmpty());
    }

    @Test
    void removeCartByUserId_ShouldDeleteCart() {
        cartService.getOrCreateCart(testUser);
        Cart removedCart = cartService.removeCartByUserId(testUser.getId());

        assertNotNull(removedCart);
        assertNull(cartService.getCartByUserId(testUser.getId()));
    }

    @Test
    void getAllCarts_ShouldReturnAllActiveCarts() {
        cartService.getOrCreateCart(testUser);
        cartService.createCart(testClient);

        List<Cart> allCarts = cartService.getAllCarts();
        assertEquals(2, allCarts.size());
    }

    @Test
    void shouldThrowWhenCreatingCartWithInvalidUserId() {
        User invalidUser = new User(-1L, "Invalid", "invalid@gmail.com","Seif2004!");

        assertThrows(IllegalArgumentException.class,
                () -> cartService.getOrCreateCart(invalidUser));
    }

    @Test
    void shouldHandleNullUserIdInRemoveOperations() {
        assertThrows(NullPointerException.class,
                () -> cartService.removeItemFromCart(null, testItem1));
    }

    @Test
    void shouldHandleNonExistentUserInOperations() {
        Cart result = cartService.removeItemFromCart(999L, testItem1);
        assertNull(result);
    }
}
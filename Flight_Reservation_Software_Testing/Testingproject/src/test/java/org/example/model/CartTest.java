package org.example.model;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class CartTest {
    private static Cart cart;
    private static User user;
    private static List<CartItem> items = new ArrayList<>();
    static long id ;
    static long userid ;
    private static Product product;
    private static Product product1;
    private static Product product2;
    private static CartItem item1;
    private static CartItem item2;
    private static Category Books;
    private static Category games;
    @BeforeAll
    public static void setUp() {
        // Initialize cart object and items
        cart = new Cart(01l,123l,items);



        product1 = new Product(1L, "Product 1","Product 1 2024" ,100.0,200,Books);  // Initialize product1
        product2 = new Product(2L, "Product 2","product 2 2025" ,50.0,1000,games);   // Initialize product2

        item1 = new CartItem(1L, product1, 10, 520L);  // Product ID 1, Quantity 10
        item2 = new CartItem(2L, product2, 5, 72L);   // Product ID 2, Quantity 5

        cart.getItems().add(item1);
        cart.getItems().add(item2);
    }

    @Test
    @Order(1)
    void Testid() {
        long id2 = 12345 ;
        cart.setId(id2);
        assertEquals(id2, cart.getId());
    }
    @Test
    @Order(2)
    void Testuserid() {
        User user2 = new User();
        user2.setId(1909L);

        Cart cart = new Cart(); // Ensure that cart is properly initialized
        cart.setUserid(user2.getId()); // Link the cart to user2

        long userid3 = user2.getId();
        assertEquals(userid3, cart.getUserid()); // Check if user ID is correctly passed to cart
        assertEquals(1909L, cart.getUserid()); // Check if cart reads the correct ID

    }
    @Test
    @Order(3)
    public void testSetItems() {
        Cart cart = new Cart();

        // Test with a list of more than 20 items
        assertThrows(IllegalArgumentException.class, () -> {
            cart.setItems(List.of(new CartItem(), new CartItem(), new CartItem(),
                    new CartItem(), new CartItem(), new CartItem(),
                    new CartItem(), new CartItem(), new CartItem(),
                    new CartItem(), new CartItem(), new CartItem(),
                    new CartItem(), new CartItem(), new CartItem(),
                    new CartItem(), new CartItem(), new CartItem(),
                    new CartItem(), new CartItem(), new CartItem(),
                    new CartItem(), new CartItem(), new CartItem()));
        });

        // Test with a valid list of 20 items
        assertDoesNotThrow(() -> {
            cart.setItems(List.of(new CartItem(), new CartItem(), new CartItem(),
                    new CartItem(), new CartItem(), new CartItem(),
                    new CartItem(), new CartItem(), new CartItem(),
                    new CartItem(), new CartItem(), new CartItem(),
                    new CartItem(), new CartItem(), new CartItem(),
                    new CartItem(), new CartItem(), new CartItem(),
                    new CartItem(), new CartItem()));
        });

        // Test with a list of fewer than 20 items
        assertDoesNotThrow(() -> {
            cart.setItems(List.of(new CartItem(), new CartItem()));
        });
    }
    @Test
    public void testAddItem() {
        // Create a new cart
        Cart cart = new Cart();

        // Create a new items list
        List<CartItem> items = new ArrayList<>();

        // Create a new product and add it to the cart
        Product product2 = new Product();
        product2.setId(23L);
        CartItem cartItem = new CartItem(123L, product2, 3);
        items.add(cartItem);

        // Add items to the cart
        cart.setItems(items);



        // Check that the cart has one item
        assertEquals(1, cart.getItems().size(), "Cart should have 1 item");

        // Check that the item in the cart is the one added
        CartItem addedItem = cart.getItems().get(0);

        // Validate that the product ID of the added item is 0123L
        assertEquals(23L, addedItem.getProduct().getId(), "Product ID should be 0123L");

        // Validate the quantity of the added item
        assertEquals(3, addedItem.getQuantity(), "Quantity should be 3");
    }
    @Test
    public void testAddOrUpdateItem_NewItem() {


        // Add item1 to the cart
        cart.addOrUpdateItem(item1);
        assertEquals(2, cart.getItems().size());
        assertEquals(20, cart.getItems().get(0).getQuantity());
    }

    @Test
    public void testAddOrUpdateItem_UpdateExistingItem() {
        // Add item1 to the cart
        cart.addOrUpdateItem(item1);

        // Add item1 again with a different quantity to update it
        CartItem updatedItem1 = new CartItem(1L, product1, 5, 520L); // Product ID 1, Quantity 5
        cart.addOrUpdateItem(updatedItem1);
        cart.removeItemByProductIdAndQuantity(item1.getProduct().getId(), 7);
        assertEquals(2, cart.getItems().size());
        assertEquals(18, cart.getItems().get(0).getQuantity());  // 10 + 5
    }
    @Test
    public void removewrongquantity() {
        assertThrows(IllegalArgumentException.class, () -> {
            cart.removeItemByProductIdAndQuantity(item1.getProduct().getId(), -2);
        });
    }
    @Test
    public void testRemoveItemByProductId() {
        cart.addOrUpdateItem(item1);
        cart.addOrUpdateItem(item2);

        // Ensure both items are added
        assertEquals(2, cart.getItems().size());

        // Remove item1 by product ID
        cart.removeItemByProductId(1L);

        // Check that item1 is removed and item2 remains
        assertEquals(1, cart.getItems().size());
        assertEquals(2L, cart.getItems().get(0).getProductId());  // Only item2 should remain
    }
    @Test
    public void testClearItems() {
        cart.addOrUpdateItem(item1);
        cart.addOrUpdateItem(item2);

        // Ensure both items are added
        assertEquals(2, cart.getItems().size());

        // Clear all items from the cart
        cart.clearItems();

        // Check that the cart is empty
        assertTrue(cart.getItems().isEmpty());
    }

    @Test
    void testquantity(){
        CartItem cartItem = new CartItem();
        /// test adding negative quantity
        assertThrows(IllegalArgumentException.class, () -> {
            cartItem.setQuantity(-3);
        });
        cartItem.setQuantity(3);//check valid value
        assertEquals(3, cartItem.getQuantity(), "Quantity should be 3");
    }
    @AfterAll
    public static void tearDown() {
        List<CartItem> items2 = new ArrayList<>();
       if(cart!=null) {
           cart.setId(0l);
           cart.setUserid(0l);
           cart.setItems(items2);

       }
    }
}
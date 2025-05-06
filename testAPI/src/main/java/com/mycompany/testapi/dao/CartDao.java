package com.mycompany.testapi.dao;

import com.mycompany.testapi.model.Cart;
import com.mycompany.testapi.model.Cartitem;
import java.util.HashMap;
import java.util.Map;

public class CartDao {
    private static final Map<Integer, Cart> carts = new HashMap<>();
    private static int currentId = 1;

    public Cart createCart(int customerId) {
        Cart cart = new Cart(currentId++, customerId);
        carts.put(customerId, cart);
        return cart;
    }

    public Cart getCartByCustomerId(int customerId) {
        return carts.get(customerId);
    }

    public void addItemToCart(int customerId, Cartitem item) {
        Cart cart = carts.get(customerId);
        cart.getItems().add(item);
    }

    public void updateCartItem(int customerId, int bookId, int quantity) {
        Cart cart = carts.get(customerId);
        for (Cartitem item : cart.getItems()) {
            if (item.getBookId() == bookId) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

    public void removeItemFromCart(int customerId, int bookId) {
        Cart cart = carts.get(customerId);
        cart.getItems().removeIf(item -> item.getBookId() == bookId);
    }

    public void clearCart(int customerId) {
        carts.remove(customerId);
    }
}

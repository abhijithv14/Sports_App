package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private final List<Cart_Item> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItemToCart(Cart_Item cartItem) {
        cartItems.add(cartItem);
    }

    public List<Cart_Item> getCartItems() {
        return cartItems;
    }
}

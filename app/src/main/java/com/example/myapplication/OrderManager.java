package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static OrderManager instance;
    private final List<Cart_Item> orders;

    private OrderManager() {
        orders = new ArrayList<>();
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void addOrder(Cart_Item order) {
        orders.add(order);
    }

    public List<Cart_Item> getOrders() {
        return orders;
    }
}

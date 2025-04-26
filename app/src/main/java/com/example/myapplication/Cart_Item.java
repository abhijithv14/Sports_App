package com.example.myapplication;

public class Cart_Item {
    private int imageResId;
    private String name;
    private String price;

    private int Quantity;

    public Cart_Item(int imageResId, String name, String price,int Quantity) {
        this.imageResId = imageResId;
        this.name = name;
        this.price = price;
        this.Quantity=Quantity;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}


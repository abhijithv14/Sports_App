package com.example.myapplication;

public class Item {
    private int imageResId;
    private String name;
    private String price;


    public Item(int imageResId, String name, String price) {
        this.imageResId = imageResId;
        this.name = name;
        this.price = price;
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

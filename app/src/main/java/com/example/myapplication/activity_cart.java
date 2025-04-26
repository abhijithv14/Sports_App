package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class activity_cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Home Button to Navigate to Home Screen
        ImageView homeButton = findViewById(R.id.navIcon);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_cart.this, home.class);
                startActivity(intent);
                finish();
            }
        });

        // "Go to Orders" Button to Navigate to Orders Screen
        Button orderButton = findViewById(R.id.order_button);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to Orders Activity
                Intent intent = new Intent(activity_cart.this,activity_order.class);
                startActivity(intent);
            }
        });

        // Set up RecyclerView
        List<Cart_Item> cartItems = CartManager.getInstance().getCartItems();

        RecyclerView recyclerView = findViewById(R.id.recycler_view_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CartAdapter(this, cartItems)); // Use cartItems instead of cartItem
    }
}

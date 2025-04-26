package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class activity_order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        RecyclerView recyclerView = findViewById(R.id.recycler_view_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<Cart_Item> orders = OrderManager.getInstance().getOrders();
        OrdersAdapter adapter = new OrdersAdapter(this, orders);
        recyclerView.setAdapter(adapter);


        ImageView homeButton = findViewById(R.id.navIcon);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(activity_order.this, home.class);
            startActivity(intent);
            finish();
        });
    }
}

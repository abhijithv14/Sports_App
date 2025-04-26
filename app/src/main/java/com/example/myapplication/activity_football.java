package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
public class activity_football extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);

        GridView gridView = findViewById(R.id.gridView);
        ImageView homeButton = findViewById(R.id.navIcon);

        // Navigate to HomeActivity on home button click
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_football.this, home.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity
            }
        });

        ImageView cart = findViewById(R.id.cartButton);
        cart.setOnClickListener(v -> {
            Intent intent = new Intent(this, activity_cart.class);
            startActivity(intent);});

        // Sample data for football items
        List<Item> items = new ArrayList<>();
        items.add(new Item(R.drawable.football_standard, "Football (Standard Size)", "1200.0"));
        items.add(new Item(R.drawable.football_boots, "Football Boots", "3500.0"));
        items.add(new Item(R.drawable.gloves, "Football Gloves", "1500.0"));
        items.add(new Item(R.drawable.shin_guards, "Shin Guards", "400.0"));
        items.add(new Item(R.drawable.training_cone, "Training Cones", "600.0"));
        items.add(new Item(R.drawable.water_bottle, "Water Bottle", "400.0"));
        items.add(new Item(R.drawable.football_bag, "Football Bag", "1500.0"));
        items.add(new Item(R.drawable.training_ladder, "Training Ladder", "1200.0"));
        items.add(new Item(R.drawable.ball_pump, "Ball Pump", "700.0"));

        // Set adapter
        GridAdapter adapter = new GridAdapter(this, items);
        gridView.setAdapter(adapter);
    }
}

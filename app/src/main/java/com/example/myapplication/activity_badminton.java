package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class activity_badminton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badminton);

        GridView gridView = findViewById(R.id.gridView);
        ImageView homeButton = findViewById(R.id.navIcon);

        // Navigate to HomeActivity on home button click
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_badminton.this, home.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity
            }
        });

        ImageView cart = findViewById(R.id.cartButton);
        cart.setOnClickListener(v -> {
            Intent intent = new Intent(this, activity_cart.class);
            startActivity(intent);});

        // Sample data for badminton items
        List<Item> items = new ArrayList<>();
        items.add(new Item(R.drawable.badminton_racquet, "Badminton Racquet", "2000.0"));
        items.add(new Item(R.drawable.shuttlecocks, "Shuttlecocks", "500.0"));
        items.add(new Item(R.drawable.badminton_net, "Badminton Net", "1500.0"));
        items.add(new Item(R.drawable.grip_tape, "Grip Tape", "300.0"));
        items.add(new Item(R.drawable.shoe_b, "Badminton Shoes", "2500.0"));
        items.add(new Item(R.drawable.badminton_bag, "Badminton Bag", "1800.0"));
        items.add(new Item(R.drawable.sweatbands, "Sweatbands", "400.0"));
        items.add(new Item(R.drawable.training_cone, "Training Cones", "600.0"));
        items.add(new Item(R.drawable.water_bottle, "Water Bottle", "400.0"));

        // Set adapter
        GridAdapter adapter = new GridAdapter(this, items);
        gridView.setAdapter(adapter);
    }
}

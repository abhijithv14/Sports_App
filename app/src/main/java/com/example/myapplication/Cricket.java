package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class Cricket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricket);

        GridView gridView = findViewById(R.id.gridView);
        ImageView homeButton = findViewById(R.id.navIcon);

        // Navigate to HomeActivity on home button click
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cricket.this, home.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity
            }
        });

        ImageView cart = findViewById(R.id.cartButton);
        cart.setOnClickListener(v -> {
            Intent intent = new Intent(this, activity_cart.class);
            startActivity(intent);});

        // Sample data
        List<Item> items = new ArrayList<>();
        items.add(new Item(R.drawable.cric_bat_kashmiri, "Kashmiri Bat", "1500.0"));
        items.add(new Item(R.drawable.english_willow, "English Willow Bat", "2500.0"));
        items.add(new Item(R.drawable.cric_ball_red, "Red Cricket Ball", "300.0"));
        items.add(new Item(R.drawable.cric_ball_tennis, "Yellow Cricket Ball", "80.0"));
        items.add(new Item(R.drawable.cric_ball_white, "White Cricket Ball", "350.0"));
        items.add(new Item(R.drawable.cric_gloves, "Batting Gloves", "600.0"));
        items.add(new Item(R.drawable.cric_gloves_wicket, "Wicketkeeping Gloves", "900.0"));
        items.add(new Item(R.drawable.cric_helmet_jpeg, "Helmet", "1500.0"));
        items.add(new Item(R.drawable.cric_pads_jpeg, "Cricket Pads", "1200.0"));
        items.add(new Item(R.drawable.cric_shoes, "Shoes", "1800.0"));
        items.add(new Item(R.drawable.cric_kit_bag_jpeg, "Kit Bags", "2000.0"));

        // Set adapter
        GridAdapter adapter = new GridAdapter(this, items);
        gridView.setAdapter(adapter);
    }
}

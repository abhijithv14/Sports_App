package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class activity_jersey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jersey);

        GridView gridView = findViewById(R.id.gridView);
        ImageView homeButton = findViewById(R.id.navIcon);

        // Navigate to HomeActivity on home button click
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_jersey.this, home.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity
            }
        });
        ImageView cart = findViewById(R.id.cartButton);
        cart.setOnClickListener(v -> {
            Intent intent = new Intent(this, activity_cart.class);
            startActivity(intent);});

        // Sample data for jersey items
        List<Item> items = new ArrayList<>();
        items.add(new Item(R.drawable.jersey_argentina, "Argentina Jersey", "2800.0"));
        items.add(new Item(R.drawable.jersey_brazil, "Brazil Jersey", "2900.0"));
        items.add(new Item(R.drawable.jersey_india, "India Jersey", "3000.0"));
        items.add(new Item(R.drawable.jersey_france, "France Jersey", "2700.0"));
        items.add(new Item(R.drawable.jersey_portugal, "Portugal Jersey", "2700.0"));
        items.add(new Item(R.drawable.jersey_barcelona_home, "Barcelona Jersey Home", "2500.0"));
        items.add(new Item(R.drawable.jersey_barcelona_away, "Barcelona Jersey Away", "2100.0"));
        items.add(new Item(R.drawable.jersey_real_madrid, "Real Madrid Jersey", "2000.0"));
        items.add(new Item(R.drawable.jersey_bayern, "Bayern Munich Jersey", "1800.0"));
        items.add(new Item(R.drawable.jersey_chelsea, "Chelsea Jersey", "1800.0"));
        items.add(new Item(R.drawable.jersey_csk, "CSK Jersey", "1500.0"));
        items.add(new Item(R.drawable.jersey_rajasthan, "Rajasthan Jersey", "1500.0"));
        items.add(new Item(R.drawable.jersey_rcb, "RCB Jersey", "1500.0"));
        items.add(new Item(R.drawable.jersey_mi, "MI Jersey", "1500.0"));

        // Set adapter
        GridAdapter adapter = new GridAdapter(this, items);
        gridView.setAdapter(adapter);
    }
}

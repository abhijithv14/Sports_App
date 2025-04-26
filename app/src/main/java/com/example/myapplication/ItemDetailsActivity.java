package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        // Get references to views
        ImageView itemImage = findViewById(R.id.item_details_image);
        TextView itemName = findViewById(R.id.item_details_name);
        TextView itemPrice = findViewById(R.id.item_details_price);
        Spinner quantitySpinner = findViewById(R.id.quantity_spinner);
        Button buyNowButton = findViewById(R.id.buy_now_button);
        Button addToCartButton = findViewById(R.id.add_to_cart_button);
        Button viewCartButton = findViewById(R.id.view_cart_button);

        // Get data from Intent
        int imageResId = getIntent().getIntExtra("imageResId", R.drawable.icon);
        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");

        // Populate views with data
        itemImage.setImageResource(imageResId);
        itemName.setText(name);
        itemPrice.setText("₹" + price);

        // Populate quantity spinner
        String[] quantities = {"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, quantities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);

        // Buy Now Button
        buyNowButton.setOnClickListener(view -> {
            String selectedQuantity = quantitySpinner.getSelectedItem().toString();
            int quantity = Integer.parseInt(selectedQuantity);

            Cart_Item cartItem = new Cart_Item(imageResId, name, String.valueOf(Float.parseFloat(price) * quantity), quantity);
            showAddressDialog(this, cartItem);
        });

        // Add to Cart Button
        addToCartButton.setOnClickListener(v -> {
            String selectedQuantity = quantitySpinner.getSelectedItem().toString();
            int quantity = Integer.parseInt(selectedQuantity);

            Cart_Item cartItem = new Cart_Item(imageResId, name, String.valueOf(Float.parseFloat(price) * quantity), quantity);
            CartManager.getInstance().addItemToCart(cartItem);
            Toast.makeText(this, "Added to Cart!", Toast.LENGTH_SHORT).show();
        });

        // View Cart Button
        viewCartButton.setOnClickListener(view -> startActivity(new Intent(this, activity_cart.class)));
    }

    private void showAddressDialog(Context context, Cart_Item item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_address_input, null);
        builder.setView(dialogView);

        EditText addressEditText = dialogView.findViewById(R.id.edit_text_address);
        EditText pinCodeEditText = dialogView.findViewById(R.id.edit_text_pin_code);

        builder.setTitle("Enter Address and PIN Code")
                .setPositiveButton("Next", (dialog, which) -> {
                    String address = addressEditText.getText().toString().trim();
                    String pinCode = pinCodeEditText.getText().toString().trim();

                    if (address.isEmpty() || pinCode.isEmpty()) {
                        Toast.makeText(context, "Address and PIN Code cannot be empty", Toast.LENGTH_SHORT).show();
                    } else {
                        showPaymentDialog(context, item);
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    private void showPaymentDialog(Context context, Cart_Item item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select Payment Method")
                .setItems(new CharSequence[]{"Cash on Delivery", "GPay", "PhonePe"}, (dialog, which) -> {
                    switch (which) {
                        case 0: // Cash on Delivery
                            placeOrder(context, item, "Cash on Delivery");
                            break;
                        case 1: // GPay
                        case 2: // PhonePe
                            verifyUPI(context, item, which == 1 ? "GPay" : "PhonePe");
                            break;
                    }
                });

        builder.create().show();
    }

    private void verifyUPI(Context context, Cart_Item item, String method) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_upi_verification, null);
        builder.setView(dialogView);

        EditText upiEditText = dialogView.findViewById(R.id.edit_text_upi);

        builder.setTitle("Verify UPI ID")
                .setPositiveButton("Verify", (dialog, which) -> {
                    String upiId = upiEditText.getText().toString().trim();
                    if (upiId.isEmpty()) {
                        Toast.makeText(context, "UPI ID cannot be empty", Toast.LENGTH_SHORT).show();
                    } else if (upiId.equals("validupi@upi")) {
                        placeOrder(context, item, method);
                    } else {
                        Toast.makeText(context, "Invalid UPI ID", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    private void placeOrder(Context context, Cart_Item item, String method) {
        Toast.makeText(context, "Order placed for " + item.getName() + " using " + method, Toast.LENGTH_SHORT).show();
        OrderManager.getInstance().addOrder(item);
    }
}

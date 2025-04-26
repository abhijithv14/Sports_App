package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final Context context;
    private final List<Cart_Item> cartItems;

    public CartAdapter(Context context, List<Cart_Item> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_row, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart_Item item = cartItems.get(position);

        holder.productImage.setImageResource(item.getImageResId());
        holder.productName.setText(item.getName());
        holder.productTotalPrice.setText(item.getPrice());
        holder.productQuantity.setText(String.valueOf(item.getQuantity()));

        // Place Order Button
        holder.placeOrderButton.setOnClickListener(v -> {
            // Start Place Order flow
            showAddressDialog(item, position);
        });

        // Cancel Order Button
        holder.cancelOrderButton.setOnClickListener(v -> {
            cartItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
            Toast.makeText(context, "Removed " + item.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productTotalPrice, productQuantity;
        Button placeOrderButton, cancelOrderButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productQuantity = itemView.findViewById(R.id.text_view_quantity);
            productImage = itemView.findViewById(R.id.image_view_product);
            productName = itemView.findViewById(R.id.text_view_product_name);
            productTotalPrice = itemView.findViewById(R.id.text_view_total_price);
            placeOrderButton = itemView.findViewById(R.id.button_place_order);
            cancelOrderButton = itemView.findViewById(R.id.button_cancel_order);
        }
    }

    // Show address and PIN code dialog
    private void showAddressDialog(Cart_Item item, int position) {
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
                        showPaymentDialog(item, position);
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    // Show payment method dialog
    private void showPaymentDialog(Cart_Item item, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select Payment Method")
                .setItems(new CharSequence[]{"Cash on Delivery", "GPay", "PhonePe"}, (dialog, which) -> {
                    switch (which) {
                        case 0: // Cash on Delivery
                            placeOrder(item, position, "Cash on Delivery");
                            break;
                        case 1: // GPay
                            verifyUPI(item, position, "GPay");
                            break;
                        case 2: // PhonePe
                            verifyUPI(item, position, "PhonePe");
                            break;
                    }
                });

        builder.create().show();
    }

    // Verify UPI ID
    private void verifyUPI(Cart_Item item, int position, String method) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_upi_verification, null);
        builder.setView(dialogView);

        EditText upiEditText = dialogView.findViewById(R.id.edit_text_upi);

        builder.setTitle("Verify UPI ID")
                .setPositiveButton("Verify", (dialog, which) -> {
                    String upiId = upiEditText.getText().toString().trim();
                    if (upiId.isEmpty()) {
                        Toast.makeText(context, "UPI ID cannot be empty", Toast.LENGTH_SHORT).show();
                    } else {
                        if (upiId.equals("validupi@upi")) {
                            placeOrder(item, position, method);
                        } else {
                            Toast.makeText(context, "Invalid UPI ID", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    // Place order
    private void placeOrder(Cart_Item item, int position, String method) {
        Toast.makeText(context, "Order placed for " + item.getName() + " using " + method, Toast.LENGTH_SHORT).show();
        cartItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, cartItems.size());
        OrderManager.getInstance().addOrder(item);
    }
}

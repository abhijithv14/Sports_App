package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

    private final Context context;
    private final List<Cart_Item> orders;

    public OrdersAdapter(Context context, List<Cart_Item> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_row, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Cart_Item order = orders.get(position);

        holder.productImage.setImageResource(order.getImageResId());
        holder.productName.setText(order.getName());
        holder.productPrice.setText(order.getPrice());
        holder.productQuantity.setText(String.valueOf(order.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice, productQuantity;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.image_view_product);
            productName = itemView.findViewById(R.id.text_view_product_name);
            productPrice = itemView.findViewById(R.id.text_view_product_price);
            productQuantity = itemView.findViewById(R.id.text_view_product_quantity);
        }
    }
}

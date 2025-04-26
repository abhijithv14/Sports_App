package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private List<Item> items;

    public GridAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview, parent, false);
        }

        // Bind views
        ImageView imageView = convertView.findViewById(R.id.itemImage);
        TextView nameView = convertView.findViewById(R.id.itemName);
        TextView priceView = convertView.findViewById(R.id.itemPrice);

        // Populate data
        Item item = items.get(position);
        imageView.setImageResource(item.getImageResId());
        nameView.setText(item.getName());
        priceView.setText("₹" + item.getPrice());

        // Set onClick listener
        convertView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ItemDetailsActivity.class);
            intent.putExtra("imageResId", item.getImageResId());
            intent.putExtra("name", item.getName());
            intent.putExtra("price", item.getPrice());
            context.startActivity(intent);
        });

        return convertView;
    }
}

package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.models.product;
import com.example.view_p2.R;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<product> productList;

    public ProductAdapter(Context context, List<product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList != null ? productList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product_spinner, parent, false);
        }

        product product = productList.get(position);

        ImageView imgProduct = convertView.findViewById(R.id.imgProduct);
        TextView txtProductName = convertView.findViewById(R.id.txtProductName);
        TextView txtProductPrice = convertView.findViewById(R.id.txtProductPrice);

        txtProductName.setText(product.getProductName());
        txtProductPrice.setText(String.format("%.0f đ", product.getProductPrice()));
        // You can set different images based on the product if needed

        return convertView;
    }
}

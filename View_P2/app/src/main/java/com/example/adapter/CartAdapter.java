package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.models.CartItemShopee;
import com.example.models.beer;
import com.example.view_p2.MainActivity4;
import com.example.view_p2.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends BaseAdapter {
    Activity activity; // màn hình hiển thị listview
    int item_layout; // thành phần giao diện
    List<CartItemShopee> cartItems; // data

    public CartAdapter(Activity activity, int item_layout, List<CartItemShopee> cartItems) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.cartItems = cartItems;
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int i) {
        return cartItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        CartItemShopee item = cartItems.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_shopee_item, null);
            holder = new ViewHolder();
            holder.imgProduct      = view.findViewById(R.id.imgProduct);
            holder.txtName         = view.findViewById(R.id.txtName);
            holder.txtVariant      = view.findViewById(R.id.txtVariant);
            holder.txtPrice        = view.findViewById(R.id.txtPrice);
            holder.txtOriginalPrice = view.findViewById(R.id.txtOriginalPrice);
            holder.txtQuantity     = view.findViewById(R.id.txtQuantity);
            holder.btnDecrease     = view.findViewById(R.id.btnDecrease);
            holder.btnIncrease     = view.findViewById(R.id.btnIncrease);
            holder.btnDelete       = view.findViewById(R.id.btnDelete);
            bindData(holder, item, i);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
            bindData(holder, item, i);
        }
        return view;
    }
    private void bindData(ViewHolder holder, CartItemShopee item, int position) {
        // Ảnh sản phẩm
        holder.imgProduct.setImageResource(item.getImageResId());

        // Tên sản phẩm
        holder.txtName.setText(item.getProductName());

        // Biến thể (màu, size)
        holder.txtVariant.setText(item.getVariant());

        // Giá
        holder.txtPrice.setText(formatPrice(item.getPrice()));

        // Giá gốc (gạch ngang nếu đang sale)
        if (item.isOnSale()) {
            holder.txtOriginalPrice.setVisibility(View.VISIBLE);
            holder.txtOriginalPrice.setText(formatPrice(item.getOriginalPrice()));
            holder.txtOriginalPrice.setPaintFlags(
                    holder.txtOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
            );
        } else {
            holder.txtOriginalPrice.setVisibility(View.GONE);
        }

        // Số lượng
        holder.txtQuantity.setText(String.valueOf(item.getQuantity()));


        // Nút giảm số lượng
        holder.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                    holder.txtQuantity.setText(String.valueOf(item.getQuantity()));
                }
            }
        });

        // Nút tăng số lượng
        holder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setQuantity(item.getQuantity() + 1);
                holder.txtQuantity.setText(String.valueOf(item.getQuantity()));
            }
        });

        // Nút xóa sản phẩm
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItems.remove(position);
                notifyDataSetChanged();
            }
        });
    }
    private String formatPrice(double price) {
        NumberFormat format = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
        return "₫" + format.format((long) price);
    }
    public static class ViewHolder{
        ImageView imgProduct;
        TextView txtName;
        TextView txtVariant;
        TextView txtPrice;
        TextView txtOriginalPrice;
        TextView txtQuantity;
        ImageButton btnDecrease;
        ImageButton btnIncrease;
        ImageButton btnDelete;

    }
}

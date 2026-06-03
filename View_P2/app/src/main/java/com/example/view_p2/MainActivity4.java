package com.example.view_p2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adapter.CartAdapter;
import com.example.models.CartItemShopee;
import com.example.view_p2.databinding.ActivityMain3Binding;
import com.example.view_p2.databinding.ActivityMain4Binding;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    ActivityMain4Binding binding;
    CartAdapter cartAdapter;
    ArrayList<CartItemShopee> cartItemShopeeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadData();
        connectEvents();
    }
    private void connectEvents(){}
    private void loadData(){
        cartItemShopeeArrayList = new ArrayList<>();
        cartItemShopeeArrayList.add(new CartItemShopee(
                1,
                "Áo thun nam cao cấp Cotton 100% form rộng Unisex nhiều màu",
                "Màu: Trắng | Size: L",
                189000, 189000,   // không sale
                1,
                R.drawable.aothun   // thay bằng ảnh sản phẩm thật
        ));
        cartItemShopeeArrayList.add(new CartItemShopee(
                2,
                "Giày sneaker nam nữ đế cao su chống trơn trượt phong cách Hàn Quốc",
                "Màu: Xanh | Size: 42",
                350000, 450000,   // có sale
                2,
                R.drawable.sneaker
        ));
        cartItemShopeeArrayList.add(new CartItemShopee(
                3,
                "Quần jean nữ ống rộng lưng cao co giãn tốt chất vải dày dặn",
                "Màu: Xanh đậm | Size: M",
                299000, 399000,   // có sale
                1,
                R.drawable.jean
        ));
        cartAdapter = new CartAdapter(MainActivity4.this, R.layout.item_shopee_item, cartItemShopeeArrayList);
        binding.lvCarts.setAdapter(cartAdapter);
    }

}
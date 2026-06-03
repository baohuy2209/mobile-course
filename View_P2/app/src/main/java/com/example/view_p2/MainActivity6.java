package com.example.view_p2;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapter.ProductAdapter;
import com.example.models.product;
import com.example.view_p2.databinding.ActivityMain6Binding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity6 extends AppCompatActivity {
    ActivityMain6Binding binding;
    Spinner spnProducts;
    ProductAdapter adapter;
    List<product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain6Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        spnProducts = findViewById(R.id.spnProducts);

        // Tạo dữ liệu mẫu
        productList = new ArrayList<>();
        productList.add(new product("iPhone 15 Pro Max", 30000000.0));
        productList.add(new product("Samsung Galaxy S24 Ultra", 28000000.0));
        productList.add(new product("Google Pixel 8 Pro", 22000000.0));
        productList.add(new product("MacBook Pro M3", 45000000.0));

        // Thiết lập adapter
        adapter = new ProductAdapter(this, productList);
        spnProducts.setAdapter(adapter);
    }
}

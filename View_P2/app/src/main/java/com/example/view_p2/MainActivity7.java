package com.example.view_p2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.view_p2.databinding.ActivityMain7Binding;

import java.util.Arrays;
import java.util.List;

public class MainActivity7 extends AppCompatActivity {

    ActivityMain7Binding binding;

    Spinner spnProvince1, spnProvince2, spnProvince3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain7Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<String> provinces = Arrays.asList(
                "Hà Nội", "Thái Nguyên", "Lạng Sơn", "Lào Cai", 
                "Bắc Cạn", "Huế", "Nha Trang", "Phú Yên", 
                "Lâm Đồng", "Tp Hồ Chí Minh", "Cần Thơ", "Long An", "Cà Mau"
        );

        // --- Style 1: Checkmark (Giống hình bên trái) ---
        spnProvince1 = findViewById(R.id.spnProvince1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provinces);
        // Sử dụng layout có sẵn hỗ trợ checkmark
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spnProvince1.setAdapter(adapter1);

        // --- Style 2: Radio Button (Giống hình giữa) ---
        spnProvince2 = findViewById(R.id.spnProvince2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provinces);
        // simple_spinner_dropdown_item thường hiển thị radio button ở các bản Android mới
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProvince2.setAdapter(adapter2);

        // --- Style 3: Single Choice (Giống hình bên phải) ---
        spnProvince3 = findViewById(R.id.spnProvince3);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provinces);
        adapter3.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnProvince3.setAdapter(adapter3);
    }
}

package com.example.act_int;

import android.content.Intent;
import android.hardware.display.DeviceProductInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.act_int.databinding.ActivitySubact1Binding;
import com.example.model.Product;

public class subact1 extends AppCompatActivity {
    ActivitySubact1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySubact1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getData();
    }
    private void getData(){
        Intent intent = getIntent();
//        // Nhận dữ liệu
//        int numb = intent.getIntExtra("numb", 0);
//        float grades = intent.getFloatExtra("grades", 0.0f);
//        String name = intent.getStringExtra("name");
//        boolean checked = intent.getBooleanExtra("checked", false);
//        Product p = (Product) intent.getSerializableExtra("product");
        Bundle bundle = intent.getBundleExtra("myBundle");
        assert bundle != null;
        int numb = bundle.getInt(Utils.NUMB);
        float grades = bundle.getFloat(Utils.GRADES);
        String name =  bundle.getString(Utils.NAME);
        Boolean checked = bundle.getBoolean(Utils.CHECKED);
        Product p = (Product) bundle.getSerializable("product");
        binding.txtContent.setText("");
        binding.txtContent.setText("Numb :" + numb + ", " + "Grades :" + grades + ", " + "Name: " + name + ", " + "Checked: " + checked + "\n");
        assert p != null;
        binding.txtContent.append("Product: "+p.getpCode()+", "+p.getpName()+", "+p.getpPrice());

    }
}
package com.mobile_course.sqllite_ex1;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mobile_course.models.Product;
import com.mobile_course.sqllite_ex1.databinding.ActivityEditBinding;

public class EditActivity extends AppCompatActivity {
    ActivityEditBinding binding;
    Intent intent;
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getData();
        events();
    }

    private void events() {
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(MainActivity.COL_Name, binding.edtPName.getText().toString());
                values.put(MainActivity.COL_Price, Float.parseFloat(binding.edtPPrice.getText().toString()));
                int flag = MainActivity.db.update(MainActivity.TBL_NAME, values, MainActivity.COL_Id+"=?", new String[]{String.valueOf(product.getProductCode())});
                if(flag > 0){
                    Toast.makeText(EditActivity.this, "Update product successful!",
                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(EditActivity.this, "Update product fail!",Toast.LENGTH_LONG).show();
                }
//Cập nhật thất bại
            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getData() {
        intent = getIntent();
        product = (Product) intent.getSerializableExtra("product");
        assert product != null;
        binding.edtPName.setText(product.getProductName());
        binding.edtPPrice.setText(String.valueOf(product.getProductPrice()));
    }
}
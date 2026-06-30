package com.nguyenbaohuy.self_learning_2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenbaohuy.Database.Database;
import com.nguyenbaohuy.models.Dish;
import com.nguyenbaohuy.self_learning_2.databinding.ActivityEditBinding;
import com.nguyenbaohuy.utils.Utils;

public class EditActivity extends AppCompatActivity {
    ActivityEditBinding binding;
    SQLiteDatabase db;

    Intent intent;
    Dish dish;

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
        openDB();
        getData();
        events();
    }

    private void openDB() {
        db = Database.getDb(EditActivity.this);
    }

    private void events() {
        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String placeName = binding.edtPlaceName.getText().toString();
                String dishName = binding.edtDishName.getText().toString();
                String address = binding.edtAddress.getText().toString();

                if (placeName.isEmpty() || dishName.isEmpty() || address.isEmpty()) {
                    Toast.makeText(EditActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                ContentValues values = new ContentValues();
                values.put(Database.COL_NAME, placeName);
                values.put(Database.COL_DISHNAME, dishName);
                values.put(Database.COL_ADDRESS, address);
                int flag = db.update(Database.TABLE_DISH, values, Database.COL_ID + "=?", new String[]{String.valueOf(dish.getId())});
                if(flag > 0){
                    Toast.makeText(EditActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(EditActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
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
        dish = (Dish) intent.getSerializableExtra(Utils.PRODUCT);
        assert dish != null;
        binding.edtPlaceName.setText(dish.getPlaceName());
        binding.edtDishName.setText(dish.getDishName());
        binding.edtAddress.setText(dish.getAddress());
    }
}
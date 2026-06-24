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
import com.mobile_course.sqllite_ex1.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        connectEvents();
    }


    private void connectEvents() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pName = binding.txtPName.getText().toString();
                float pPrice = Float.parseFloat(binding.txtPPrice.getText().toString());
                ContentValues values = new ContentValues();
                values.put(MainActivity.COL_Name, pName);
                values.put(MainActivity.COL_Price, pPrice);
                long flag = MainActivity.db.insert(MainActivity.TBL_NAME, null, values);
                if(flag > 0) {
                    Toast.makeText(MainActivity2.this, "Add product successful!",
                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(MainActivity2.this, "Add product fail!",
                            Toast.LENGTH_LONG).show(); }
            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
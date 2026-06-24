package com.mobile_course.asset_shared_preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mobile_course.asset_shared_preferences.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        events();
    }

    private void events() {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("numb", 89);
                editor.putFloat("grade", 9.8f);
                editor.putBoolean("checked", true);
                editor.putString("name", "John");
                editor.apply();
            }
        });
        binding.btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
                int numb = preferences.getInt("numb", 0);
                float grade = preferences.getFloat("grade", 0);
                boolean checked = preferences.getBoolean("checked", false);
                String name = preferences.getString("name", "");

                binding.txtData.setText("numb: " + numb + "\n" + "grade: " + grade + "\n" + "checked: " + checked + "\n" + "name: " + name);
            }
        });
    }
}
package com.example.view_p2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.view_p2.databinding.ActivityMain8Binding;

public class MainActivity8 extends AppCompatActivity {
    ActivityMain8Binding binding;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain8Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadData();
    }
    private void loadData(){
        AutoCompleteTextView completeTextViewProvince = binding.autoCompleteProvince;
        adapter = new ArrayAdapter<>(MainActivity8.this,
                android.R.layout.simple_list_item_1);
        adapter.addAll(getResources().getStringArray(R.array.auto_complete_text_view));
        completeTextViewProvince.setAdapter(adapter);
    }
}
package com.example.act_int;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.act_int.databinding.ActivityMain4Binding;

public class MainActivity4 extends AppCompatActivity {
    ActivityMain4Binding binding;
    int numb;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getData();
        connectEvents();
    }
    private void getData(){
        intent = getIntent();
        numb = intent.getIntExtra("numb", 0);
        binding.txtNumber.setText(String.valueOf(numb));
    }
    private void connectEvents(){
        binding.btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double res = numb * numb;
                intent.putExtra("result", res);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
package com.example.myfirstapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.databinding.ActivityFilmScreenBinding;

public class film_screen extends AppCompatActivity {
    ActivityFilmScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityFilmScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        linkEvents();
    }
    private void linkEvents(){
        binding.btnConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = "Bạn chọn: ";
                if(binding.chkfilm.isChecked()){
                    s += "Film +, ";
                }
                if(binding.chkCLipTv.isChecked()){
                    s += "Clip TV, ";
                }
                if(binding.chkfptplay.isChecked()){
                    s += "Clip TV, ";
                }
                s = s.substring(0, s.length() - 2);
                binding.txtAns.setText(s);
            }
        });
    }
}
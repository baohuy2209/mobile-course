package com.example.myfirstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfirstapp.databinding.ActivityFilmScreenBinding;
import com.example.myfirstapp.databinding.ActivityRadioExampleBinding;

public class radio_example extends AppCompatActivity {
    ActivityRadioExampleBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRadioExampleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        linkEvents();
    }
    private void linkEvents(){
        binding.btnConfirm1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String s = "Bạn đã đánh giá: ";
//                if(binding.radioBtnTot.isChecked()){
//                    s += binding.radioBtnTot.getText().toString();
//                } else if (binding.radioBtnTuongDoiTot.isChecked()){
//                    s += binding.radioBtnTuongDoiTot.getText().toString();
//                }else if (binding.radioBtnRatTot.isChecked()){
//                    s += binding.radioBtnRatTot.getText().toString();
//                }else if (binding.radioBtnTuyetVoi.isChecked()){
//                    s += binding.radioBtnTuyetVoi.getText().toString();
//                }
                int checkedRadioButtonId = binding.radioBinhChon.getCheckedRadioButtonId();
                if(checkedRadioButtonId > 0){
                    RadioButton checkRadioButton = findViewById(checkedRadioButtonId);
                    s += checkRadioButton.getText().toString();
                    binding.txtAns.setText(s);
                }
            }
        });
    }
}
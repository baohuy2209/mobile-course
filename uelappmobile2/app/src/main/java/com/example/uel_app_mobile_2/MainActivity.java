package com.example.uel_app_mobile_2;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uel_app_mobile_2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        connectEvents();
    }
    private void connectEvents(){
        binding.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.imgBtn.getTag() == "ic_1" || binding.imgBtn.getTag() == null){
                    binding.imgBtn.setImageResource(R.drawable.ic_2);
                    binding.imgBtn.setTag("ic_2");
                }else{
                    binding.imgBtn.setImageResource(R.drawable.ic_1);
                    binding.imgBtn.setTag("ic_1");
                }
            }
        });
        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
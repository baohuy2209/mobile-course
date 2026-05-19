package com.example.uel_app_example_3;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.example.uel_app_example_3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
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
        binding.btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Anonymos", Toast.LENGTH_SHORT).show();
            }
        });
        // Variable as Listener
        binding.btnVar1.setOnClickListener(myClickListener);
        binding.btnVar2.setOnClickListener(myClickListener);
        // Activity as Listener
        binding.btnImlement1.setOnClickListener(this);
        binding.btnImlement2.setOnClickListener(this);
        // Class as Listener
        MyClassListener myClassListener = new MyClassListener();
        binding.btnClass2.setOnClickListener(myClassListener);
        binding.btnClass1.setOnClickListener(myClassListener);
    }
    // OnClick
    public void project(View view) {
        Toast.makeText(this, "Wifi disconnect", Toast.LENGTH_SHORT).show();
    }
    // Variable as Listener
    View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btnVar1){
                Toast.makeText(MainActivity.this, "var 1", Toast.LENGTH_SHORT).show();
            }else if (view.equals(binding.btnVar2)){
                Toast.makeText(MainActivity.this, "var 2", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onClick(View view) {
        if(view.equals(binding.btnImlement1)){
            Toast.makeText(MainActivity.this, "implement 1", Toast.LENGTH_SHORT).show();
        }else if(view.equals(binding.btnImlement2)){
            Toast.makeText(MainActivity.this, "implement 2", Toast.LENGTH_LONG).show();
        }
    }

    // Class as Listener
    public class MyClassListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if(view.equals(binding.btnClass1)){
                Toast.makeText(MainActivity.this, "class 1", Toast.LENGTH_SHORT).show();
            }else if(view.equals(binding.btnClass2)){
                Toast.makeText(MainActivity.this, "class 2", Toast.LENGTH_LONG).show();
            }
        }
    }
}
package com.example.view_p2;

import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.view_p2.databinding.ActivityMain10Binding;

public class MainActivity10 extends AppCompatActivity {
    ActivityMain10Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain10Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupTabHost();
        connectEvents();
    }
    private void setupTabHost(){
        TabHost tabHost = binding.tabHost;
        tabHost.setup();
        //Tạo tab 1
        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("Foods");
        tabHost.addTab(tab1);
//Tạo tab 2
        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Drinks");
        tabHost.addTab(tab2);
    }
    private void connectEvents(){
        binding.tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                Toast.makeText(MainActivity10.this,
                        "Tab " + s + " selected, index: " + binding.tabHost.getCurrentTab(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
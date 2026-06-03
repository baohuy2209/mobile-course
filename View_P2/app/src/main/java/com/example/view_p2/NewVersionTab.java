package com.example.view_p2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapter.TabAdapter;
import com.example.view_p2.databinding.ActivityNewVersionTabBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class NewVersionTab extends AppCompatActivity {
    ActivityNewVersionTabBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityNewVersionTabBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupTabs();
    }
    private void setupTabs() {

        binding.viewPager.setAdapter(
                new TabAdapter(this)
        );

        new TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,

                (tab, position) -> {

                    switch (position) {

                        case 0:
                            tab.setText("Foods");
                            break;

                        case 1:
                            tab.setText("Drinks");
                            break;

                    }

                }

        ).attach();
    }
}
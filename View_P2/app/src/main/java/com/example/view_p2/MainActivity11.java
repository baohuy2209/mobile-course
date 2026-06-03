package com.example.view_p2;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.view_p2.databinding.ActivityMain11Binding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity11 extends AppCompatActivity {
    ActivityMain11Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain11Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TabLayout tabLayout = binding.tabLayout;
        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {

                    @Override
                    public void onTabSelected(
                            TabLayout.Tab tab
                    ) {

                        binding.layoutFood
                                .setVisibility(
                                        View.GONE
                                );

                        binding.layoutDrink
                                .setVisibility(
                                        View.GONE
                                );

                        binding.layoutDessert
                                .setVisibility(
                                        View.GONE
                                );

                        switch (
                                tab.getPosition()
                        ) {

                            case 0:

                                binding.layoutFood
                                        .setVisibility(
                                                View.VISIBLE
                                        );

                                break;

                            case 1:

                                binding.layoutDrink
                                        .setVisibility(
                                                View.VISIBLE
                                        );

                                break;

                            case 2:

                                binding.layoutDessert
                                        .setVisibility(
                                                View.VISIBLE
                                        );

                                break;
                        }

                    }

                    @Override
                    public void onTabUnselected(
                            TabLayout.Tab tab
                    ) {
                    }

                    @Override
                    public void onTabReselected(
                            TabLayout.Tab tab
                    ) {
                    }

                }
        );
    }
}
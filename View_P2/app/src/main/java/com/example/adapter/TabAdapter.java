package com.example.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.view_p2.DrinkFragment;
import com.example.view_p2.FoodFragment;

public class TabAdapter
        extends FragmentStateAdapter {

    public TabAdapter(
            FragmentActivity activity
    ) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(
            int position
    ) {

        if (position == 0) {
            return new FoodFragment();
        }

        return new DrinkFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
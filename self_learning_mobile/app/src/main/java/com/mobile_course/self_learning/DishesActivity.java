package com.mobile_course.self_learning;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mobile_course.Utils;
import com.mobile_course.adapters.DishAdapter;
import com.mobile_course.models.Dish;
import com.mobile_course.self_learning.databinding.ActivityDishesBinding;

import java.util.ArrayList;

public class DishesActivity extends AppCompatActivity {
    ActivityDishesBinding binding;

    DishAdapter dishAdapter;
    ArrayList<Dish> dishes;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDishesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        loadData();
        connectEvents();
    }

    private void connectEvents() {
        binding.lvDishes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dish item = dishes.get(i);
                Dialog dialog = new Dialog(DishesActivity.this);
                dialog.setContentView(R.layout.dialog_dish);
                dialog.setCanceledOnTouchOutside(true);
                ImageView txtDetailDish = dialog.findViewById(R.id.imvDetailDish);

                int resId = getResources().getIdentifier(
                        item.getPhoto(),
                        "drawable",
                        getPackageName()
                );

                if (resId != 0) {
                    txtDetailDish.setImageResource(resId);
                } else {
                    txtDetailDish.setImageResource(R.drawable.ic_launcher_background);
                }
                TextView txtPlaceName = dialog.findViewById(R.id.txtPlaceName);
                txtPlaceName.setText(item.getPlaceName());
                TextView txtRatingValue = dialog.findViewById(R.id.txtRatingValue);
                txtRatingValue.setText(String.valueOf(item.getRatingValue()));
                TextView txtAddress = dialog.findViewById(R.id.txtAddress);
                txtAddress.setText(item.getAddress());
                if(dialog.getWindow() != null){
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().setGravity(Gravity.BOTTOM);
                }
                dialog.show();
            }
        });
    }

    private void loadData() {
        intent = getIntent();
        dishes = (ArrayList<Dish>) intent.getSerializableExtra(Utils.PRODUCT);
        String title = intent.getStringExtra(Utils.TITLE);
        binding.txtTitle.setText(title);
        dishAdapter = new DishAdapter(DishesActivity.this, R.layout.item_list, dishes);
        binding.lvDishes.setAdapter(dishAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
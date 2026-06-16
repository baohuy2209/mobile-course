package com.k234111333_nguyenbaohuy.test;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.k234111333_nguyenbaohuy.adapter.LunchBoxAdapter;
import com.k234111333_nguyenbaohuy.model.LunchBox;
import com.k234111333_nguyenbaohuy.test.databinding.ActivityNoodleBinding;

import java.util.ArrayList;

public class NoodleActivity extends AppCompatActivity {
    ActivityNoodleBinding binding;
    LunchBoxAdapter lunchBoxAdapter;
    ArrayList<LunchBox> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityNoodleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadData();
    }
    private void loadData(){
        listItems = new ArrayList<LunchBox>();
        listItems.add(new LunchBox("Cơm Tấm Đại Đồng", "Cơm Sườn", R.drawable.lunch_com_tam_dai_dong, 4.1, "100+", "38 Đường Số 17"));
        listItems.add(new LunchBox("Bula Bento - Cơm Trưa", "Cơm Chiên Cá Hồi", R.drawable.lunch_bula_bento, 4.5, "200+", "38 Đường Số 17"));
        lunchBoxAdapter = new LunchBoxAdapter(NoodleActivity.this, R.layout.item_list, listItems);
        binding.lstViewNoodle.setAdapter(lunchBoxAdapter);
    }
}
package com.k234111333_nguyenbaohuy.test;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.k234111333_nguyenbaohuy.adapter.LunchBoxAdapter;
import com.k234111333_nguyenbaohuy.model.LunchBox;
import com.k234111333_nguyenbaohuy.test.databinding.ActivityLunchboxBinding;

import java.util.ArrayList;

public class LunchboxActivity extends AppCompatActivity {
    ActivityLunchboxBinding binding;
    LunchBoxAdapter lunchBoxAdapter;
    ArrayList<LunchBox> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLunchboxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadData();
        connectEvent();
    }
    private void loadData(){
        listItems = new ArrayList<LunchBox>();
        listItems.add(new LunchBox("Cơm Tấm Đại Đồng", "Cơm Sườn", R.drawable.lunch_com_tam_dai_dong, 4.1, "100+", "38 Đường Số 17"));
        listItems.add(new LunchBox("Bula Bento - Cơm Trưa", "Cơm Chiên Cá Hồi", R.drawable.lunch_bula_bento, 4.5, "200+", "38 Đường Số 17"));
        lunchBoxAdapter = new LunchBoxAdapter(LunchboxActivity.this, R.layout.item_list, listItems);
        binding.lstViewLunchbox.setAdapter(lunchBoxAdapter);
    }

    private void connectEvent(){
        binding.lstViewLunchbox.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
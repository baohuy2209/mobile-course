package com.k234111333_nguyenbaohuy.test;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.k234111333_nguyenbaohuy.adapter.BannerAdapter;
import com.k234111333_nguyenbaohuy.adapter.ServiceAdapter;
import com.k234111333_nguyenbaohuy.test.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ServiceAdapter serviceAdapter;
    BannerAdapter bannerAdapter;
    ArrayList<Integer> listItems;
    ArrayList<Integer> listItemsBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadData();
        connectEvent();
    }
    private void loadData(){
        listItems = new ArrayList<>();
        listItems.add(R.drawable.lunch_box_icon);
        listItems.add(R.drawable.noodle_icon);
        listItems.add(R.drawable.milk_tea_icon);
        listItems.add(R.drawable.healthy_icon);
        listItems.add(R.drawable.korean_icon);
        listItems.add(R.drawable.snack_icon);
        // 2. Nạp dữ liệu cho adapter
        serviceAdapter = new ServiceAdapter(MainActivity.this, R.layout.item_service, listItems);

        // 3. Trỏ dữ liệu từ adapter tới listView
        binding.gvService.setAdapter(serviceAdapter);

        listItemsBanner = new ArrayList<>();
        listItemsBanner.add(R.drawable.chaosuon_promo);
        listItemsBanner.add(R.drawable.comebuy_promo);
        listItemsBanner.add(R.drawable.tocotoco_promo);
        listItemsBanner.add(R.drawable.share_tea_promo);
        bannerAdapter = new BannerAdapter(MainActivity.this, R.layout.item_banner, listItemsBanner);
        binding.gvBanner.setAdapter(bannerAdapter);
    }
    private void connectEvent(){
        binding.gvService.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
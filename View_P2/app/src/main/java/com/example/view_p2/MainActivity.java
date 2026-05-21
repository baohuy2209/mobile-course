package com.example.view_p2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.view_p2.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> listItems;
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

        // 1. Tạo nguồn dữ liệu
        String [] drinks = {"Corona", "Tiger", "Heineken", "Blanc 1664", "333"};
        String [] foods = getResources().getStringArray(R.array.Foods);
        listItems = new ArrayList<String>(Arrays.asList(foods));
        // 2. Nạp dữ liệu cho adapter
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, listItems);

        // 3. Trỏ dữ liệu từ adapter tới listView
        binding.listView.setAdapter(arrayAdapter);
    }

    private void connectEvent() {
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = arrayAdapter.getItem(i);
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            }
        });
        binding.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = arrayAdapter.getItem(i);
                listItems.remove(selectedItem);
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
package com.example.view_p2;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adapter.BeerAdapter2;
import com.example.models.beer;
import com.example.view_p2.databinding.ActivityMain5Binding;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {
    ActivityMain5Binding binding;
    BeerAdapter2 beerAdapter;
    ArrayList<beer> beers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        connectEvents();
        loadData();
    }
    private void loadData(){
        beers = new ArrayList<>();
        beers.add(new beer(R.drawable.heineken, "Heineken", 19000.0));
        beers.add(new beer(R.drawable.tiger, "Tiger", 20000.0));
        beers.add(new beer(R.drawable.saigon, "Saigon", 15000.0));
        beers.add(new beer(R.drawable.sapporo, "Sapporo", 19540.0));
        beers.add(new beer(R.drawable.hanoi, "HaNoi", 23450.0));
        beers.add(new beer(R.drawable.larue, "Larue", 18000.0));
        beerAdapter = new BeerAdapter2(MainActivity5.this, beers, R.layout.item_list_grid_view);
        binding.gvSanpham.setAdapter(beerAdapter);
    }
    private void connectEvents(){
        binding.gvSanpham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(MainActivity5.this);
                dialog.setContentView(R.layout.custom_dialog_beer);
                ImageView imvBeer = dialog.findViewById(R.id.imgBeerDialog);
                TextView textView = dialog.findViewById(R.id.txtNamePrice);
                Button btnBack = dialog.findViewById(R.id.btnBackBeer);
                dialog.setCanceledOnTouchOutside(true);
                imvBeer.setImageResource(beers.get(i).getBeerThumb());
                String info = beers.get(i).getBeerName() + "-" + beers.get(i).getBeerPrice();
                textView.setText(info);
                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                if (dialog.getWindow() != null) {
                    dialog.getWindow().setLayout(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    dialog.getWindow().setGravity(Gravity.TOP);
                }
                dialog.show();
            }
        });
    }
}
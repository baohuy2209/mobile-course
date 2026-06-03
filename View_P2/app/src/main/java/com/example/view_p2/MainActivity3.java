package com.example.view_p2;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapter.BeerAdapter;
import com.example.models.beer;
import com.example.view_p2.databinding.ActivityMain3Binding;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    ActivityMain3Binding binding;
    BeerAdapter beerAdapter;
    ArrayList<beer> beers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        registerForContextMenu(binding.lvBeer);
        loadData();
        connectEvents();
    }
    private void loadData() {
        // Load data
        beers = new ArrayList<>();
        beers.add(new beer(R.drawable.heineken, "Heineken", 19000.0));
        beers.add(new beer(R.drawable.tiger, "Tiger", 20000.0));
        beers.add(new beer(R.drawable.saigon, "Saigon", 15000.0));
        beers.add(new beer(R.drawable.sapporo, "Sapporo", 19540.0));
        beers.add(new beer(R.drawable.hanoi, "HaNoi", 23450.0));
        beers.add(new beer(R.drawable.larue, "Larue", 18000.0));
        beerAdapter = new BeerAdapter(MainActivity3.this, beers, R.layout.item_list);
        binding.lvBeer.setAdapter(beerAdapter);
    }
    private void connectEvents(){
        binding.lvBeer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(MainActivity3.this);
                dialog.setContentView(R.layout.custom_dialog_beer);
                ImageView imvBeer = dialog.findViewById(R.id.imgBeerDialog);
                TextView textView = dialog.findViewById(R.id.txtNamePrice);
                dialog.setCanceledOnTouchOutside(true);
                imvBeer.setImageResource(beers.get(i).getBeerThumb());
                textView.setText(beers.get(i).getBeerName() + "-" + beers.get(i).getBeerPrice() + "");
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
        binding.lvBeer.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(MainActivity3.this);
                dialog.setContentView(R.layout.custom_dialog_beer);
                ImageView imvBeer = dialog.findViewById(R.id.imgBeerDialog);
                TextView textView = dialog.findViewById(R.id.txtNamePrice);
                dialog.setCanceledOnTouchOutside(true);
                imvBeer.setImageResource(beers.get(i).getBeerThumb());
                textView.setText(beers.get(i).getBeerName() + "-" + beers.get(i).getBeerPrice() + "");
                if (dialog.getWindow() != null) {
                    dialog.getWindow().setLayout(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    dialog.getWindow().setGravity(Gravity.TOP);
                }
                dialog.show();
                beers.remove(i);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuEdit){
            Toast.makeText(this, "Edit...", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId() == R.id.menuDelete){
            Toast.makeText(this, "Deleting...", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}
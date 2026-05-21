package com.example.view_p2;

import android.annotation.SuppressLint;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.models.product;
import com.example.view_p2.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    ArrayAdapter<product> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initAdapter();

        connectEvents();
    }

    private void initAdapter() {
        arrayAdapter = new ArrayAdapter<product>(MainActivity2.this, android.R.layout.simple_list_item_1);
        binding.listViewPr.setAdapter(arrayAdapter);
    }

    private void connectEvents() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = binding.edtName.getText().toString();
                Double productPrice = Double.valueOf(binding.edtPrice.getText().toString());
                product p = new product(productName, productPrice);
                arrayAdapter.add(p);
                arrayAdapter.notifyDataSetChanged();
                // Set default
                binding.edtName.setText("");
                binding.edtName.requestFocus();

                binding.edtName.setFocusable(true);
            }
        });
        binding.listViewPr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                product selectedItem = arrayAdapter.getItem(i);
                String name = "";
                Double price = 0.0;
                if(selectedItem != null){
                    name = selectedItem.getProductName();
                    price = selectedItem.getProductPrice();
                }

                Dialog dialog = new Dialog(MainActivity2.this);
                dialog.setContentView(R.layout.custom_dialog);
                TextView productName = dialog.findViewById(R.id.txtProductName);
                TextView productPrice = dialog.findViewById(R.id.txtProductPrice);
                Button btnClose =  dialog.findViewById(R.id.buttonClose);

                productName.setText(name);
                productPrice.setText(String.valueOf(price));
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                if (dialog.getWindow() != null){
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setGravity(Gravity.TOP);
                }
                dialog.show();
            }
        });
    }

    // Menu


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return super.onContextItemSelected(item);
    }
}
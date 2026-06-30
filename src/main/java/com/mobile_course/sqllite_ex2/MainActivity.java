package com.mobile_course.sqllite_ex2;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mobile_course.adapter.ProductAdapter;
import com.mobile_course.models.Product;
import com.mobile_course.sqllite_ex2.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Database db;
    ProductAdapter productAdapter;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        openDB();
        events();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();

    }

    private void events() {
        binding.tabAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_add);
                EditText edtName, edtPrice;
                edtName = dialog.findViewById(R.id.edtName);
                edtPrice = dialog.findViewById(R.id.edtPrice);
                Button insert = dialog.findViewById(R.id.btnAdd);
                insert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // insert data
                        boolean status = db.query("INSERT INTO "+Database.TABLE_NAME+" VALUES (null, '"+edtName.getText().toString()+"', "+Double.valueOf(edtPrice.getText().toString())+")");;
                        if(status){
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            loadData();
                        }else{
                            Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                Button cancel = dialog.findViewById(R.id.btnAddCancel);
                cancel.setOnClickListener(new View.OnClickListener() {
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
                    dialog.getWindow().setGravity(Gravity.CENTER);
                }

                dialog.show();
            }
        });
    }

    private void loadData() {
        products = new ArrayList<>();
        Cursor c = db.selectQuery("SELECT * FROM "+Database.TABLE_NAME);

        while (c.moveToNext()){
            products.add(new Product(c.getInt(0), c.getString(1), c.getDouble(2)));
        }
        c.close();
        productAdapter = new ProductAdapter(MainActivity.this, R.layout.item_list, products);
        binding.listView.setAdapter(productAdapter);
    }

    private void openDB() {
        db = new Database(MainActivity.this);
        db.createSampleData();
    }
}
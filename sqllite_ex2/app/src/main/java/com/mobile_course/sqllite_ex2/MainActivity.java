package com.mobile_course.sqllite_ex2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnItemActionListener{
    ActivityMainBinding binding;
    Database db;
    ProductAdapter productAdapter;
    ArrayList<Product> products;
    private boolean isOpen = false;

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
        Animation openAnim = AnimationUtils.loadAnimation(this,R.anim.from_bottom);
        Animation closeAnim = AnimationUtils.loadAnimation(this,R.anim.to_bottom);

        Animation rotateOpen = AnimationUtils.loadAnimation(this,R.anim.rotate_open);
        Animation rotateClose = AnimationUtils.loadAnimation(this,R.anim.rotate_close);
//        binding.tabAdd.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Dialog dialog = new Dialog(MainActivity.this);
//                dialog.setContentView(R.layout.dialog_add);
//                EditText edtName, edtPrice;
//                edtName = dialog.findViewById(R.id.edtName);
//                edtPrice = dialog.findViewById(R.id.edtPrice);
//                Button insert = dialog.findViewById(R.id.btnAdd);
//                insert.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        // insert data
//                        boolean status = db.query("INSERT INTO "+Database.TABLE_NAME+" VALUES (null, '"+edtName.getText().toString()+"', "+Double.valueOf(edtPrice.getText().toString())+")");;
//                        if(status){
//                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
//                            dialog.dismiss();
//                            loadData();
//                        }else{
//                            Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//                Button cancel = dialog.findViewById(R.id.btnAddCancel);
//                cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//                if (dialog.getWindow() != null) {
//                    dialog.getWindow().setLayout(
//                            ViewGroup.LayoutParams.MATCH_PARENT,
//                            ViewGroup.LayoutParams.WRAP_CONTENT
//                    );
//                    dialog.getWindow().setGravity(Gravity.CENTER);
//                }
//
//                dialog.show();
//            }
//        });
        binding.fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen){

                    binding.fabMain.startAnimation(rotateClose);

                    binding.fabAddProduct.startAnimation(closeAnim);
                    binding.fabEditProduct.startAnimation(closeAnim);

                    binding.fabAddProduct.setVisibility(View.GONE);
                    binding.fabEditProduct.setVisibility(View.GONE);

                    binding.txtAddProduct.setVisibility(View.GONE);
                    binding.txtEditProduct.setVisibility(View.GONE);

                    isOpen = false;

                }else{

                    binding.fabMain.startAnimation(rotateOpen);

                    binding.fabAddProduct.setVisibility(View.VISIBLE);
                    binding.fabEditProduct.setVisibility(View.VISIBLE);

                    binding.txtAddProduct.setVisibility(View.VISIBLE);
                    binding.txtEditProduct.setVisibility(View.VISIBLE);

                    binding.fabAddProduct.startAnimation(openAnim);
                    binding.fabEditProduct.startAnimation(openAnim);

                    isOpen = true;
                }
            }
        });
        binding.fabEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.fabAddProduct.setOnClickListener(new View.OnClickListener() {
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
        productAdapter = new ProductAdapter(MainActivity.this, R.layout.item_list, products, MainActivity.this);
        binding.listView.setAdapter(productAdapter);
    }

    private void openDB() {
        db = new Database(MainActivity.this);
        db.createSampleData();
    }

    @Override
    public void onEditClick(Product product, int position) {
        showEditDialog(product, position);
    }

    private void showEditDialog(Product product, int position) {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_edit);
        dialog.setCanceledOnTouchOutside(true);
        EditText edtName, edtPrice;
        edtName = dialog.findViewById(R.id.edtName);
        edtPrice = dialog.findViewById(R.id.edtPrice);
        edtName.setText(product.getpName());
        edtPrice.setText(String.valueOf(product.getpPrice()));
        Button btnEdit = dialog.findViewById(R.id.btnEdit);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status = db.query("UPDATE "+Database.TABLE_NAME+" SET "+Database.COL_NAME+"='"+edtName.getText().toString()+"', "+Database.COL_PRICE+"="+Double.valueOf(edtPrice.getText().toString())+" WHERE "+Database.COL_ID+"="+product.getpCode());;
                if(status){
                    Toast.makeText(MainActivity.this, "Update SuccessSuccess", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    productAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        if(dialog.getWindow() != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
        }
        dialog.show();
    }

    @Override
    public void onDeleteClick(Product product, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc muốn xóa " + product.getpName() + "?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    boolean status = db.query("DELETE FROM " + Database.TABLE_NAME + " WHERE "+Database.COL_ID+"=" + product.getpCode());
                    if(status){
                        loadData();
                        productAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Update SuccessSuccess", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_LONG).show();
                    }
                    products.remove(position);
                    productAdapter.notifyDataSetChanged();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }
}
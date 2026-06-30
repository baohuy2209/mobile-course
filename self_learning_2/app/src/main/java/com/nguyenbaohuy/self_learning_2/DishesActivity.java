package com.nguyenbaohuy.self_learning_2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

import com.nguyenbaohuy.Database.Database;
import com.nguyenbaohuy.adapters.DishAdapter;
import com.nguyenbaohuy.models.Dish;
import com.nguyenbaohuy.self_learning_2.databinding.ActivityDishesBinding;
import com.nguyenbaohuy.utils.Utils;

import java.util.ArrayList;

public class DishesActivity extends AppCompatActivity {
    ActivityDishesBinding binding;

    DishAdapter dishAdapter;
    ArrayList<Dish> dishes;
    Intent intent;
    Dish dish;
    SQLiteDatabase db;
    private boolean isOpen = false;

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
        openDB();
        loadData();
        connectEvents();
        registerForContextMenu(binding.lvDishes);
    }

    private void openDB() {
        db = Database.getDb(DishesActivity.this);
    }

    private void connectEvents() {
        Animation openAnim = AnimationUtils.loadAnimation(this,R.anim.from_bottom);
        Animation closeAnim = AnimationUtils.loadAnimation(this,R.anim.to_bottom);

        Animation rotateOpen = AnimationUtils.loadAnimation(this,R.anim.rotate_open);
        Animation rotateClose = AnimationUtils.loadAnimation(this,R.anim.rotate_close);
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
        binding.lvDishes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                dish = (Dish) adapterView.getItemAtPosition(i);
                return true;
            }
        });
        binding.fabMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isOpen){

                    binding.fabMain.startAnimation(rotateClose);
                    binding.fabAddProduct.startAnimation(closeAnim);
                    binding.fabAddProduct.setVisibility(View.GONE);
                    isOpen = false;

                }else{

                    binding.fabMain.startAnimation(rotateOpen);
                    binding.fabAddProduct.setVisibility(View.VISIBLE);
                    binding.fabAddProduct.startAnimation(openAnim);
                    isOpen = true;
                }
            }
        });
        binding.fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DishesActivity.this, AddActivity.class);
                startActivity(intent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnEdit){
            Intent intent = new Intent(DishesActivity.this, EditActivity.class);
            if(dish != null) {
                intent.putExtra(Utils.PRODUCT, dish);
                startActivity(intent);
            }else if(item.getItemId() == R.id.mnDelete){
                if(dish != null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(DishesActivity.this);
                    builder.setTitle("Xác nhận xóa");
                    builder.setIcon(android.R.drawable.ic_delete);
                    builder.setMessage("Bạn có muốn xóa sản phẩm : "+dish.getDishName());
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int flag = db.delete(Database.TABLE_DISH, Database.COL_ID + "=?", new String[]{String.valueOf(dish.getId())});
                            if(flag > 0){
                                Toast.makeText(DishesActivity.this, "Delete product successful!",
                                        Toast.LENGTH_LONG).show();
                                loadData();
                            }
                            else {
                                Toast.makeText(DishesActivity.this, "Delete product fail!",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    Dialog dialog = builder.create();
                    dialog.show();

                }
            }
        }
        return super.onContextItemSelected(item);
    }
}
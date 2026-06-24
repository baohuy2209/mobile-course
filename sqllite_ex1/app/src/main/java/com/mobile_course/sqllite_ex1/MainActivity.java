package com.mobile_course.sqllite_ex1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mobile_course.models.Product;
import com.mobile_course.sqllite_ex1.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    public static final String DB_NAME = "product_db.db";
    public static final String DB_FOLDER = "databases";
    public static final String TBL_NAME = "Product";
    public static final String COL_Id = "ProductId";
    public static final String COL_Name = "ProductName";
    public static final String COL_Price = "ProductPrice";

    public static SQLiteDatabase db;
    ArrayAdapter<Product> adapter;
//    ActivityResultLauncher<Intent> launcher;
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
        copyDB();
        openDB();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, getProducts());
        binding.lvProducts.setAdapter(adapter);
    }
    private ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<Product>();
        // Cách 1
        // Load data
        // Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME, null);
        // Cursor cursor = db.rawQuery("SELECT * FROM "+TBL_NAME + " WHERE "+COL_PRICE+" >? ", new String[]{"16000"});
        //Cursor cursor = db.rawQuery("SELECT * FROM "+TBL_NAME+" WHERE "+COL_Name+" LIKE ?", new String[]{"%H%"});

        // Cách 2
        Cursor cursor = db.query(TBL_NAME, null, null, null, null, null, null);
        //Cursor cursor = db.query(TBL_NAME, new String[]{COL_Id, COL_Name, COL_Price}, COL_Price + " >= ?", new String[]{"16000"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Product product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getFloat(2));
                products.add(product);
            }
            cursor.close();
        }
        return products;
    }
    private void openDB() {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
    }
    private void copyDB() {
        try{
            File dbFile = getDatabasePath(DB_NAME);
            if(!dbFile.exists()){
                if(copyDBFromAsset()){
                    Toast.makeText(MainActivity.this,
                            "Copy database successful!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,
                            "Copy database fail!", Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception e){
            Log.e("Error: ", e.toString());
        }
    }
    private boolean copyDBFromAsset() {
        String dbPath = getApplicationInfo().dataDir + "/" + DB_FOLDER + "/" +
                DB_NAME;
        try {
            InputStream inputStream = getAssets().open(DB_NAME);
            File f = new File(getApplicationInfo().dataDir + "/" + DB_FOLDER + "/");
            if(!f.exists()){
                f.mkdir();
            }
            OutputStream outputStream = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024]; int length;
            while((length=inputStream.read(buffer))>0){
                outputStream.write(buffer,0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuAdd){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
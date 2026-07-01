package com.nguyenbaohuy.ktgk2;

import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenbaohuy.adapters.ReceiptAdapter;
import com.nguyenbaohuy.database.Database;
import com.nguyenbaohuy.ktgk2.databinding.ActivityMainBinding;
import com.nguyenbaohuy.models.Receipt;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    Database db;
    ReceiptAdapter receiptAdapter;
    ArrayList<Receipt> listReceipt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        openDB();
        loadData();
        connectEvents();
    }
    private void connectEvents() {
    }

    private void openDB() {
        db = new Database(MainActivity.this);
        db.createSampleData();
    }

    private void loadData() {
        Cursor c = db.selectQuery("SELECT * FROM " + Database.TABLE_RECEIPT);
        listReceipt = new ArrayList<>();
        while (c.moveToNext()){
            listReceipt.add(new Receipt(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getBlob(6), c.getInt(7)));
        }
        c.close();
        receiptAdapter = new ReceiptAdapter(MainActivity.this, R.layout.item_list, listReceipt);
        binding.lvReceipt.setAdapter(receiptAdapter);
    }
}
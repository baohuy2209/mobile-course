package com.example.act_int;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import com.example.act_int.databinding.ActivityMainBinding;
import com.example.model.Product;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ActivityResultLauncher<Intent> launcher;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.i("MainActivity", "onCreate");
        connectEvents();
        // Nhan du lieu tu man hinh phu gui ve sau qua trinh xu ly
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_OK && result.getData() != null){
                // result.getData(): dai dien cho doi tuong 'intent' o man hinh phu
                double res = result.getData().getDoubleExtra("result", 0.0);
                binding.txtResult.setText(String.valueOf(res));
            }
        });
    }
    private void connectEvents(){
        binding.btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open Activity #2
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        binding.btnTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
        binding.btnTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, subact1.class);
//                intent.putExtra("numb", 99);
//                intent.putExtra("grades", 0.9f);
//                intent.putExtra("name", "John");
//                intent.putExtra("checked", true);
//                Product p = new Product(1, "Bia 333", 20000.0);
//                intent.putExtra("product", p);

                // Đính kèm dữ liệu
                Bundle bundle = new Bundle();
                bundle.putInt(Utils.NUMB, 99);
                bundle.putFloat(Utils.GRADES, 0.9f);
                bundle.putString(Utils.NAME, "John");
                bundle.putBoolean(Utils.CHECKED, true);

                Product p = new Product(2, "Sapporo", 100000.0);
                bundle.putSerializable(Utils.PRODUCT, p);
                intent.putExtra("myBundle",bundle);

                startActivity(intent);
            }
        });
        binding.btnSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity4.class);

                int numb = Integer.parseInt(binding.txtContent.getText().toString());
                intent.putExtra(Utils.NUMB, numb);
//                startActivity(intent);
                launcher.launch(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy");
    }
}
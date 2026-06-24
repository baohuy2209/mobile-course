package com.example.act_int;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.act_int.databinding.ActivityMain5Binding;

public class MainActivity5 extends AppCompatActivity {

    ActivityMain5Binding binding;
    ActivityResultLauncher<Intent> launcher;
    Boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_OK && result.getData() != null){
                if(flag){
                    Bitmap bm = (Bitmap) result.getData().getExtras().get("data");
                    binding.imgView.setImageBitmap(bm);
                }else{
                    Uri uri = result.getData().getData();
                    binding.imgView.setImageURI(uri);
                }
            }
        });
        events();
    }
    private void events(){
        binding.btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel:"+binding.txtPhone.getText().toString());
                intent.setData(uri);
                startActivity(intent);
            }
        });
        binding.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:"+binding.txtPhone.getText().toString());
                intent.setData(uri);
                startActivity(intent);
            }
        });
        binding.btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity5.this);
                // nap giao dien
                dialog.setContentView(R.layout.diaglog_activity5);
                LinearLayout btnPhoto =  dialog.findViewById(R.id.btnPhoto);
                LinearLayout btnCamera = dialog.findViewById(R.id.btnCamera);

                btnPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flag = false;
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        launcher.launch(intent);
                        dialog.dismiss();
                    }
                });
                btnCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flag = true;
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        launcher.launch(intent);
                        dialog.dismiss();
                    }
                });
                if (dialog.getWindow() != null){
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setGravity(Gravity.BOTTOM);
                }
                dialog.show();
            }
        });
    }
}
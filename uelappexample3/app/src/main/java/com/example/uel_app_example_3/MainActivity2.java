package com.example.uel_app_example_3;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uel_app_example_3.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        connectEvents();
    }
    private void connectEvents(){
        binding.btnShowToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Show Toast", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnShowToast1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setTitle("Xác nhận thoát");
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setMessage("Bạn có muốn thoát không");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                // Không cho tương tác bên ngoài
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        binding.btnShowCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity2.this);
                dialog.setContentView(R.layout.custom_dialog);
                ImageView imvOke = dialog.findViewById(R.id.btnOke);
                ImageView imvClose = dialog.findViewById(R.id.btnClose);
                dialog.setCanceledOnTouchOutside(false);
                imvOke.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                imvClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                if (dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().setLayout(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                }
                dialog.show();
            }
        });
        binding.btnShowCustomDialogUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity2.this, R.style.DialogSlideUp);
                dialog.setContentView(R.layout.bottom_sheet);
                LinearLayout gal = dialog.findViewById(R.id.gallery);
                gal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity2.this, "Open Gallery", Toast.LENGTH_SHORT).show();
                    }
                });
                LinearLayout cam = dialog.findViewById(R.id.camera);
                gal.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity2.this,"Open Camera", Toast.LENGTH_SHORT).show();
                    }
                });
                if (dialog.getWindow() != null){
                   dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                   dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                   dialog.getWindow().setGravity(Gravity.BOTTOM);
                }
                dialog.show();
            }
        });
        binding.btnShowCustomDialogDown.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity2.this, R.style.DialogSlideDown);
                dialog.setContentView(R.layout.bottom_sheet);
                LinearLayout gal = dialog.findViewById(R.id.gallery);
                gal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity2.this, "Open Gallery", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                LinearLayout cam = dialog.findViewById(R.id.camera);
                gal.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                        Toast.makeText(MainActivity2.this,"Open Camera", Toast.LENGTH_SHORT).show();
                    }
                });
                if (dialog.getWindow() != null){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setGravity(Gravity.TOP);
                }
                dialog.show();
            }
        });

    }
}
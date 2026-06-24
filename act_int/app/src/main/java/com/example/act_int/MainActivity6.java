package com.example.act_int;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.act_int.databinding.ActivityMain6Binding;

public class MainActivity6 extends AppCompatActivity {
    ActivityMain6Binding binding;
    ActivityResultLauncher<Intent> launcher;
    Boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain6Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_OK && result.getData() != null){
                // 1. Khởi tạo ImageView
                ImageView imageView = new ImageView(MainActivity6.this);

                // 2. Định dạng kích thước (Dùng DP để đẹp trên mọi màn hình)
                int sizeInDp = 100;
                float scale = getResources().getDisplayMetrics().density;
                int sizeInPx = (int) (sizeInDp * scale + 0.5f);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(sizeInPx, sizeInPx);
                params.setMargins(15, 10, 15, 10); // Khoảng cách giữa các ảnh
                imageView.setLayoutParams(params);

                // 3. Bo góc cho ImageView (Làm cho ảnh trông chuyên nghiệp hơn)
                imageView.setClipToOutline(true);
                imageView.setOutlineProvider(new ViewOutlineProvider() {
                    @Override
                    public void getOutline(View view, Outline outline) {
                        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30f);
                    }
                });

                // 4. Các thuộc tính hiển thị khác
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setElevation(10f); // Tạo độ nổi (shadow) cho ảnh

                // 5. Xử lý ảnh (Camera hoặc Thư viện)
                if(flag){
                    Bitmap bm = (Bitmap) result.getData().getExtras().get("data");
                    imageView.setImageBitmap(bm);
                }else{
                    Uri uri = result.getData().getData();
                    imageView.setImageURI(uri);
                }

                // 6. PHẦN XỬ LÝ THÊM: Nhấn giữ vào ảnh để xóa
                imageView.setOnLongClickListener(v -> {
                    binding.lnrImages.removeView(v);
                    Toast.makeText(MainActivity6.this, "Đã xóa ảnh", Toast.LENGTH_SHORT).show();
                    return true;
                });

                // Thêm vào layout
                binding.lnrImages.addView(imageView);
            }
        });
        events();
    }
    private void events(){
        binding.btnCaptureMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity6.this);
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
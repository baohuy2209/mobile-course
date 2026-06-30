package com.nguyenbaohuy.self_learning_2;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import com.nguyenbaohuy.Database.Database;
import com.nguyenbaohuy.self_learning_2.databinding.ActivityAddBinding;

public class AddActivity extends AppCompatActivity {
    ActivityAddBinding binding;
    SQLiteDatabase db;

    Intent intent;
    Boolean flag = false;
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_OK && result.getData() != null){
                        // 1. Khởi tạo ImageView
                        ImageView imageView = new ImageView(AddActivity.this);
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
                            Toast.makeText(AddActivity.this, "Đã xóa ảnh", Toast.LENGTH_SHORT).show();
                            return true;
                        });

                        // Thêm vào layout
                        binding.lnrImages.addView(imageView);
            }
        });
        openDB();
        events();
    }

    private void events() {
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(Database.COL_NAME, binding.edtPlaceName.getText().toString());
                values.put(Database.COL_DISHNAME, binding.edtDishName.getText().toString());
                values.put(Database.COL_ADDRESS, binding.edtAddress.getText().toString());

                long status = db.insert(Database.TABLE_DISH, null, values);
                if(status > 0) {
                    Toast.makeText(AddActivity.this, "Add product successful!",
                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(AddActivity.this, "Add product fail!",
                            Toast.LENGTH_LONG).show(); }
            }
        });
        binding.btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(AddActivity.this);
                dialog.setContentView(R.layout.dialog_image);
                LinearLayout btnPhoto = dialog.findViewById(R.id.btnPhoto);
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
                if(dialog.getWindow() != null){
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().setGravity(Gravity.BOTTOM);
                }
                dialog.show();
            }
        });
    }

    private void openDB() {
        db = Database.getDb(AddActivity.this);
    }
}
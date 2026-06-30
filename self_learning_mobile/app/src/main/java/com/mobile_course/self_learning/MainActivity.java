package com.mobile_course.self_learning;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.mobile_course.Database.Database;
import com.mobile_course.Utils;
import com.mobile_course.adapters.DishAdapter;
import com.mobile_course.models.Dish;
import com.mobile_course.self_learning.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Database db;

    ArrayList<Dish> listDishLunch;
    ArrayList<Dish> listDishNoodle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        openDB();
        loadData();
        connectEvents();

    }

    private void openDB() {
        db = new Database(MainActivity.this);
//        db.createSampleData();
    }

    private void loadData() {

//        listDishLunch.add(new Dish("Cơm Tấm Đại Đồng", "Cơm Sườn", R.drawable.lunch_com_tam_dai_dong, 4.1, "100+", "38 Đường Số 17, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
//        listDishLunch.add(new Dish("Bula Bento - Cơm Trưa", "Cơm Chiên Cá Hồi", R.drawable.lunch_bula_bento, 4.0, "399+", "22 Đoàn Kết, P. Bình Thọ, Q. Thủ Đức, Tp.HCM"));
//        listDishLunch.add(new Dish("Cơm Bình Dân Quang Ngân", "Cơm Gà Kho Xả Ớt", R.drawable.lunch_com_bd_quang_ngan, 4.0, "100+", "130 Linh Trung, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
//        listDishLunch.add(new Dish("Cơm Gà - Tô Vĩnh Diện", "Cơm Đùi Gà Chiên Nước Mắm", R.drawable.lunch_com_ga_to_vinh_dien, 4.5, "999+", "15 Tô Vĩnh Diện, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM"));
//        listDishLunch.add(new Dish("Cơm Niêu Phương Bắc", "Cơm Cá Cơm Rim Mặn", R.drawable.lunch_com_nieu_phuong_bac, 4.0, "100+", "87 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
//        listDishLunch.add(new Dish("Cơm Tấm Dì Ba", "Cơm Sườn Muối Ớt", R.drawable.lunch_com_tam_di_ba, 4.1, "999+", "197B Lê Văn Việt, P. Hiệp Phú, Q. 9, Tp.HCM"));
//        listDishLunch.add(new Dish("Cơm Tấm Phúc Lộc Thọ", "Cơm Sườn, Cơm Ba Rọi", R.drawable.lunch_com_tam_phuc_loc_tho, 4.5, "999+", "31-33 Lê Văn Việt, Q. Thủ Đức, Tp.HCM"));
//        listDishLunch.add(new Dish("Shilin - Cơm Gà", "Gà Rán", R.drawable.lunch_shilin_com_ga, 4.4, "999+", "22 Đoàn Kết, P. Bình Thọ, Q. Thủ Đức, Tp.HCM"));
//

//        listDishNoodle.add(new Dish("Bún Đậu Mẹt Tre - Hoàng Diệu 2", "Bún Đậu Tá Lả", R.drawable.noodle_bun_dau_mam_tom, 4.0, "499+", "177 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
//        listDishNoodle.add(new Dish("Bún Đậu A Chảnh - Tô Vĩnh Diện", "Bún Đậu Thập Cẩm", R.drawable.noodle_bun_dau_a_chanh, 4.5, "500+", "26 Tô Vĩnh Diện, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM"));
//        listDishNoodle.add(new Dish("Bún Đậu Mắm Tôm Tiểu Muội", "Bún Đậu Thập Cẩm", R.drawable.noodle_bun_dau_tieu_muoi, 4.0, "400+", "39 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
//        listDishNoodle.add(new Dish("Bún Thịt Nướng Dì 7", "Bún Thịt Nướng Đặc Biệt", R.drawable.noodle_bun_di_7, 4.0, "300+", "25/1 Đường Số 19, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM"));
//        listDishNoodle.add(new Dish("Bún Quậy Phú Quốc - Hoàng Diệu 2", "Bún Quậy Chả Tôm", R.drawable.noodle_bun_quay_phu_quoc, 4.6, "200+", "204A Hoàng Diệu 2, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM"));
//        listDishNoodle.add(new Dish("Dino - Bún Chả Nha Trang", "Bò Né", R.drawable.noodle_dino_bun_cha_nha_trang, 4.4, "400+", "89 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
//        listDishNoodle.add(new Dish("Thảo Ly Quán", "Mì Xào Bò", R.drawable.noodle_thao_ly_quan, 4.0, "100+", "29 Đường Số 16, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM"));
//        listDishNoodle.add(new Dish("Bún Đậu Bún Chả - Cô Hường", "Bún Đậu Thịt Luộc", R.drawable.noodle_bun_dau_bun_cha, 4.2, "200+", "61 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));

        Cursor c = db.selectQuery("SELECT * FROM " + Database.TABLE_DISH);
        listDishLunch = new ArrayList<>();
        listDishNoodle = new ArrayList<>();
        Log.d("DB_CHECK", "Query được " + c.getCount() + " rows");
        while (c.moveToNext()){
            if(c.getString(3).startsWith("lunch")){
                listDishLunch.add(new Dish(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getDouble(4),
                        c.getString(5),
                        c.getString(6)));
            } else if (c.getString(3).startsWith("noodle")){
                listDishNoodle.add(new Dish(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getDouble(4),
                        c.getString(5),
                        c.getString(6)));
            }
        }
        c.close();
    }


    private void connectEvents(){
        binding.imvLunchBoxIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DishesActivity.class);
                intent.putExtra(Utils.PRODUCT, listDishLunch);
                intent.putExtra(Utils.TITLE, "RICE");
                startActivity(intent);
            }
        });
        binding.imvNoodleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DishesActivity.class);
                intent.putExtra(Utils.PRODUCT, listDishNoodle);
                intent.putExtra(Utils.TITLE, "NOODLE");
                startActivity(intent);
            }
        });
    }
}
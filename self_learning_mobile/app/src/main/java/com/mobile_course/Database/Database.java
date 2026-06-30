package com.mobile_course.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DB_NAME = "dishes_db.sqlite";
    public static final int VERSION = 1;
    public static final String TABLE_DISH = "dishes";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "placeName";
    public static final String COL_DISHNAME = "dishName";
    public static final String COL_RATINGVALUE = "ratingValue";
    public static final String COL_RATINGCOUNT = "ratingCount";
    public static final String COL_ADDRESS = "address";
    public static final String COL_PHOTO = "photo";
    private static final String CREATE_TABLE_DISH = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT, " +
                    "%s REAL DEFAULT 0.0, " +
                    "%s TEXT, " +
                    "%s TEXT" +
                    ")",
            TABLE_DISH,
            COL_ID,
            COL_NAME,
            COL_DISHNAME,
            COL_PHOTO,
            COL_RATINGVALUE,
            COL_RATINGCOUNT,
            COL_ADDRESS
    );
    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_DISH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DISH);
        onCreate(sqLiteDatabase);
    }

    public Cursor selectQuery(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }
    public boolean query(String sql){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL(sql);
            return true;
        }catch (Exception e){
            Log.e("query", e.getMessage());
            return false;
        }
    }
    public int getNumOfRows(String tblName){
        Cursor c = selectQuery("SELECT * FROM "+tblName);
        int rows = c.getCount();
        c.close();
        return rows;
    }
    public void createSampleData(){
        int rows = getNumOfRows(TABLE_DISH);
        if (rows == 0){
            // ===== INSERT DATA: LUNCH_BOX =====
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Cơm Tấm Đại Đồng', 'Cơm Sườn', 'lunch_com_tam_dai_dong', 4.1, '100+', '38 Đường Số 17, P. Linh Trung, Q. Thủ Đức, Tp.HCM')");
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Bula Bento - Cơm Trưa', 'Cơm Chiên Cá Hồi', 'lunch_bula_bento', 4.0, '499+', '22 Đoàn Kết, P. Bình Thọ, Q. Thủ Đức, Tp.HCM')");
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Cơm Bình Dân Quang Ngân', 'Cơm Gà Kho Xả Ớt', 'lunch_com_bd_quang_ngan', 4.0, '100+', '130 Linh Trung, P. Linh Trung, Q. Thủ Đức, Tp.HCM')");
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Cơm Gà - Tô Vĩnh Diện', 'Cơm Đùi Gà Chiên Nước Mắm', 'lunch_com_ga_to_vinh_dien', 4.5, '999+', '15 Tô Vĩnh Diện, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM')");
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Cơm Niêu Phương Bắc', 'Cơm Cá Cơm Rim Mặn', 'lunch_com_nieu_phuong_bac', 4.0, '100+', '87 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM')");
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Cơm Tấm Dì Ba', 'Cơm Sườn Muối Ớt', 'lunch_com_tam_di_ba', 4.1, '999+', '197B Lê Văn Việt, P. Hiệp Phú, Q. 9, Tp.HCM')");
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Cơm Tấm Phúc Lộc Thọ', 'Cơm Sườn, Cơm Ba Rọi', 'lunch_com_tam_phuc_loc_tho', 4.5, '999+', '31-33 Lê Văn Việt, Q. Thủ Đức, Tp.HCM')");
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Shilin - Cơm Gà', 'Gà Rán', 'lunch_shilin_com_ga', 4.4, '999+', '22 Đoàn Kết, P. Bình Thọ, Q. Thủ Đức, Tp.HCM')");

// ===== INSERT DATA: NOODLE =====
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Bún Đậu Mẹt Tre - Hoàng Diệu 2', 'Bún Đậu Tá Lả', 'noodle_bun_dau_mam_tom', 4.0, '499+', '177 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM')");
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Bún Đậu A Chảnh - Tô Vĩnh Diện', 'Bún Đậu Thập Cẩm', 'noodle_bun_dau_a_chanh', 4.5, '500+', '26 Tô Vĩnh Diện, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM')");
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Bún Đậu Mắm Tôm Tiểu Muội', 'Bún Đậu Thập Cẩm', 'noodle_bun_dau_tieu_muoi', 4.0, '400+', '39 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM')");
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Bún Thịt Nướng Dì 7', 'Bún Thịt Nướng Đặc Biệt', 'noodle_bun_di_7', 4.0, '300+', '25/1 Đường Số 19, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM')");
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Bún Quậy Phú Quốc - Hoàng Diệu 2', 'Bún Quậy Chả Tôm', 'noodle_bun_quay_phu_quoc', 4.6, '200+', '204A Hoàng Diệu 2, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM')");
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Dino - Bún Chả Nha Trang', 'Bò Né', 'noodle_dino_bun_cha_nha_trang', 4.4, '400+', '89 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM')");
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Thảo Ly Quán', 'Mì Xào Bò', 'noodle_thao_ly_quan', 4.0, '100+', '29 Đường Số 16, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM')");
            query("INSERT INTO " + TABLE_DISH + " VALUES (null, 'Bún Đậu Bún Chả - Cô Hường', 'Bún Đậu Thịt Luộc', 'noodle_bun_dau_bun_cha', 4.2, '200+', '61 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM')");
        }
    }
}

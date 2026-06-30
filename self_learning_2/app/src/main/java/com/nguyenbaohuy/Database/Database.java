package com.nguyenbaohuy.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Database {
    public static final String DB_NAME = "dishes_db.sqlite";
    public static final String DB_FOLDER = "databases";
    public static final String TABLE_DISH = "dishes";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "placeName";
    public static final String COL_DISHNAME = "dishName";
    public static final String COL_RATINGVALUE = "ratingValue";
    public static final String COL_RATINGCOUNT = "ratingCount";
    public static final String COL_ADDRESS = "address";
    public static final String COL_PHOTO = "photo";

    public static SQLiteDatabase db;

    // Gọi hàm này ở bất kỳ đâu để lấy DB dùng chung
    public static SQLiteDatabase getDb(Context context) {
        if (db == null) {
            copyDB(context);
            db = context.getApplicationContext()
                    .openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        }
        return db;
    }

    private static void copyDB(Context context) {
        try {
            File dbFile = context.getDatabasePath(DB_NAME);
            if (dbFile.exists()) return;

            InputStream inputStream = context.getAssets().open(DB_NAME);
            File folder = new File(context.getApplicationInfo().dataDir + "/" + DB_FOLDER + "/");
            if (!folder.exists()) folder.mkdir();

            OutputStream outputStream = new FileOutputStream(folder.getPath() + "/" + DB_NAME);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            Log.e("DatabaseManager", "copyDB error: " + e);
        }
    }


}

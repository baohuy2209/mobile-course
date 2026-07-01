package com.nguyenbaohuy.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DB_NAME = "dishes_db.sqlite";
    public static final int VERSION = 1;

    public static final String TABLE_CATEGORY = "category";
    public static final String CATEGORY_ID = "id";

    public static final String CATEGORY_NAME = "categoryName";
    public static final String TABLE_RECEIPT = "receipt";
    public static final String RECEIPT_ID = "id";
    public static final String RECEIPT_NAME = "receiptName";
    public static final String RECEIPT_NAT = "receiptNat";
    public static final String RECEIPT_CL = "receiptCL";
    public static final String RECEIPT_TIME = "receiptTime";
    public static final String RECEIPT_LEVEL = "receiptLevel";

    public static final String RECEIPT_CATEGORY_ID = "receiptCategoryID";
    public static final String RECEIPT_IMAGE = "receiptImage";

    public static final String TABLE_FAVORITE = "favorite";
    public static final String FAVORITE_ID = "id";

    public static final String FAVORITE_DATE = "favoriteDate";
    public static final String FAVORITE_ID_RECEIPT = "favoriteReceiptId";

    private static final String CREATE_TABLE_CATEGORY = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT NOT NULL, " +
                    ")",
            TABLE_CATEGORY,
            CATEGORY_ID,
            CATEGORY_NAME
    );
    private static final String CREATE_TABLE_RECEIPT = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s INTEGER DEFAULT 0, " +
                    "%s TEXT NOT NULL, " +
                    "%s BLOB," +
                    "%s INTERGER NOT NULL"+
                    ")",
            TABLE_RECEIPT,
            RECEIPT_ID,
            RECEIPT_NAME,
            RECEIPT_NAT,
            RECEIPT_CL,
            RECEIPT_TIME,
            RECEIPT_LEVEL,
            RECEIPT_IMAGE,
            RECEIPT_CATEGORY_ID
    );
    private static final String CREATE_TABLE_FAVORITE = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s INTEGER NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    ")",
            TABLE_FAVORITE,
            FAVORITE_ID,
            FAVORITE_ID_RECEIPT,
            FAVORITE_DATE
    );
    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CATEGORY);
        sqLiteDatabase.execSQL(CREATE_TABLE_RECEIPT);
        sqLiteDatabase.execSQL(CREATE_TABLE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEIPT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE);
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
        int rows = getNumOfRows(TABLE_CATEGORY);
        if(rows == 0){
            query("INSERT INTO"+TABLE_CATEGORY+" VALUES (null,'Món nước')");
            query("INSERT INTO"+TABLE_CATEGORY+" VALUES (null,'Món khô')");
            query("INSERT INTO"+TABLE_CATEGORY+" VALUES (null,'Chè')");
            query("INSERT INTO"+TABLE_CATEGORY+" VALUES (null,'Nước giải khát')");
            query("INSERT INTO"+TABLE_CATEGORY+" VALUES (null,'Món mặn')");
        }
    }
}

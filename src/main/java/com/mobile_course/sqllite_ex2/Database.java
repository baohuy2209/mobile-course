package com.mobile_course.sqllite_ex2;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DB_NAME = "product_db2.sqlite";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "product";
    public static final String COL_ID = "ProductCode";
    public static final String COL_NAME = "ProductName";
    public static final String COL_PRICE = "ProductPrice";

    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+TABLE_NAME+"("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_NAME+" TEXT, "+COL_PRICE+" REAL)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    // SELECT
    public Cursor selectQuery(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }
    // INSERT UPDATE DELETE
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

    // Create sample data
    public void createSampleData(){
        int rows = getNumOfRows(TABLE_NAME);
        if(rows == 0){
            query("INSERT INTO "+TABLE_NAME+" VALUES (null, 'Tiger', '12000')");
            query("INSERT INTO "+TABLE_NAME+" VALUES (null, 'Hanoi', '23000')");
            query("INSERT INTO "+TABLE_NAME+" VALUES (null, 'Heineken', '19500')");
            query("INSERT INTO "+TABLE_NAME+" VALUES (null, 'Pepsi', '17600')");

        }
    }
}

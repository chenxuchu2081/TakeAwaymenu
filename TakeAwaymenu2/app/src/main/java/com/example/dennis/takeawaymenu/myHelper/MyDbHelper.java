package com.example.dennis.takeawaymenu.myHelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "takeawaymenu";
    public static int DB_VERSION =1;
    public static String TABLE_NAME = "foodmenu";

    //book table column names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "foodname";
    public static final String COLUMN_PRICE = "foodprice";
    public static final String COLUMN_IMAGE = "foodimage";


    public  MyDbHelper(Context context){
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_IMAGE + " BLOB, " +
                COLUMN_PRICE + " TEXT)";

        System.out.println(sql);

        db.execSQL( sql );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //if older table exit it will drop
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;

        System.out.println(sql);

        db.execSQL( sql );

        onCreate( db );

    }
}


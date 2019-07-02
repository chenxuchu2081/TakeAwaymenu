package com.example.dennis.takeawaymenu.myHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dennis.takeawaymenu.model.Food;

public class MyDbAdapter {
    private Context context;
    private SQLiteOpenHelper myDbHelper;
    public static String TABLE_NAME = "foodmenu";

    public  MyDbAdapter (Context context){
        this.context = context;

        this.myDbHelper = new MyDbHelper(this.context);
    }

    public void addFood(Food food){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("foodname",     food.getName());
        values.put("foodprice",   food.getPrice());

        db.insert(TABLE_NAME, null, values);

        db.close();
    }



    public Cursor getAllBooks(){

        SQLiteDatabase db = myDbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NAME,
                new String[ ] { "_id","foodname","foodprice","foodimage"},
                null,
                null,
                null,
                null,
                null,
                null);

        //display(cursor);
        return cursor;

    }


    public  void deleteAllFoods(){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();

        db.delete(
                TABLE_NAME,
                null,
                null);

        db.close();

    }

//    private void display(Cursor cursor) {
//
//        cursor.moveToFirst();
//
//        while (!cursor.isAfterLast())  {
//
//            int id = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String price = cursor.getString(2);
//
//            System.out.println("_id " + id + " name:  " +name+ " price:  " + price);
//
//            cursor.moveToNext();
//
//        }
//    }



}

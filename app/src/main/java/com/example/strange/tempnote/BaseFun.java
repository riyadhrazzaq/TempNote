package com.example.strange.tempnote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Random;

/**
 * Created by riyadh on 08-Jul-18.
 */

public class BaseFun extends SQLiteOpenHelper {

    Random rand = new Random();

    private static final String DATABASE_NAME = "db";
    private static final String TABLE_NAME = "tab";
    private static final String col_id = "id";        //0
    private static final String col_title = "title";    //1
    private static final String col_note = "note";     //2
    private static final String col_exp = "exp";    // 3 expires in 'exp' millsec


    BaseFun(Context c) {
        super(c, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {

        String s = "CREATE TABLE " + TABLE_NAME + "(" + col_id + " INT PRIMARY KEY," + col_title + " TEXT," + col_note +
                " TEXT," + col_exp + " TEXT)";
        mydb.execSQL(s);
        Log.e("dbstat", "database created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    // adding data to db

    public boolean addData(String title, String note, String exp) {

        int id = rand.nextInt(9999);
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_id, id);
        cv.put(col_title, title);
        cv.put(col_note, note);
        cv.put(col_exp, exp);
        long flag=  sqdb.insert(TABLE_NAME, null, cv);
        if(flag==-1) return false;
        else return true;
    }


    Cursor getAllByCursor() {
        SQLiteDatabase sq = this.getReadableDatabase();

        String q = "SELECT * FROM " + TABLE_NAME;

        Cursor c = sq.rawQuery(q, null);

        return c;

    }

    Cursor getById(int id) {
        SQLiteDatabase sq = this.getReadableDatabase();
        String q = "select * from " + TABLE_NAME + " where " + col_id + " =" + id;
        Cursor c = sq.rawQuery(q, null);
        return c;
    }

    void removeById(int id) {
        SQLiteDatabase sq = this.getWritableDatabase();
        String q = "delete from "+TABLE_NAME+" where "+col_id+" ="+id;
        sq.execSQL(q);

    }

    void update(String id, String title, String note){
        SQLiteDatabase sq = this.getWritableDatabase();
        Log.d("say","ok");
        ContentValues cv  = new ContentValues();
        cv.put(col_title,title);
        cv.put(col_note,note);
        sq.update( TABLE_NAME,cv,col_id+"="+id,null );
        Log.d("say","updated");



    }



}

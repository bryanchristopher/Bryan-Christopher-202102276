package com.bryanchristopher202102276.tugasakhir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "project2.db";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key, password TEXT)");
        sqLiteDatabase.execSQL("create table barang(kode TEXT primary key, nama TEXT, jenis TEXT, banyak TEXT, harga TEXT)");

    }

    public DBHelper(@Nullable Context context) {
        super(context,"project2.db",null,1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        sqLiteDatabase.execSQL("drop table if exists barang");

    }

    public Boolean insertData (String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username",username);
        values.put("password",password);
        long result = db.insert("users", null,values);
        if (result==1) return false;
        else
            return true;
    }

    public Boolean checkusername (String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;


    }

    public Boolean checkusernamepassword (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[] {username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;


    }


    public Boolean insertDataBarang (String nim,String nama, String jeniskelamin, String alamat, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("kode",nim);
        values.put("nama",nama);
        values.put("jenis", jeniskelamin);
        values.put("banyak", alamat);
        values.put("harga", email);
        long result = db.insert("barang", null,values);
        if (result==1) return false;
        else
            return true;
    }

    public Cursor tampilDataBarang(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery("select * from barang", null);
        return cursor;
    }

    public boolean hapusDatabarang (String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from barang where kode=?", new String[]{nim});
        if (cursor.getCount()>0){
            long result = db.delete("barang", "kode=?", new String[]{nim});
            if (result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }


    public Boolean editDataBarang (String nim,String nama, String jeniskelamin, String alamat, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nama",nama);
        values.put("jenis", jeniskelamin);
        values.put("banyak", alamat);
        values.put("harga", email);
        Cursor cursor = db.rawQuery("Select * from barang where kode=?", new String[]{nim});
        if (cursor.getCount()>0){
            long result = db.update("barang",values, "kode=?",new String[]{nim});
            if (result == -1){
                return false;
            }else {
                return true;
            }

        }else {
            return  false;
        }
    }

    public Boolean checkkodebarang (String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from barang where kode=?", new String[] {nim});
        if (cursor.getCount()>0)
            return true;
        else
            return false;


    }
}

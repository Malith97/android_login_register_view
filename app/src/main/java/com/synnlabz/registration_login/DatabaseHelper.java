package com.synnlabz.registration_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper{


    public DatabaseHelper(@Nullable Context context) {
        super(context, "THA_DB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table User_Infor(indexno text primary key,password text,name text,email text,mobile text,gpa text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists User_Infor");
        onCreate(db);
    }

    public boolean insert(String name , String email , String indexno , String mobile , String gpa , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("indexno",indexno);
        contentValues.put("mobile",mobile);
        contentValues.put("gpa",gpa);
        contentValues.put("password",password);

        long ins = db.insert("User_Infor",null,contentValues);

        if (ins == -1) {
            return false;
        }else{
            return true;
        }
    }

    public boolean checkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from User_Infor where email=?",new String[]{email});

        if (cursor.getCount()>0){
            return false;
        }else {
            return true;
        }
    }

    public String emailpassword(String indexno , String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select indexno from User_Infor where indexno=? and password=?",new String[]{indexno,password});
        String temp = null;
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                temp = cursor.getString(0);
            }
        }
        return temp;
    }

    public Cursor alldata(String Indexno){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User_Infor where indexno=? ",new String[]{Indexno});
        return cursor;
    }

}

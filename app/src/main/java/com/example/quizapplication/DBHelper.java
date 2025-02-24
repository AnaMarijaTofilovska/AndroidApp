package com.example.quizapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
//DBHelper CONTAINS THE CODE FOR MY SQLITE DATABASE
public class DBHelper extends SQLiteOpenHelper {

    public static  final  String DBNAME="login.db"; //naming my database that will be created in my device
    public DBHelper(Context context) {
        super(context, "login.db", null, 1);
    }  // creating database in login.db


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table users(username TEXT primary key, password TEXT)");
        //creating table users with 2 columns username and password

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop table if exists users");
        //checking if table users already exsists
    }
    public Boolean insertData(String username, String password){ //Inserting Data into database
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put("username",username);
        contentValues.put("password",password);

        long result = MyDB.insert("users",null,contentValues);
        if(result==-1) return false; //if insertion failed
        else
            return true;

    }
    //Check user function,if user already exsists
    public Boolean chekuser (String username){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from users where username=?",new String[] {username});
        if(cursor.getCount()>0) //if user exsists return true
            return true;
        else
            return false; //if it doesn't false
    }
    //Check UsernamePassword function, if that username and same password already exsists
    public Boolean chekusernamepassword (String username,String password){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from users where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

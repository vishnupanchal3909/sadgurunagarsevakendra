package com.vishnu.sadgurunagarsevakendra.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbClassHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName";
    public static final String Table_name = "UserInformation";

    public DbClassHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Table_name+"( name Text,address text,mobile text,jnumber text,email text,password text )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(db);
    }

    public Boolean insert(String n,String a,String m,String j,String e,String p){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",n);
        values.put("address",a);
        values.put("mobile",m);
        values.put("jnumber",j);
        values.put("email",e);
        values.put("passwprd",p);
        db.insert(Table_name,null,values);
        return  true;
    }
//    public Cursor ViewData(){
//        return
//    }
}

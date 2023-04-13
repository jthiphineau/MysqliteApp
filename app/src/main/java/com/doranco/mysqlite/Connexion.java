package com.doranco.mysqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Connexion extends SQLiteOpenHelper {
    public Connexion(Context context){
        super(context, namedb, null, 1);
    }
    private static final String namedb="userdb";
    private static final String table_user="create table user(id integer primary key autoincrement, nom text not null, fonction text not null, age integer not null);";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(table_user);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table user");
        onCreate(sqLiteDatabase);


    }
}
package com.doranco.mysqlite;

import android.database.sqlite.SQLiteOpenHelper;

public class Connexion extends SQLiteOpenHelper {
    public Connexion(Context context){
        super(contex, namedb, null, 1);
    }

    private static final String namedb="userdb"
}

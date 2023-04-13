package com.doranco.mysqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Connexion conn=null;
    private SQLiteDatabase sd=null;
    private AppCompatEditText nom_txt=null, fonction_txt=null, age_txt=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nom_txt=findViewById(R.id.nom);
        fonction_txt=findViewById(R.id.fonction);
        age_txt=findViewById(R.id.age);

        conn=new Connexion(this);
        sd=conn.getReadableDatabase();



    }
    public void save(View arg){
        try{
            if(sd!=null){
                ContentValues cv=new ContentValues();
                cv.put("nom", nom_txt.getText().toString());
                cv.put("fonction", fonction_txt.getText().toString());
                cv.put("age", age_txt.getText().toString());
                long r= sd.insertOrThrow("user", null, cv);
                if(r>0){
                    Toast.makeText(this, "Cet utilisateur est enregistré avec succès! ", Toast.LENGTH_LONG).show();
                    Log.e("Mysqlite", "OK");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void get(View arg){

        Cursor cursor=sd.rawQuery("select *from user", null);
        cursor.moveToLast();
        int idm=cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        String nom=cursor.getString(cursor.getColumnIndexOrThrow("nom"));
        int age=cursor.getInt(cursor.getColumnIndexOrThrow("age"));
        String fonction=cursor.getString(cursor.getColumnIndexOrThrow("fonction"));
        nom_txt.setText(nom);
        age_txt.setText(age+"");
        fonction_txt.setText(fonction);
        cursor.close();

    }

}
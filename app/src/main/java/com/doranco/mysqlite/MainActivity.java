package com.doranco.mysqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Connexion conn = null;
    private SQLiteDatabase sd = null;
    private AppCompatEditText nom_txt=null, fonction_txt=null, age_txt=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom_txt=findViewById(R.id.nom);
        fonction_txt=findViewById(R.id.fonction);
        age_txt=findViewById(R.id.age);

        conn = new Connexion(this);

        sd = conn.getReadableDatabase();
        try {
            if (sd != null) {
                ContentValues cv = new ContentValues();
                cv.put("nom", nom_txt.getText().toString());
                cv.put("fonction", fonction_txt.getText().toString());
                cv.put("age", age_txt.getText().toString());
                long r = sd.insertOrThrow("user", null, cv);
                if (r > 0) {
                    Toast.makeText(this, "Cet utilisateur est enregistré avec succés!", Toast.LENGTH_LONG).show();
                    Log.e("Mysqlite", "Ok");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
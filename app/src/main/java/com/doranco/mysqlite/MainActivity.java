package com.doranco.mysqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.room.Room;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Connexion conn = null;
    private SQLiteDatabase sd = null;
    private AppCompatEditText nom_txt = null, fonction_txt = null, age_txt = null;
    AppDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nom_txt = findViewById(R.id.nom);
        fonction_txt = findViewById(R.id.fonction);
        age_txt = findViewById(R.id.age);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "userdb").build();


    }

    public void save(View arg) {
        try {

            /* ---sans BDD---


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

             */

            new Thread(new Runnable() {
                @Override
                public void run() {
                    User user = new User();
                    user.setAge(Integer.parseInt(age_txt.getText().toString()));
                    user.setId(15);
                    user.setFonction(fonction_txt.getText().toString());
                    user.setNom(nom_txt.getText().toString());

                    db.userDAO().enregistrerUser(user);
                    Log.e("ROOM PERSISTENCE ===> ", "L'enregistremente est fait avec succès");

                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void get(View arg) {


        /* ---sans BDD---

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
        */

        /* ---avec BDD---*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> listes = db.userDAO().getAll();
                // récupérer tous les utilisateurs enregistrés dans la BDD
                if (listes != null && !listes.isEmpty()) {
                    User user = listes.get(listes.size() - 1); // récupérer le dernier utilisateur enregistré
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            nom_txt.setText(user.getNom());
                            age_txt.setText(String.valueOf(user.getAge()));
                            fonction_txt.setText(user.getFonction());
                        }
                    });
                }
            }
        }).start();
    }
}






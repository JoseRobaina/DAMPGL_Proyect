package com.example.aplicacion2.empleate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicacion2.empleate.BDData.AdminSQLiteOpenHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText userText, passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        userText = (EditText) findViewById(R.id.editTextUser);
        passText = (EditText) findViewById(R.id.editTextPass);

    }

    public void consulta(View v) {

        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,null);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String user = userText.getText().toString();
        String pass = passText.getText().toString();
        String[] args = new String[]{user,pass};
        Cursor fila = bd.rawQuery(
                "select id from login where login=? and pass =?", args);

        if (fila.moveToFirst()) {
            String id = fila.getString(0);
            sp.edit().putBoolean("logged",true).apply();
            sp.edit().putString("Id",id).apply();

            goToIndex(id);

        } else {

            sp.edit().putBoolean("logged",false).apply();

            Toast.makeText(this, "Usuario o Contraseña incorrecta",

                    Toast.LENGTH_SHORT).show();
        }
        fila.close();
    }

    private void goToIndex(String Id) {
        Intent intent = new Intent(getApplicationContext(), IndexDrawerActivity.class);
        intent.putExtra("Id",Id);
        startActivity(intent);
    }
}

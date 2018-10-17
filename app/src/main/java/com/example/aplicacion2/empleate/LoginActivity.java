package com.example.aplicacion2.empleate;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,null);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String user = userText.getText().toString();
        String pass = passText.getText().toString();

        Cursor fila = bd.rawQuery(
                "select id from login where login='" + user + "' and pass ='" + pass +"'", null);

        if (fila.moveToFirst()) {
            Intent intent = new Intent(getApplicationContext(), IndexDrawerActivity.class);
            intent.putExtra("id",fila.getString(0));
            startActivity(intent);

        } else {
            Toast.makeText(this, "Usuario o Contraseña incorrecta",

                    Toast.LENGTH_SHORT).show();
        }
        fila.close();
        bd.close();

    }
}

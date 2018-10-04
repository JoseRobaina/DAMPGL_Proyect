package com.example.aplicacion2.empleate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class IndexActivity extends AppCompatActivity {
    String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        UserID = getIntent().getExtras().getString("id");

    }

    public void GoProflie(View v){
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.putExtra("id",UserID);
        startActivity(intent);
    }

    public void GoListado(View v){
        Toast.makeText(this, "Funcionalidad no disponible",Toast.LENGTH_SHORT).show();
    }
}

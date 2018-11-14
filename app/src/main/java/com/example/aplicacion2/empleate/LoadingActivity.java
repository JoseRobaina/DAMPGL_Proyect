package com.example.aplicacion2.empleate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        getSupportActionBar().hide();

        LoadImg();

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
                if(sp.getBoolean("logged",false)){
                    goToIndex(sp.getString("Id",""));
                } else {
                    goToLogin();
                }
            }
        }, 3000); // 3 segundos de "delay"
    }

    private void LoadImg() {
        ImageView load = (ImageView) findViewById(R.id.imageViewLoading);
        load.setBackgroundResource(R.drawable.loading);

        AnimationDrawable frameAnimation = (AnimationDrawable) load.getBackground();
        frameAnimation.start();
    }

    private void goToLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void goToIndex(String id) {
        Intent intent = new Intent(getApplicationContext(), IndexDrawerActivity.class);
        //intent.putExtra("Id",id);
        startActivity(intent);
    }
}

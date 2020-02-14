package com.razan.MyCarTracks; // 5/2 last modified

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000; //Splash Screen code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(MainActivity.this, events.class);
                startActivity(homeIntent);
                finish();
            }

        },SPLASH_TIME_OUT);
    }
}
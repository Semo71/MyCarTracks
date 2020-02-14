package com.razan.MyCarTracks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class upcoming extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);
    }

    public void button6(View v){ //كود التنقل
        Intent intent = new Intent(upcoming.this,service.class);
        startActivity(intent);
        finish();
    }
    public void button10(View v){ // كود التنقل
        Intent intent = new Intent(upcoming.this,events.class);
        startActivity(intent);
        finish();
    }
    public void button11 (View v){ // كود التنقل
        Intent intent = new Intent(upcoming.this,service.class);
        startActivity(intent);
        finish();
    }

}

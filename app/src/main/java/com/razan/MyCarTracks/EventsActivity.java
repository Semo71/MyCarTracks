package com.razan.MyCarTracks;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class EventsActivity extends AppCompatActivity {

    private LinearLayout mCarServicesLL;
    private LinearLayout mUpcomingServicesLL;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        mContext = EventsActivity.this;

        mCarServicesLL = findViewById(R.id.carServicesLL);
        mUpcomingServicesLL = findViewById(R.id.upcomingServicesLL);


        /** Intent to go to UpcomingActivity class **/
        mUpcomingServicesLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, UpcomingActivity.class);
                startActivity(mIntent);
            }
        });

        /** Intent to go to ServicesActivity class **/
        mCarServicesLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, ServicesActivity.class);
                startActivity(mIntent);
            }
        });
    }



}



package com.razan.MyCarTracks.Alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.razan.MyCarTracks.R;

public class TestActivity extends AppCompatActivity {
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        mContext=TestActivity.this;
        alarmMgr = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(mContext, AlarmReceiver.class);
        intent.putExtra("AlarmReceiver","Hi");

        alarmIntent = PendingIntent.getBroadcast(mContext, 423, intent, 0);

        long i=5*1000;
        alarmMgr.set(AlarmManager.RTC_WAKEUP,

                        System.currentTimeMillis() + (5 * 1000), alarmIntent);

    }
}

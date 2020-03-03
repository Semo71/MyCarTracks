package com.razan.MyCarTracks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.razan.MyCarTracks.Alarm.AlarmManagerProvider;
import com.razan.MyCarTracks.Alarm.AlarmScheduler;
import com.razan.MyCarTracks.Alarm.AlarmService;
import com.razan.MyCarTracks.Alarm.ExampleJobService;

import java.util.Calendar;

public class EventsActivity extends AppCompatActivity {
    private static final String TAG = "EventsActivity";

    private ImageView mUpcomingImageView;
    private ImageView mAddServicesImageView;
    private Context mContext;
    private Calendar mCalendar;
    private int mYear, mMonth, mHour, mMinute, mDay;
    private String mTime;
    private String mDate;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Log.d("TimeInMillis",EventsActivity.class.getSimpleName());

        mContext=EventsActivity.this;
        mCalendar = Calendar.getInstance();

        mUpcomingImageView=findViewById(R.id.upcomingImageView);
        mAddServicesImageView=findViewById(R.id.addServicesImageView);

        mAddServicesImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComponentName componentName = new ComponentName(EventsActivity.this, ExampleJobService.class);
                JobInfo info = new JobInfo.Builder(123, componentName)
                        .setRequiresCharging(true)
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                        .setPersisted(true)
                        .setPeriodic(15 * 60 * 1000)
                        .build();

                JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                int resultCode = scheduler.schedule(info);
                if (resultCode == JobScheduler.RESULT_SUCCESS) {
                    Log.d(TAG, "Job scheduled");
                } else {
                    Log.d(TAG, "Job scheduling failed");
                }

/*
                Intent mIntent=new Intent(mContext, ServicesActivity.class);
                startActivity(mIntent);
*/

            }
        });

        mUpcomingImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                scheduler.cancel(123);
                Log.d(TAG, "Job cancelled");

/*
                Intent mIntent=new Intent(mContext, UpcomingActivity.class);
                startActivity(mIntent);
*/

            }
        });

        //setAlarm(EventsActivity.this);


/*
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH) + 1;
        mDay = mCalendar.get(Calendar.DATE);
*/

/*
        mDate = mDay + "/" + mMonth + "/" + mYear;
        mTime = mHour + ":" + mMinute;
*/

/*
        mCalendar.set(Calendar.MONTH, -- mMonth);
        mCalendar.set(Calendar.YEAR, mYear);
        mCalendar.set(Calendar.DAY_OF_MONTH, mDay);
*/
        mCalendar.set(Calendar.HOUR_OF_DAY, 23);
        mCalendar.set(Calendar.MINUTE, 25);
        mCalendar.set(Calendar.SECOND, 0);

        String CONTENT_AUTHORITY = "com.razan.MyCarTracks";

        Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

        String PATH_VEHICLE = "reminder-path";

        Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_VEHICLE);

        long selectedTimestamp =  mCalendar.getTimeInMillis();
        Log.d("TimeInMillis",String.valueOf(selectedTimestamp));
        Uri currentVehicleUri = ContentUris.withAppendedId(CONTENT_URI, 4);
        //content://com.delaroystudios.alarmreminder/reminder-path/5
        Log.d("TimeInMillis",String.valueOf(currentVehicleUri));





/*
        long repeatTime=3000;
        new AlarmScheduler().setAlarm(getApplicationContext(), selectedTimestamp,currentVehicleUri);
*/

    }



    public void setAlarm(Context context)
    {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, AlarmService.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 3000, pi); // Millisec * Second * Minute
    }

    public void createNotification (View view) {
        Intent myIntent = new Intent(getApplicationContext() , NotifyService. class ) ;
        AlarmManager alarmManager = (AlarmManager) getSystemService( ALARM_SERVICE ) ;
        PendingIntent pendingIntent = PendingIntent. getService ( this, 0 , myIntent , 0 ) ;
        Calendar calendar = Calendar. getInstance () ;
        calendar.set(Calendar. SECOND , 0 ) ;
        calendar.set(Calendar. MINUTE , 0 ) ;
        calendar.set(Calendar. HOUR , 0 ) ;
        calendar.set(Calendar. AM_PM , Calendar. AM ) ;
        calendar.add(Calendar. DAY_OF_MONTH , 1 ) ;
        alarmManager.setRepeating(AlarmManager. RTC_WAKEUP , calendar.getTimeInMillis() , 1000 * 60 * 60 * 24 , pendingIntent) ;
    }


}

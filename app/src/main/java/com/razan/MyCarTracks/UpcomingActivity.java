package com.razan.MyCarTracks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.razan.MyCarTracks.Alarm.ServicesDateManager;
import com.razan.MyCarTracks.SharedPrefsManager.SharedPrefsKeys;

import java.util.Calendar;

import static com.razan.MyCarTracks.SharedPrefsManager.MyApplication.NOTIFICATION_CHANNEL_ID;

public class UpcomingActivity extends AppCompatActivity {

    private Button mBackButton;
    private TextView mCarInsuranceTxtV;
    private TextView mVehicleInspectionTxtV;
    private TextView mAcGasTxtV;
    private TextView mBeltsTxtV;
    private TextView mSparkPlugsTxtV;
    private TextView mWheelsTxtV;
    private TextView mBatteryTxtV;
    private TextView mFiltersTxtV;
    private TextView mSpeedLimitsTxtV;
    private Context mContext;

    public static Query query;
    public static ValueEventListener valueEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);

        mContext = UpcomingActivity.this;
        connectUiComponent();
        updatingUIViews();
        retrieveSensorData();

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    //Connecting UI Views with id from xml file
    private void connectUiComponent() {
        mBackButton =findViewById(R.id.backButton);
        mCarInsuranceTxtV =findViewById(R.id.carInsuranceTxtV);
        mVehicleInspectionTxtV =findViewById(R.id.vehicleInspectionTxtV);
        mAcGasTxtV =findViewById(R.id.acGasTxtV);
        mBeltsTxtV =findViewById(R.id.beltsTxtV);
        mSparkPlugsTxtV =findViewById(R.id.sparkPlugsTxtV);
        mWheelsTxtV =findViewById(R.id.wheelsTxtV);
        mFiltersTxtV =findViewById(R.id.filtersTxtV);
        mSpeedLimitsTxtV =findViewById(R.id.speedLimitsTxtV);
        mBatteryTxtV =findViewById(R.id.batteryTxtV);
    }

    //Updating UI views in case Service Model for each service != null
    private void updatingUIViews() {
        if (ServicesDateManager.getServicesModel(SharedPrefsKeys.CAR_INSURANCE).getCalendar() != null)
            mCarInsuranceTxtV.setText(getDateFormat(ServicesDateManager.getServicesModel(SharedPrefsKeys.CAR_INSURANCE).getCalendar()));

        if (ServicesDateManager.getServicesModel(SharedPrefsKeys.VEHICLE_INSPECTION).getCalendar() != null)
            mVehicleInspectionTxtV.setText(getDateFormat(ServicesDateManager.getServicesModel(SharedPrefsKeys.VEHICLE_INSPECTION).getCalendar()));

        if (ServicesDateManager.getServicesModel(SharedPrefsKeys.AC_GAS).getCalendar() != null)
            mAcGasTxtV.setText(getDateFormat(ServicesDateManager.getServicesModel(SharedPrefsKeys.AC_GAS).getCalendar()));

        if (ServicesDateManager.getServicesModel(SharedPrefsKeys.BELTS).getCalendar() != null)
            mBeltsTxtV.setText(getDateFormat(ServicesDateManager.getServicesModel(SharedPrefsKeys.BELTS).getCalendar()));

        if (ServicesDateManager.getServicesModel(SharedPrefsKeys.SPARK_PLUGS).getCalendar() != null)
            mSparkPlugsTxtV.setText(getDateFormat(ServicesDateManager.getServicesModel(SharedPrefsKeys.SPARK_PLUGS).getCalendar()));

        if (ServicesDateManager.getServicesModel(SharedPrefsKeys.WHEELS).getCalendar() != null)
            mWheelsTxtV.setText(getDateFormat(ServicesDateManager.getServicesModel(SharedPrefsKeys.WHEELS).getCalendar()));

        if (ServicesDateManager.getServicesModel(SharedPrefsKeys.BATTERY).getCalendar() != null)
            mBatteryTxtV.setText(getDateFormat(ServicesDateManager.getServicesModel(SharedPrefsKeys.BATTERY).getCalendar()));

    }

    //Returning a date format string from calender parameter
    private String getDateFormat(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH)+1;
        return calendar.get(Calendar.DAY_OF_MONTH) + "/" + month
                + "/" + calendar.get(Calendar.YEAR);
    }

    private void retrieveSensorData(){
        query = FirebaseDatabase.getInstance().getReference().child("SensorData");

        if (valueEventListener==null){
            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){

                        //List<SensorDataModel> mSensorDataModelList = new ArrayList<>();
                        for (DataSnapshot mDataSnapshot:dataSnapshot.getChildren()){
                            SensorDataModel mSensorDataModel = mDataSnapshot.getValue(SensorDataModel.class);
                            //mSensorDataModelList.add(mSensorDataModel);
                            if (mSensorDataModel.getDistance()>3)
                                sendMyNotification("Distance",""+mSensorDataModel.getDistance(),mContext);


                        }

                    }else {
                        Toast.makeText(mContext,"Data not exist",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            query.addValueEventListener(valueEventListener);

        }

    }

    /** Method to send notification, Arguments (Notification Title, Notification Message,
     *  The Context of the BroadcastReceiver) **/
    private void sendMyNotification(String title, String message, Context context) {

        /** Serious of intents that starts when notification is pressed **/
        Intent intentEventsActivity = new Intent (context, EventsActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(EventsActivity.class);
        stackBuilder.addNextIntent(intentEventsActivity);
        Intent intentUpcomingActivity = new Intent (context, UpcomingActivity.class);
        stackBuilder.addNextIntent(intentUpcomingActivity);
        PendingIntent contentIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        /** Creating the notification **/
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setSound(soundUri)
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify((int) System.currentTimeMillis(), notificationBuilder.build());
    }


}

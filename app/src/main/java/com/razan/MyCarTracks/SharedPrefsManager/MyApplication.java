package com.razan.MyCarTracks.SharedPrefsManager;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;


/** Class that fires up when app starting before all activities **/
public class MyApplication extends Application {

    /** Notification Channel Variables **/
    public static final String NOTIFICATION_CHANNEL_ID = "MyCarTracksID";
    public static final String NOTIFICATION_CHANNEL_NAME = "MyCarTracksName";
    public static final String NOTIFICATION_CHANNEL_DESCRIPTION = "MyCarTracksDescription";

    @Override
    public void onCreate() {
        super.onCreate();
        /** Initializing Shared Preference only one time in here **/
        SharedPrefsManager.initialize(this);

        createChannelAndHandleNotifications();
    }


    /** Creating Channel for Notifications **/
    private void createChannelAndHandleNotifications() {
        try{
            Log.d("CodeTracing","createChannelAndHandleNotifications");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(
                        NOTIFICATION_CHANNEL_ID,
                        NOTIFICATION_CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_HIGH);
                channel.setDescription(NOTIFICATION_CHANNEL_DESCRIPTION);
                channel.setShowBadge(true);

                NotificationManager notificationManager =  getSystemService(NotificationManager.class);
                assert notificationManager !=null;
                notificationManager.createNotificationChannel(channel);
            }

        }catch (Exception ex) {
            Log.d("CodeTracing","createChannelAndHandleNotifications  "+ex.getMessage());
        }
    }

}

package com.razan.MyCarTracks.SharedPrefsManager;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import com.razan.MyCarTracks.SharedPrefsManager.SharedPrefsManager;

public class MyApplication extends Application {
    public static final String NOTIFICATION_CHANNEL_ID = "nh-demo-channel-id";
    public static final String NOTIFICATION_CHANNEL_NAME = "Notification Hubs Demo Channel";
    public static final String NOTIFICATION_CHANNEL_DESCRIPTION = "Notification Hubs Demo Channel";

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefsManager.initialize(this);
        createChannelAndHandleNotifications();
    }

    private void createChannelAndHandleNotifications() {
        try{
            Log.d("TimeInMillis","createChannelAndHandleNotifications");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(
                        NOTIFICATION_CHANNEL_ID,
                        NOTIFICATION_CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_HIGH);
                channel.setDescription(NOTIFICATION_CHANNEL_DESCRIPTION);
                channel.setShowBadge(true);

                NotificationManager notificationManager =  getSystemService(NotificationManager.class);

                notificationManager.createNotificationChannel(channel);
            }

        }catch (Exception ex) {

        }
    }

}

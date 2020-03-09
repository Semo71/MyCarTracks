package com.razan.MyCarTracks.Alarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import com.razan.MyCarTracks.EventsActivity;
import com.razan.MyCarTracks.R;
import com.razan.MyCarTracks.UpcomingActivity;

import static com.razan.MyCarTracks.SharedPrefsManager.MyApplication.NOTIFICATION_CHANNEL_ID;

/** BroadcastReceiver that fires up when the alarm goes off to send a notification **/
public class AlarmReceiver extends BroadcastReceiver {

    String title;
    String message;

    @Override
    public void onReceive(Context context, Intent intent) {

        title = intent.getStringExtra("title");
        message = intent.getStringExtra("message");

        sendMyNotification(title, "You have " + title + " at " + message, context);

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

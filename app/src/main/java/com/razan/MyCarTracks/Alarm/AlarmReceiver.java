package com.razan.MyCarTracks.Alarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.razan.MyCarTracks.R;
import com.razan.MyCarTracks.UpcomingActivity;

import static com.razan.MyCarTracks.SharedPrefsManager.MyApplication.NOTIFICATION_CHANNEL_ID;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        Log.e("onReceive","onReceive");
        sendMyNotification("Test","Test",context);
    }
    private void sendMyNotification(String title,String message,Context context) {
        //On click of notification it redirect to this Activity
        Log.d("TimeInMillis","sendMyNotification");


        Intent intent = new Intent(context, UpcomingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 563,
                intent, PendingIntent.FLAG_ONE_SHOT);


        Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setSound(soundUri)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

}

package com.razan.MyCarTracks.Alarm;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;

import com.razan.MyCarTracks.R;
import com.razan.MyCarTracks.SharedPrefsManager.SharedPrefsKeys;
import com.razan.MyCarTracks.SharedPrefsManager.SharedPrefsManager;
import com.razan.MyCarTracks.UpcomingActivity;

import static com.razan.MyCarTracks.SharedPrefsManager.MyApplication.NOTIFICATION_CHANNEL_ID;

public class AlarmService extends IntentService {

    private static final String TAG = AlarmService.class.getSimpleName();
    private static final int NOTIFICATION_ID = 42;
    public static final int PENDING_INTENT_REQUEST_CODE=77;
    String idChannel = "my_channel_01";


    //This is a deep link intent, and needs the task stack
    public static PendingIntent getReminderPendingIntent(Context context, Uri uri) {
        Intent action = new Intent(context, AlarmService.class);
        action.setData(uri);
        return PendingIntent.getService(context, PENDING_INTENT_REQUEST_CODE, action, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    public AlarmService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("TimeInMillis", "onHandleIntent");

        String message = "";
        if (SharedPrefsManager.getInstance().getString(SharedPrefsKeys.KEY_TITLE)!=null) {
            message = SharedPrefsManager.getInstance().getString(SharedPrefsKeys.KEY_TITLE);
        }

        sendMyNotification("Test",message);
/*
        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
        Vibrator vibrator=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel mChannel = null;
        Uri uri = intent.getData();
        Log.d("TimeInMillis",String.valueOf(uri));

*/


/*
        //Display a notification to view the task details
        Intent action = new Intent(this, UpcomingActivity.class);
        action.setData(uri);
        PendingIntent operation = TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(action)
                .getPendingIntent(PENDING_INTENT_REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT);

        //Grab the task description
        String description = "";
        if (SharedPrefsManager.getInstance().getString(SharedPrefsKeys.KEY_TITLE)!=null) {
            description = SharedPrefsManager.getInstance().getString(SharedPrefsKeys.KEY_TITLE);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, null);
        builder.setContentTitle("Reminder")
                .setContentText(description)
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(operation)
                .setVibrate(new long[]{100})
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setAutoCancel(true);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(idChannel,getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
            // Configure the notification channel.
            mChannel.setDescription("Reminder");
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            manager.createNotificationChannel(mChannel);
        } else {
            builder.setContentTitle(getString(R.string.app_name))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setColor(ContextCompat.getColor(this, R.color.transparent))
                    .setVibrate(new long[]{100, 250})
                    .setLights(Color.YELLOW, 500, 5000)
                    .setAutoCancel(true);
        }


        manager.notify(NOTIFICATION_ID, builder.build());
*/
    }

    private void sendMyNotification(String title,String message) {
        //On click of notification it redirect to this Activity
        Log.d("TimeInMillis","sendMyNotification");


        Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setSound(soundUri)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

}
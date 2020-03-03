/*
package com.razan.MyCarTracks;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

public class AutoStartBroadcastReceiver extends BroadcastReceiver
{
    AlarmBroadcastReceiver alarm = new AlarmBroadcastReceiver();
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
        {
            alarm.setAlarm(context);
            Log.d("Alarm","setAlarm AutoStartBroadcastReceiver");

        }
    }
}*/

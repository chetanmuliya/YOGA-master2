package com.example.chetan.yoga.reciever;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.chetan.yoga.R;
import com.example.chetan.yoga.activity.MainActivity;

public class AlarmNotificationRecevier extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,new Intent(context,MainActivity.class),0);

        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.yogalogo)
                .setContentTitle("its Yoga Time")
                .setContentText("time to Train")
                .setContentInfo("info")
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }

}

package com.example.strange.tempnote;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by riyadh on 11-Jul-18.
 */

public class processTimerReciever extends BroadcastReceiver {

    public static Map<Long, Integer> map = new TreeMap<Long, Integer>();
    int count = 0;

    @Override
    public void onReceive(Context context, Intent intent) {

        BaseFun fun = new BaseFun(context);
        Set set = map.entrySet();
        Iterator it = set.iterator();
        //note deletion task
        count = 0;
        while (it.hasNext()) {
            Map.Entry val = (Map.Entry) it.next();
            if (Long.valueOf(System.currentTimeMillis()).compareTo((Long) val.getKey()) != -1) {
                fun.removeById(Integer.parseInt(String.valueOf(val.getValue())));
                map.remove(val.getKey());
                count++;
            }
        }
        MainActivity.mAdapter.swapCursor(fun.getAllByCursor());
        MainActivity.mAdapter.notifyDataSetChanged();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = String.valueOf(R.string.channel_name);
            String description = String.valueOf(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("default", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "default")
                .setSmallIcon(R.drawable.ic_down)
                .setContentTitle("TempNote")
                .setContentText(count + " notes deleted")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[]{1000, 1000});


        if (count > 0) {
            count=0;
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(177, mBuilder.build());
        }

    }
}


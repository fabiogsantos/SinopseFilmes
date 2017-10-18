package com.tecdam.fabiogsantos.sinopsefilmes.repository.api;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.tecdam.fabiogsantos.sinopsefilmes.R;

/**
 * Created by fabio.goncalves on 14/10/2017.
 */

public class NotificationDownload {

    public void sendNotification(Context context, String title, String message) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(android.R.drawable.stat_sys_download_done)
                        .setContentTitle(title)
                        .setContentText(message);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, mBuilder.build());

    }
}

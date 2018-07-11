package com.sahabatdeveloper.realtimechatsahabatdeveloper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    public static final String INTENT_ACTION_HANDLEGCM = "com.intentexample.gcm";
    public volatile static boolean isHandled = false;
    public volatile static RemoteMessage remoteMessage = null;
    private PendingIntent pendingIntent;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Memeriksa apakan pesan berisi muatan pemberitahuan
        if(remoteMessage.getNotification() != null){

            //Ini Hanya di perlukan jika chat realtime
            Log.d(TAG, "Notifikasi: "+remoteMessage.getNotification().getBody());
            Intent toActivityIntent = new Intent();
            toActivityIntent.setAction(INTENT_ACTION_HANDLEGCM);
            MyFirebaseMessagingService.isHandled = false;
            MyFirebaseMessagingService.remoteMessage = remoteMessage;
            //Saat Halaman Activity dibuak seharusnya isHandled true
            LocalBroadcastManager.getInstance(this).sendBroadcastSync(toActivityIntent);
            // --------------------------------------------------------------

            if (!MyFirebaseMessagingService.isHandled) { //kondisi hanya diperlukan saat chat realtime

                handleNotification(remoteMessage); //hanya method ini untuk notifikasi
            }

        }
    }

    private void handleNotification(RemoteMessage remoteMessage) {
        Notification notification  = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            notification = new Notification.Builder(this)
                    .setCategory(Notification.CATEGORY_MESSAGE)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true)
                    .setVisibility(Notification.VISIBILITY_PUBLIC).build();
        }
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification );
    }
}

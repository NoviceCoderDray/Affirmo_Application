package com.example.affirmo_welcome;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.*;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import java.util.Random;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences prefs = context.getSharedPreferences("notif_prefs", Context.MODE_PRIVATE);
        if (!prefs.getBoolean("notifs_enabled", true)) return;

        // Custom full messages
        String[] messages = {
                "Scroll to your affirmations to motivate your day!",
                "Create your own affirmation to boost your day!",
                "Send your own affirmation to Andrey's Database to boost his spirit!",
                "Motivate yourself with our Affirmation and your own!"
        };

        // Randomly pick one message
        String message = messages[new Random().nextInt(messages.length)];

        // Notification setup
        String channelId = "default";
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && manager != null) {
            NotificationChannel channel = new NotificationChannel(
                    channelId, "Daily Affirmations", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        PendingIntent pi = PendingIntent.getActivity(context, 0,
                new Intent(context, welcome_screen.class), PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Affirmation Reminder")
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pi);

        if (manager != null) {
            manager.notify(new Random().nextInt(1000), builder.build());
        }
    }
}

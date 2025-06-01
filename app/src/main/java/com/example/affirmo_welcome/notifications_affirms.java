package com.example.affirmo_welcome;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class notifications_affirms extends AppCompatActivity {

    Switch notificationSwitch;
    SharedPreferences prefs;
    String key = "notifs_enabled";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_affirms);

        ImageButton backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the Welcome Screen
                Intent intent = new Intent(notifications_affirms.this, settings_options.class);
                startActivity(intent);
                finish();
            }
        });

        notificationSwitch = findViewById(R.id.notificationSwitch);
        prefs = getSharedPreferences("notif_prefs", MODE_PRIVATE);

        boolean enabled = prefs.getBoolean(key, true);
        notificationSwitch.setChecked(enabled);

        notificationSwitch.setOnCheckedChangeListener((btn, isChecked) -> {
            prefs.edit().putBoolean(key, isChecked).apply();
            Toast.makeText(this, isChecked ? "On" : "Off", Toast.LENGTH_SHORT).show();

            if (isChecked) scheduleNotification();
            else cancelNotification();
        });

        if (enabled) scheduleNotification();
    }

    void scheduleNotification() {
        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        long time = System.currentTimeMillis() + 60_000; // 1 minute
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }

    void cancelNotification() {
        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm.cancel(pendingIntent);

        SharedPreferences prefs = getSharedPreferences("themePrefs", MODE_PRIVATE);
        String theme = prefs.getString("selectedTheme", "pink");

        ConstraintLayout mainLayout = findViewById(R.id.main);
        if (theme.equals("blue")) {
            mainLayout.setBackgroundResource(R.drawable.blue_background);
        } else {
            mainLayout.setBackgroundResource(R.drawable.pink_background);
        }
    }
}
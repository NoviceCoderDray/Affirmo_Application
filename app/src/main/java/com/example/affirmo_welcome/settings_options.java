package com.example.affirmo_welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class settings_options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings_options);

        ImageButton backButton = findViewById(R.id.backButton);
        ImageButton notificationsButton = findViewById(R.id.notificationsButton);
        ImageButton themeButton = findViewById(R.id.theme_button);
        ImageButton aboutButton = findViewById(R.id.about_button);

        // Set OnClickListener to handle back navigation
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the Welcome Screen
                Intent intent = new Intent(settings_options.this, welcome_screen.class);
                startActivity(intent);
                finish();
            }
        });

        notificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settings_options.this, notifications_affirms.class);
                startActivity(intent);
                finish();
            }
        });

        themeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settings_options.this, theme_customization.class);
                startActivity(intent);
                finish();
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(settings_options.this, "About Affirmo! Preview", Toast.LENGTH_SHORT).show();
                // Navigate to About screen
                Intent intent = new Intent(settings_options.this, about_affirmo.class);
                startActivity(intent);
            }
        });

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
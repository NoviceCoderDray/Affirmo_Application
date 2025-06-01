package com.example.affirmo_welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 5000; // 5 seconds


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show a welcome message using Toast
        Toast.makeText(MainActivity.this, "Welcome to the Affirmo! Preview!", Toast.LENGTH_SHORT).show();

        SharedPreferences prefs = getSharedPreferences("themePrefs", MODE_PRIVATE);
        String theme = prefs.getString("selectedTheme", "pink");

        ConstraintLayout mainLayout = findViewById(R.id.main);
        if (theme.equals("blue")) {
            mainLayout.setBackgroundResource(R.drawable.blue_background);
        } else {
            mainLayout.setBackgroundResource(R.drawable.pink_background);
        }

        // Automatically navigate to welcome screen after a delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, welcome_screen.class);
            startActivity(intent);
            finish(); // finish to prevent back press from returning here
        }, SPLASH_DELAY);
    }
}
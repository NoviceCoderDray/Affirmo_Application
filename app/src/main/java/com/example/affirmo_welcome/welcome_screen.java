package com.example.affirmo_welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class welcome_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome_screen);
        // Apply window insets for edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences prefs = getSharedPreferences("themePrefs", MODE_PRIVATE);
        String theme = prefs.getString("selectedTheme", "pink");

        ConstraintLayout mainLayout = findViewById(R.id.main);
        if (theme.equals("blue")) {
            mainLayout.setBackgroundResource(R.drawable.blue_background);
        } else {
            mainLayout.setBackgroundResource(R.drawable.pink_background);
        }

        // Find all buttons
        ImageButton settingsButton = findViewById(R.id.settingsButton);
        Button affirmationListButton = findViewById(R.id.affirmationListButton);
        Button affirmSenderButton = findViewById(R.id.affirmSenderButton);
        Button createYourOwnButton = findViewById(R.id.createYourOwnButton);

        // Set click listeners
        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(welcome_screen.this, settings_options.class);
            startActivity(intent);
        });

        affirmationListButton.setOnClickListener(v -> {
            Intent intent = new Intent(welcome_screen.this, affirm_list.class);
            startActivity(intent);
        });

        affirmSenderButton.setOnClickListener(v -> {
            Intent intent = new Intent(welcome_screen.this, SendAffirms.class);
            startActivity(intent);
        });

        createYourOwnButton.setOnClickListener(v -> {
            Intent intent = new Intent(welcome_screen.this, create_affirms.class);
            startActivity(intent);
        });
    }
}


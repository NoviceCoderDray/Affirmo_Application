package com.example.affirmo_welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class theme_customization extends AppCompatActivity {

    private ConstraintLayout mainLayout;
    private RadioButton pinkOption, blueOption;
    private static final String PREFS_NAME = "themePrefs";
    private static final String THEME_KEY = "selectedTheme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_theme_customization);

        // Initialize views
        ImageButton backButton = findViewById(R.id.backButton);
        pinkOption = findViewById(R.id.lovelyPinkOption);
        blueOption = findViewById(R.id.friendlyBlueOption);
        Button applyButton = findViewById(R.id.applyButton);
        mainLayout = findViewById(R.id.main);

        // Load saved theme
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedTheme = prefs.getString(THEME_KEY, "pink");

        // Apply the saved theme visually
        if (savedTheme.equals("blue")) {
            mainLayout.setBackgroundResource(R.drawable.blue_background);
            blueOption.setChecked(true);
        } else {
            mainLayout.setBackgroundResource(R.drawable.pink_background);
            pinkOption.setChecked(true);
        }

        // Back button: go to settings screen
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(theme_customization.this, settings_options.class);
            startActivity(intent);
            finish();
        });

        // Apply theme button
        applyButton.setOnClickListener(v -> {
            String selectedTheme = pinkOption.isChecked() ? "pink" : "blue";

            // Save the selected theme
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(THEME_KEY, selectedTheme);
            editor.apply();

            // Apply the new theme
            if (selectedTheme.equals("blue")) {
                mainLayout.setBackgroundResource(R.drawable.blue_background);
            } else {
                mainLayout.setBackgroundResource(R.drawable.pink_background);
            }
        });
    }
}
package com.example.affirmo_welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class about_affirmo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about_affirmo);

        ImageButton backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(about_affirmo.this, settings_options.class);
                startActivity(intent);
                finish();
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
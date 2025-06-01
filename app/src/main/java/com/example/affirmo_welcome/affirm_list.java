package com.example.affirmo_welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager2.widget.ViewPager2;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class affirm_list extends AppCompatActivity {

    private List<String> affirmations;
    private ViewPager2 viewPager;
    private AffirmationAdapter adapter;

    private static final String PREFS_NAME = "customAffirmPrefs";
    private static final String CUSTOM_KEY = "customAffirmations";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_affirm_list);

        ConstraintLayout mainLayout = findViewById(R.id.main);
        viewPager = findViewById(R.id.viewPagerAffirmations);
        ImageButton backButton = findViewById(R.id.backButton);

        // Theme
        SharedPreferences prefs = getSharedPreferences("themePrefs", MODE_PRIVATE);
        String theme = prefs.getString("selectedTheme", "pink");
        mainLayout.setBackgroundResource(theme.equals("blue") ? R.drawable.blue_background : R.drawable.pink_background);

        // Default affirmations
        affirmations = new ArrayList<>(Arrays.asList(
                "I am worthy of love and respect.",
                "Every day I grow stronger and more confident.",
                "I attract positive energy and opportunities.",
                "My challenges help me grow and evolve.",
                "I am enough just as I am.",
                "I choose happiness and peace in every moment.",
                "Success flows to me effortlessly.",
                "I trust in my ability to make great decisions."
        ));

        // Load custom affirmations
        SharedPreferences customPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String json = customPrefs.getString(CUSTOM_KEY, null);
        if (json != null) {
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    affirmations.add(array.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Collections.shuffle(affirmations);
        adapter = new AffirmationAdapter(this, affirmations);
        viewPager.setAdapter(adapter);

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(affirm_list.this, welcome_screen.class));
            finish();
        });
    }
}

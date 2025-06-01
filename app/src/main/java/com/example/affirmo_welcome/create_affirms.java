package com.example.affirmo_welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.activity.EdgeToEdge;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class create_affirms extends AppCompatActivity {

    private ArrayList<String> customAffirmations;
    private ArrayAdapter<String> adapter;
    private SharedPreferences prefs;

    private static final String PREFS_NAME = "customAffirmPrefs";
    private static final String KEY = "customAffirmations";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_affirms);

        ConstraintLayout mainLayout = findViewById(R.id.main);
        EditText inputAffirm = findViewById(R.id.inputAffirmation);
        ListView customAffirmList = findViewById(R.id.customAffirmList);
        Button addButton = findViewById(R.id.AddButton);
        ImageButton backButton = findViewById(R.id.backButton);

        // Theme
        SharedPreferences themePrefs = getSharedPreferences("themePrefs", MODE_PRIVATE);
        String theme = themePrefs.getString("selectedTheme", "pink");
        mainLayout.setBackgroundResource(theme.equals("blue") ? R.drawable.blue_background : R.drawable.pink_background);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        customAffirmations = loadAffirmations();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, customAffirmations);
        customAffirmList.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            String text = inputAffirm.getText().toString().trim();
            if (!text.isEmpty()) {
                customAffirmations.add(text);
                inputAffirm.setText("");
                adapter.notifyDataSetChanged();
                saveAffirmations();
                Toast.makeText(this, "Affirmation added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        customAffirmList.setOnItemClickListener((parent, view, position, id) -> {
            String toEdit = customAffirmations.get(position);
            inputAffirm.setText(toEdit);
            customAffirmations.remove(position);
            adapter.notifyDataSetChanged();
            saveAffirmations();
            Toast.makeText(this, "Edit and press save", Toast.LENGTH_SHORT).show();
        });

        customAffirmList.setOnItemLongClickListener((parent, view, position, id) -> {
            customAffirmations.remove(position);
            adapter.notifyDataSetChanged();
            saveAffirmations();
            Toast.makeText(this, "Affirmation deleted", Toast.LENGTH_SHORT).show();
            return true;
        });

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(create_affirms.this, welcome_screen.class));
            finish();
        });
    }

    private ArrayList<String> loadAffirmations() {
        ArrayList<String> list = new ArrayList<>();
        String json = prefs.getString(KEY, null);
        if (json != null) {
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    list.add(array.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private void saveAffirmations() {
        JSONArray array = new JSONArray();
        for (String aff : customAffirmations) {
            array.put(aff);
        }
        prefs.edit().putString(KEY, array.toString()).apply();
    }
}

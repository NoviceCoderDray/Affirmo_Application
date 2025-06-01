package com.example.affirmo_welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.affirmo_welcome.databinding.ActivitySendAffirmsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SendAffirms extends AppCompatActivity {

    ActivitySendAffirmsBinding binding;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySendAffirmsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Firebase setup
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Affirmations");

        // Theme
        SharedPreferences prefs = getSharedPreferences("themePrefs", MODE_PRIVATE);
        String theme = prefs.getString("selectedTheme", "pink");
        if (theme.equals("blue")) {
            binding.main.setBackgroundResource(R.drawable.blue_background);
        } else {
            binding.main.setBackgroundResource(R.drawable.pink_background);
        }

        // Submit affirmation button
        binding.btnSubmitAffirms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = binding.currentAffirms.getText().toString().trim();

                if (!message.isEmpty()) {
                    AffirmationSender affirmation = new AffirmationSender(message);
                    String key = reference.push().getKey();
                    reference.child(key).setValue(affirmation).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                binding.currentAffirms.setText("");
                                Toast.makeText(SendAffirms.this, "Affirmation sent!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SendAffirms.this, "Failed to send", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(SendAffirms.this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Back button
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendAffirms.this, welcome_screen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}


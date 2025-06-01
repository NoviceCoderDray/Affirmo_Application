package com.example.affirmo_welcome;

public class AffirmationSender {
    private String message;

    public AffirmationSender() {
        // Required for Firebase deserialization
    }

    public AffirmationSender(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

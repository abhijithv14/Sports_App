package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_login extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView signUpText;
    private DB_Helper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signUpText = findViewById(R.id.signUpText);

        // Initialize database helper
        dbHelper = new DB_Helper(this);

        // Login button click listener
        loginButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(activity_login.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    // Check user credentials
                    if (dbHelper.checkUser(email, password)) {
                        Toast.makeText(activity_login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        // Navigate to home activity
                        Intent intent = new Intent(activity_login.this, home.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(activity_login.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("Login Error", "Error checking user", e);
                    Toast.makeText(activity_login.this, "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sign-up text click listener
        signUpText.setOnClickListener(view -> {
            Intent intent = new Intent(activity_login.this, activity_sign_up.class);
            startActivity(intent);
        });
    }
}

package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_sign_up extends AppCompatActivity {

    private EditText nameEditText, numberEditText, emailEditText, passwordEditText;
    private Button signUpButton;
    private DB_Helper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText = findViewById(R.id.nameEditText);
        numberEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signUpButton = findViewById(R.id.signupButton);

        dbHelper = new DB_Helper(this);

        // Sign-Up Button Click Listener
        signUpButton.setOnClickListener(view -> {
            String name = nameEditText.getText().toString().trim();
            String number = numberEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (name.isEmpty() || number.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(activity_sign_up.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                if (dbHelper.insertUser(name, number, email, password)) {
                    Toast.makeText(activity_sign_up.this, "Sign-Up Successful! Please Login", Toast.LENGTH_SHORT).show();
                    finish(); // Close Sign-Up Activity and return to Login
                } else {
                    Toast.makeText(activity_sign_up.this, "Sign-Up Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        TextView loginText = findViewById(R.id.loginText);
        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(activity_sign_up.this, activity_login.class);
            startActivity(intent);
        });

    }
}
package com.studdybuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etMajor;
    private EditText etClassLevel;
    private Button btnRegister;

    // Dummy data store
    private Map<String, User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        etEmail = findViewById(R.id.editEmailAddress);
        etUsername = findViewById(R.id.editUsername);
        etPassword = findViewById(R.id.editPassword);
        etFirstName = findViewById(R.id.editFirstName);
        etLastName = findViewById(R.id.editLastName);
        etMajor = findViewById(R.id.major);
        etClassLevel = findViewById(R.id.grade);
        btnRegister = findViewById(R.id.buttonNext);

        // Initialize dummy data store (in a real app, this would be passed/shared across activities)
        users = new HashMap<>();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        Button registerButton = findViewById(R.id.buttonNext);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("registrationSuccess", true);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void registerUser() {
        String email = etEmail.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String major = etMajor.getText().toString().trim();
        String classLevel = etClassLevel.getText().toString().trim();

        if (email.isEmpty() || username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || major.isEmpty() || classLevel == null) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (users.containsKey(email)) {
            Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
        } else {
            String id = UUID.randomUUID().toString();
            User newUser = new User(id, email, username, password, firstName, lastName, major, classLevel);
            users.put(email, newUser);
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
        }
    }
}

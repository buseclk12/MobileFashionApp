package com.example.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register  extends AppCompatActivity {
    Button myButton;
    TextView textView2;
    EditText mUsernameEditText;
    EditText mPasswordEditText;
    EditText mEmailEditText;
    DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout);
        Button myButton = findViewById(R.id.myButton);
        myButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral30));

        textView2 = findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mUsernameEditText = findViewById(R.id.editTextTextPersonName);
        mPasswordEditText = findViewById(R.id.editTextTextPassword);
        mEmailEditText = findViewById(R.id.editTextMail);

        mDatabaseHelper = new DatabaseHelper(this);
        Button registerButton = findViewById(R.id.myButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsernameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String email = mEmailEditText.getText().toString();

                boolean addUser = mDatabaseHelper.addUser(username, password, email);
                // Show a toast message to indicate success or failure
                if (addUser) {
                    Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

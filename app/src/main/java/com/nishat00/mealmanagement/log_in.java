package com.nishat00.mealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class log_in extends AppCompatActivity {

    Button loginButton,signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loginButton = findViewById(R.id.login);
        signUpButton = findViewById(R.id.signup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homePage = new Intent(log_in.this,homepage.class);
                startActivity(homePage);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(log_in.this,signup.class);
                startActivity(signUp);
            }
        });
    }
}
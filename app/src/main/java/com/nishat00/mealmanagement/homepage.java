package com.nishat00.mealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class homepage extends AppCompatActivity {

    ImageView backToLogInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        backToLogInButton = findViewById(R.id.backToLogIn);

        backToLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homePageToLogInPage = new Intent(homepage.this,log_in.class);
                startActivity(homePageToLogInPage);
            }
        });
    }
}
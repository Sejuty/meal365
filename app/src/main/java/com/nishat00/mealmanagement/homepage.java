package com.nishat00.mealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class homepage extends AppCompatActivity {

    ImageView backToLogInButton;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        backToLogInButton = findViewById(R.id.backToLogIn);
        fAuth=FirebaseAuth.getInstance();

        backToLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();

                Intent homePageToLogInPage = new Intent(homepage.this,log_in.class);
                startActivity(homePageToLogInPage);
            }
        });
    }
}
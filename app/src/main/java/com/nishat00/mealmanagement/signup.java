package com.nishat00.mealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signup extends AppCompatActivity {

    Button signUpButton,loginButtonSignUpPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUpButton = findViewById(R.id.signupButtonInSignUpPage);
        loginButtonSignUpPage = findViewById(R.id.loginButtonInSignUpPage);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goToSignUpPageToHomePage = new Intent(signup.this,homepage.class);
                startActivity(goToSignUpPageToHomePage);

            }
        });

        loginButtonSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLogInPageToHomePage = new Intent(signup.this,log_in.class);
                startActivity(goToLogInPageToHomePage);
            }
        });

    }
}
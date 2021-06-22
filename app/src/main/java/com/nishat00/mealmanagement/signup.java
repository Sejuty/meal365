package com.nishat00.mealmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    Button signUpButton,loginButtonSignUpPage;
    EditText userName,mEmail,mPassword,mConfirmPassword;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUpButton = findViewById(R.id.signupButtonInSignUpPage);
        loginButtonSignUpPage = findViewById(R.id.loginButtonInSignUpPage);
        userName = findViewById(R.id.userNameSignUp);
        mEmail =findViewById(R.id.email);
        mPassword =findViewById(R.id.passwordSignUp);
        mConfirmPassword=findViewById(R.id.confirmPassword);
        fAuth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        Intent goToSignUpPageToHomePage = new Intent(signup.this,homepage.class);
        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(goToSignUpPageToHomePage);
            finish();

        }

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String email = mEmail.getText().toString().trim();
                String password =mPassword.getText().toString().trim();
                String confirmPassword =mConfirmPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email is required!");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    mPassword.setError("Password is required!");
                    return ;

                }

                if(password.length()<6)
                {
                    mPassword.setError("The password should be more than 6 digits");
                    return;
                }
                if(!confirmPassword.equals(password))
                {
                    mConfirmPassword.setError("Password doesn't match");
                    return ;
                }
                progressBar.setVisibility(View.VISIBLE);
                //when we click the signup button
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful() )
                        {
                            Toast.makeText(signup.this, "User created", Toast.LENGTH_SHORT).show();
                            startActivity(goToSignUpPageToHomePage);
                        }
                        else
                        {
                            Toast.makeText(signup.this, "Error"+task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


            };
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
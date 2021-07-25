package com.nishat00.mealmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class log_in extends AppCompatActivity {

    Button loginButton,signUpButton;
    EditText mEmail,mPassword;
    FirebaseAuth fAuth;
    //ProgressBar progressBar;
    TextView forgetPasswordLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loginButton = findViewById(R.id.login);
        signUpButton = findViewById(R.id.signup);
        mEmail=findViewById(R.id.emailLogin);
        mPassword=findViewById(R.id.password);
        // progressBar =findViewById(R.id.progressBar2);
         fAuth=FirebaseAuth.getInstance();
         forgetPasswordLink =findViewById(R.id.forgotPassword);
        //signUpButton = findViewById(R.id.signup);

//        signUpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent logIntoSignUpPage = new Intent(log_in.this,signup.class);
//                startActivity(logIntoSignUpPage);
//            }
//        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progressBar.setVisibility(View.VISIBLE);

                Intent homePage = new Intent(log_in.this,homepage.class);

                String email = mEmail.getText().toString().trim();
                String password =mPassword.getText().toString().trim();

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


                //authenticate the user
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(log_in.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(homePage);
                            finish();

                        }
                        else
                        {
                            Toast.makeText(log_in.this, "Error"+task.getException().getMessage() , Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(log_in.this,signup.class);
                startActivity(signUp);
            }
        });
        forgetPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail =new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter Your Email To Receive Reset Link");
                passwordResetDialog.setView(resetMail);
                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mailReset= resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mailReset).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(log_in.this, "Reset mail has been sent", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull  Exception e) {
                                Toast.makeText(log_in.this, "Error Reset Link"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                passwordResetDialog.create().show();
            }
        });
    }
}
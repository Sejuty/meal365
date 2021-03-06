package com.nishat00.mealmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    Button signUpButton,loginButtonSignUpPage;
    EditText userName,mEmail,mPassword,mConfirmPassword,phoneNum;
    FirebaseAuth fAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUpButton = findViewById(R.id.signupButtonInSignUpPage);
        loginButtonSignUpPage = findViewById(R.id.loginButtonInSignUpPage);
        phoneNum=findViewById(R.id.phoneNum);
        userName = findViewById(R.id.userNameSignUp);
        mEmail =findViewById(R.id.proEmail);
        mPassword =findViewById(R.id.passwordSignUp);
        mConfirmPassword=findViewById(R.id.confirmPassword);
        fAuth = FirebaseAuth.getInstance();
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
                String phone=phoneNum.getText().toString().trim();
                String user= userName.getText().toString().trim();
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
                if(TextUtils.isEmpty(phone))
                {
                    phoneNum.setError("phone number required");
                    return;
                }
                if(phone.length()!=11)
                {
                    phoneNum.setError("phone number is not correct");
                    return;
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


                //when we click the signup button
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful() )
                        {
                            rootNode=FirebaseDatabase.getInstance();
                            reference=rootNode.getReference("User");
                            UserHelper helperClass=new UserHelper(user,email,phone);

                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(helperClass);
                            startActivity(new Intent(signup.this,homepage.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(signup.this, "Error"+task.getException().getMessage() , Toast.LENGTH_SHORT).show();

                        }
                    }
                });


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
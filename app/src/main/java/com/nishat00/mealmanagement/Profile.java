package com.nishat00.mealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    private Button logout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout=findViewById(R.id.logOut);
        firebaseAuth=FirebaseAuth.getInstance();
        SharedPreferences sharedPreferences= getSharedPreferences(log_in.PREFS_NAME,0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                editor.putBoolean("hasLoggedIn",false);
                editor.commit();
                startActivity(new Intent(Profile.this ,log_in.class));
            }
        });


    }
}
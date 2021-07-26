package com.nishat00.mealmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private Button logout;
    private FirebaseAuth firebaseAuth;
    private TextView proUserName,proFullName,proEmail,proPhone;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout=findViewById(R.id.logOut);
        proUserName=findViewById(R.id.userName);
        proFullName=findViewById(R.id.fullName);
        proEmail=findViewById(R.id.proEmail);
        proPhone =findViewById(R.id.proPhn);


        firebaseAuth=FirebaseAuth.getInstance();
        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference("User");
        SharedPreferences sharedPreferences= getSharedPreferences(log_in.PREFS_NAME,0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    proUserName.setText(ds.child("userName").getValue(String.class));
                    proFullName.setText(ds.child("userName").getValue(String.class));
                    proPhone.setText(ds.child("phone").getValue(String.class));
                    proEmail.setText(ds.child("email").getValue(String.class));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
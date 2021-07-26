package com.nishat00.mealmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class homepage extends AppCompatActivity implements View.OnClickListener {

    ImageView exitFromHome;
    Button homeToProfile;
    TextView toManagement;
    private AlertDialog.Builder alertFromHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        exitFromHome = findViewById(R.id.goBackFromHome);
        homeToProfile=findViewById(R.id.homepage_to_profile);
        toManagement = findViewById(R.id.noti_box);

        //will go to meal management
        toManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent management = new Intent(homepage.this,MealManagement.class);
                startActivity(management);
            }
        });



        exitFromHome.setOnClickListener(this);


        //by clicking the profile image we will goto my profile
      homeToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toProfile = new Intent(homepage.this,Profile.class);
                startActivity(toProfile);
            }
        });
    }

    @Override
    public void onClick(View v) {
        alertFromHome = new AlertDialog.Builder(homepage.this);
        //alertFromHome.setTitle(R.string.alert_title);
        alertFromHome.setMessage(R.string.alert_message);
        alertFromHome.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertFromHome.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertFromHome.create();
        alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.appYellow));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.appYellow));
            }
        });

        alertDialog.show();
        //ends dialog box
    }


}

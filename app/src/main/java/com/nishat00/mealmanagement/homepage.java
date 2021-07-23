package com.nishat00.mealmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class homepage extends AppCompatActivity implements View.OnClickListener {

    ImageView exitFromHome;
    private AlertDialog.Builder alertFromHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        exitFromHome = findViewById(R.id.goBackFromHome);

        exitFromHome.setOnClickListener(this);
    }

    //start dialog box
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

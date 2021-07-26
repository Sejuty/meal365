package com.nishat00.mealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=5000;

    TextView start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              SharedPreferences sharedPreference =getSharedPreferences(log_in.PREFS_NAME,0);
              boolean hasLoggedIn=sharedPreference.getBoolean("hasLoggedIn",false);
              if(hasLoggedIn)
              {
                  Intent intent =new Intent(MainActivity.this,homepage.class);
                  startActivity(intent);
                  finish();

              }
              else
              {
                  Intent intent =new Intent(MainActivity.this,log_in.class);
                  startActivity(intent);
                  finish();
              }


          }
      },SPLASH_TIME_OUT);
    }
}
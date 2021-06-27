package com.igc.lifeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {



    int SPLASH_SCREEN = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SharedPreferences sp = getSharedPreferences("Patient",MODE_PRIVATE);

        if(sp.contains("Email") && sp.contains("Password"))
        {
            startActivity(new Intent(MainActivity2.this,Patient_Dashborad_Activity.class));
            finish();
        }
        else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity2.this,FancyWalkThrough_Activity.class));
                    finish();
                }
            },SPLASH_SCREEN);
        }

    }
    /*@Override
    protected void onStart() {
        super.onStart();
        splach();
        SharedPreferences sp = getSharedPreferences("Patient",MODE_PRIVATE);

        if(sp.contains("Email") && sp.contains("Password"))
        {
            startActivity(new Intent(MainActivity2.this,Patient_Dashborad_Activity.class));
            finish();
        }
        else
        {
            startActivity(new Intent(MainActivity2.this,FancyWalkThrough_Activity.class));
            finish();
        }


    }*/

    public void splach()
    {

    }
}
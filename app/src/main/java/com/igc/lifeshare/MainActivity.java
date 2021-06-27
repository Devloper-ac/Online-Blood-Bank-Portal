package com.igc.lifeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OTP(View view) {
        startActivity(new Intent(this,GetOTP.class));
    }

    public void getGoogleSignIn(View view) {
        startActivity(new Intent(this, GoogleSignIn.class));
    }

    public void getEmail(View view) {
        startActivity(new Intent(this,Email_Password_SignIn.class));
    }
}
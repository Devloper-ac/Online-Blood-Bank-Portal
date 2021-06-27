package com.igc.lifeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OwnerSignup_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_signup_activity);
    }

    public void gotoEmail(View view) {
        //startActivity(new Intent(OwnerSignup_Activity.this,Email_Password_SignIn.class));
    }

    
}
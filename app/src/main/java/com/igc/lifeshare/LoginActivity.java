package com.igc.lifeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    String[] choice = { "Owner", "Patient"};
    String text;
    private FirebaseAuth fbAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        fbAuth = FirebaseAuth.getInstance();

        spinner = findViewById(R.id.spinner);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,choice);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
       spinner.setOnItemSelectedListener(this);
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();


    }




   /* @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser mf = fbAuth.getCurrentUser();
        if(mf !=null)
        {
            Toast.makeText(this, "User is Logged In..", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,OwnerDashboard_Activity.class));
            finish();
        }
        else
        {
            Toast.makeText(this, "No User Logged In..", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,LoginActivity.class));
            finish();
        }
    }*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
        SharedPreferences tt = getSharedPreferences("spinner",MODE_PRIVATE);
        SharedPreferences.Editor se = tt.edit();
        se.putString("text",text);
        se.commit();
        //Toast.makeText(this, "" + text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Nothing is Selected", Toast.LENGTH_SHORT).show();

    }

    public void Con(View view) {

        if (text.contains("Owner"))
        {
            Toast.makeText(this, "Owner Selected", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,OwnerLogin_Activity.class));

        }
        else
        {
            Toast.makeText(this, "Patient Selected", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,Patient_Login_Activity.class));
        }

    }

    /*@Override
    protected void onStart() {
        super.onStart();

        FirebaseUser mf = fbAuth.getCurrentUser();
        if(mf !=null)
        {
            Toast.makeText(this, "User is Logged In..", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,Main.class));
            finish();
        }
        else
        {
            Toast.makeText(this, "No User Logged In..", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(LoginActivity.this,FancyWalkThrough_Activity.class));
            //finish();


        }
    }*/

}
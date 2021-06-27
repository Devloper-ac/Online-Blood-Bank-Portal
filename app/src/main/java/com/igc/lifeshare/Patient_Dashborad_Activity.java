package com.igc.lifeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

public class Patient_Dashborad_Activity extends AppCompatActivity {

    NavigationView nav1;
    ActionBarDrawerToggle toggle1;
    DrawerLayout patientdrwlyt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__dashborad_activity);


        nav1=findViewById(R.id.nav1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        patientdrwlyt = findViewById(R.id.patientdrwlyt);
        toggle1 = new ActionBarDrawerToggle(this,patientdrwlyt,R.string.open,R.string.close);
        toggle1.syncState();
        patientdrwlyt.addDrawerListener(toggle1);

        View v = getLayoutInflater().inflate(R.layout.drawerheader,null);


        nav1.addHeaderView(v);
        nav1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.logoutpat:
                        out();
                        break;
                }

                return false;
            }
        });


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION} ,101);
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION} ,101);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle1.onOptionsItemSelected(item)) {
            return true;

        }

        switch (item.getItemId()) {
            case R.id.logoutpatient:
                startActivity(new Intent(Patient_Dashborad_Activity.this,LoginActivity.class));
                File deletePrefFile = new File("/data/data/com.igc.lifeshare/shared_prefs/Patient.xml");
                deletePrefFile.delete();
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void PatientProfileUP(View view) {
        startActivity(new Intent(Patient_Dashborad_Activity.this,Patient_ProfileUpdate_Activity.class));
    }

    public void FindBloodDonar(View view) {
        startActivity(new Intent(Patient_Dashborad_Activity.this,Patient_BloodGroupView_Activity.class));
    }

    public void PatientRequestBlood(View view) {
        startActivity(new Intent(Patient_Dashborad_Activity.this,Patient_RequertBlood_Activity.class));
    }


    public void PatienNearby(View view) {
        gotoUrl("https://lifeshareofficial.000webhostapp.com/");
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dd, menu);
        return true;
    }

    public void out()
    {
        startActivity(new Intent(Patient_Dashborad_Activity.this,LoginActivity.class));
        File deletePrefFile = new File("/data/data/com.igc.lifeshare/shared_prefs/Patient.xml");
        deletePrefFile.delete();
        finish();
    }
}
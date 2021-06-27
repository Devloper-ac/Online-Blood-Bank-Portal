package com.igc.lifeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

import java.io.File;

public class Main extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drwlyt;
    BottomNavigation btmnav;
    FragmentTransaction frTr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        nav=findViewById(R.id.nav);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drwlyt = findViewById(R.id.drwlyt);
        toggle = new ActionBarDrawerToggle(this,drwlyt,R.string.open,R.string.close);
        toggle.syncState();
        drwlyt.addDrawerListener(toggle);

        View v = getLayoutInflater().inflate(R.layout.drawerheader,null);
        
        nav.addHeaderView(v);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.logout:
                            singout();
                        break;

                }
                return false;
            }
        });






        /*btmnav=findViewById(R.id.btmnav);
        btmnav.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int i) {
                switch (i)
                {
                    case R.id.fragment_home:
                            frTr=getSupportFragmentManager().beginTransaction();
                            frTr.replace(R.id.frame_fragment_container,new Home_Activity());
                        break;
                    case R.id.fragment_call:
                            frTr=getSupportFragmentManager().beginTransaction();
                            frTr.replace(R.id.frame_fragment_container,new Call_Activity());
                        break;
                    case R.id.fragment_aboutus:
                            frTr=getSupportFragmentManager().beginTransaction();
                            frTr.replace(R.id.frame_fragment_container,new AboutUs_Activity());
                        break;
                }
                frTr.commit();
            }
        });*/
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ac, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;

        }

        switch (item.getItemId()) {
            case R.id.logoutowner:
                startActivity(new Intent(Main.this,LoginActivity.class));
                File deletePrefFile = new File("/data/data/com.igc.lifeshare/shared_prefs/File.xml");
                deletePrefFile.delete();
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void singout()
    {

        startActivity(new Intent(Main.this,LoginActivity.class));
        File deletePrefFile = new File("/data/data/com.igc.lifeshare/shared_prefs/File.xml");
        deletePrefFile.delete();
        finish();

    }

    public void AddPatient(View view) {
        startActivity(new Intent(Main.this,OwnerProfileUpdate_Activity.class));
    }

    public void ViewDonar(View view) {
        startActivity(new Intent(Main.this,Owner_BloodGroup_View_Activity.class));
    }

    public void BloodRequest(View view) {
        startActivity(new Intent(Main.this,Owner_BloodRequest_Activity.class));
    }

    public void NearbyPlace(View view) {
        gotoUrl("https://lifeshareofficial.000webhostapp.com/");
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}
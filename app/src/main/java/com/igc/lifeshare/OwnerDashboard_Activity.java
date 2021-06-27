package com.igc.lifeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

import java.security.acl.Owner;

public class OwnerDashboard_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_dashboard_activity);
        Toast.makeText(this, "Welcome Owner...", Toast.LENGTH_SHORT).show();

    }

    public void btnSin(View view) {

    }


}





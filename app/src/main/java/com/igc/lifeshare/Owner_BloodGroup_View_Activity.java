package com.igc.lifeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Owner_BloodGroup_View_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner__blood_group__view_activity);
    }

    public void OP(View view) {
        startActivity(new Intent(Owner_BloodGroup_View_Activity.this,Owner_OP_Activity.class));
    }

    public void ON(View view) {
        startActivity(new Intent(Owner_BloodGroup_View_Activity.this,Owner_ON_Activity.class));
    }

    public void AP(View view) {
        startActivity(new Intent(Owner_BloodGroup_View_Activity.this,Owner_AP_Activity.class));
    }

    public void AN(View view) {
        startActivity(new Intent(Owner_BloodGroup_View_Activity.this,Owner_AN_Activity.class));
    }

    public void BP(View view) {
        startActivity(new Intent(Owner_BloodGroup_View_Activity.this,Owner_BP_Activity.class));
    }

    public void BN(View view) {
        startActivity(new Intent(Owner_BloodGroup_View_Activity.this,Owner_BN_Activity.class));
    }

    public void ABP(View view) {
        startActivity(new Intent(Owner_BloodGroup_View_Activity.this,Owner_ABP_Activity.class));
    }

    public void ABN(View view) {
        startActivity(new Intent(Owner_BloodGroup_View_Activity.this,Owner_ABN_Activity.class));
    }
}
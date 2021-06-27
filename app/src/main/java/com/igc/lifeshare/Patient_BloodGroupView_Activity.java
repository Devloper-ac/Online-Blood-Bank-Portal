package com.igc.lifeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Patient_BloodGroupView_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__blood_group_view_activity);
    }

    public void P_OP(View view) {
        startActivity(new Intent(Patient_BloodGroupView_Activity.this,Patient_OP_Activity.class));
    }

    public void P_ON(View view) {
        startActivity(new Intent(Patient_BloodGroupView_Activity.this,Patient_ON_Activity.class));
    }

    public void P_AP(View view) {
        startActivity(new Intent(Patient_BloodGroupView_Activity.this,Patient_AP_Activity.class));
    }

    public void P_AN(View view) {
        startActivity(new Intent(Patient_BloodGroupView_Activity.this,Patient_AN_Activity.class));
    }

    public void P_BP(View view) {
        startActivity(new Intent(Patient_BloodGroupView_Activity.this,Patient_BP_Activity.class));
    }

    public void P_BN(View view) {
        startActivity(new Intent(Patient_BloodGroupView_Activity.this,Patient_BN_Activity.class));
    }

    public void P_ABP(View view) {
        startActivity(new Intent(Patient_BloodGroupView_Activity.this,Patient_ABP_Activity.class));
    }

    public void P_ABN(View view) {
        startActivity(new Intent(Patient_BloodGroupView_Activity.this,Patient_ABN_Activity.class));
    }
}
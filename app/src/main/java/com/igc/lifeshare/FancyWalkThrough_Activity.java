package com.igc.lifeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FancyWalkThrough_Activity extends FancyWalkthroughActivity {

   private FirebaseAuth fbAuth;
    FirebaseUser fbUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fancy_walk_through_activity);



        List<FancyWalkthroughCard> lst = new ArrayList<>();

        FancyWalkthroughCard c1 = new FancyWalkthroughCard("Blood Donation","“You don’t have to be somebody’s family member to donate blood.”- Anonymous, blood donation on birthday quotes",R.drawable.bll);
        FancyWalkthroughCard c2 = new FancyWalkthroughCard("Tracking Of Donar","In this Application we will gon to see the donar tracking ",R.drawable.bl);
        FancyWalkthroughCard c3 = new FancyWalkthroughCard("Need A Blood In Emerygency","There will be all the record show to patient that who need a blood",R.drawable.blll);

        lst.add(c1);
        lst.add(c2);
        lst.add(c3);

        setOnboardPages(lst);



    }

    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = fbAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(FancyWalkThrough_Activity.this,Patient_Dashborad_Activity.class));
            finish();
            reload();
        }
    }

    private void reload() { }*/



    @Override
    public void onFinishButtonPressed() {

        startActivity(new Intent(FancyWalkThrough_Activity.this,LoginActivity.class));
        finish();

    }


}
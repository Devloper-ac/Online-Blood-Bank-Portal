 package com.igc.lifeshare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;

 public class OwnerLogin_Activity extends AppCompatActivity {

    TextInputEditText txtOLEmail,txtOLPass;
     FirebaseAuth fbAuth;
     FirebaseUser fbUser;
     TextView txtshop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_login_activity);

        fbAuth = FirebaseAuth.getInstance();

        txtOLEmail = findViewById(R.id.txtOLEmail);
        txtOLPass = findViewById(R.id.txtOLPass);




    }

     /*@Override
     public void onStart() {
         super.onStart();
         // Check if user is signed in (non-null) and update UI accordingly.
         FirebaseUser currentUser = fbAuth.getCurrentUser();
         if(currentUser != null){
             reload();
         }
     }*/

    /* FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
         @Override
         public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
             FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
             if (firebaseUser != null) {
                 Intent intent = new Intent(OwnerLogin_Activity.this, FancyWalkThrough_Activity.class);
                 startActivity(intent);
                 finish();
             }
         }
     };*/

     //private void reload() { }

     public void loginInto(View view) {
         if(txtOLEmail.getText().toString().isEmpty() && txtOLPass.getText().toString().isEmpty())
         {
             txtOLEmail.setError("Please Enter Email");txtOLEmail.requestFocus();
             txtOLPass.setError("Please Enter txtOLPass");txtOLPass.requestFocus();
         }else if(txtOLEmail.getText().toString().isEmpty())
         {
             txtOLEmail.setError("Please Enter Email");txtOLEmail.requestFocus();
         }else if(txtOLPass.getText().toString().isEmpty())
         {
             txtOLPass.setError("Please Enter txtOLPass");txtOLEmail.requestFocus();
         }
         else
         {
             if(txtOLEmail.getText().toString().equals("Admin") && txtOLPass.getText().toString().equals("Admin"))
             {
                 startActivity(new Intent(OwnerLogin_Activity.this,Main.class));
                 /*SharedPreferences sp = getSharedPreferences("File",MODE_PRIVATE);
                 SharedPreferences.Editor se = sp.edit();
                 se.putString("Admin","Admin");
                 se.putString("Password","Admin");
                 se.commit();*/
                 finish();
             }
             else
             {
                 txtOLEmail.setError("Wrong Email");txtOLEmail.requestFocus();
                 txtOLPass.setError("Wrong Password");txtOLPass.requestFocus();

             }
         }

     }



     /*public void checkEmail()
     {
         String email = txtOLEmail.getText().toString().trim();
         fbAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
             @Override
             public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                 boolean check = !task.getResult().getSignInMethods().isEmpty();
                 if(!check)
                 {
                     txtOLEmail.setError("No Email Found...!");txtOLEmail.requestFocus();
                     //Toast.makeText(OwnerLogin_Activity.this, "No Email Found..", Toast.LENGTH_SHORT).show();
                 }
                 else
                 {

                 }
             }
         });
     }*/


     
 }
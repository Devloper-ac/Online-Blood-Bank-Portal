package com.igc.lifeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class Patient_Login_Activity extends AppCompatActivity {

    TextInputEditText txtPLEmail,txtPLPass;
    FirebaseAuth fbAth;
    FirebaseUser fbUsr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__login_activity);
        fbAth = FirebaseAuth.getInstance();
        txtPLEmail = findViewById(R.id.txtPLEmail);
        txtPLPass = findViewById(R.id.txtPLPass);

    }

    public void sinUpagee1(View view) {
        startActivity(new Intent(Patient_Login_Activity.this,Patient_Registration_Activity.class));
    }

    public void ResetEmailPPass(View view) {
        startActivity(new Intent(Patient_Login_Activity.this,Reset_EmailPass_Activity.class));
    }

    public void loginIntoP(View view) {
        if(txtPLEmail.getText().toString().isEmpty() && txtPLPass.getText().toString().isEmpty())
        {
            txtPLEmail.setError("Please Enter Email");
            txtPLEmail.requestFocus();
            txtPLPass.setError("Please Enter Password");
            txtPLPass.requestFocus();
        }else if(txtPLEmail.getText().toString().isEmpty())
        {
            txtPLEmail.setError("Please Enter Email");txtPLEmail.requestFocus();
        }else if(txtPLPass.getText().toString().isEmpty())
        {
            txtPLPass.setError("Please Enter Password");txtPLPass.requestFocus();
        }else
        {

            String email = txtPLEmail.getText().toString().trim();
            String pass = txtPLPass.getText().toString().trim();

            fbAth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    checkEmail();
                    if(task.isSuccessful())
                    {

                        fbUsr = FirebaseAuth.getInstance().getCurrentUser();
                        if(fbUsr.isEmailVerified())
                        {

                            Toast.makeText(Patient_Login_Activity.this, "User Login Sucessfully ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Patient_Login_Activity.this,Patient_Dashborad_Activity.class));
                            SharedPreferences sp = getSharedPreferences("Patient",MODE_PRIVATE);
                            SharedPreferences.Editor se = sp.edit();
                            se.putString("Email",txtPLEmail.getText().toString().trim());
                            se.putString("Password",txtPLPass.getText().toString().trim());
                            se.commit();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Patient_Login_Activity.this, "Please Verifiya Your Email We Have Send Email On Your Email address", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(Patient_Login_Activity.this, "User Login Field! Invalid Email OR Password..", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = fbAth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }*/

    FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                Intent intent = new Intent(Patient_Login_Activity.this, FancyWalkThrough_Activity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    //private void reload() { }

    public void checkEmail()
    {
        String email = txtPLEmail.getText().toString().trim();
        fbAth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                boolean check = task.getResult().getSignInMethods().isEmpty();
                if(!check)
                {
                    txtPLEmail.setError("No Email Found...!");txtPLEmail.requestFocus();
                    //Toast.makeText(OwnerLogin_Activity.this, "No Email Found..", Toast.LENGTH_SHORT).show();
                }
                else
                {

                }
            }
        });
    }

}
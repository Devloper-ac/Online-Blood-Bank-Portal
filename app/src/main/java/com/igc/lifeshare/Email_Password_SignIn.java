package com.igc.lifeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class Email_Password_SignIn extends AppCompatActivity {

    TextInputEditText txtEmail,txtOTP;
    FirebaseAuth fbAut;
    FirebaseUser fbUser;
    Button btnSub,btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email__password__sign_in_activity);

        txtEmail = findViewById(R.id.patientREmail);
        txtOTP = findViewById(R.id.patientRPass);

        fbAut = FirebaseAuth.getInstance();
        btnSub = findViewById(R.id.btnSub);
        btnGet = findViewById(R.id.btnGet);
        btnGet.setVisibility(View.GONE);
    }

    public void subMit(View view) {

        if(txtEmail.getText().toString().isEmpty())
        {
            txtEmail.setError("Please Enter Email Address");txtEmail.requestFocus();
        }
        else if(txtOTP.getText().toString().isEmpty())
        {
            txtOTP.setError("Please Enter Password");txtOTP.requestFocus();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString().trim()).matches())
        {
            txtEmail.setError("Please Enter Valid Email Address");txtEmail.requestFocus();
        }
        else
        {
            btnSub.setVisibility(view.GONE);
            btnGet.setVisibility(view.VISIBLE);
            String email = txtEmail.getText().toString().trim();
            String pass = txtOTP.getText().toString().trim();
            fbAut.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Email_Password_SignIn.this, "User Registration Succefully", Toast.LENGTH_SHORT).show();
                        fbUser = FirebaseAuth.getInstance().getCurrentUser();
                        fbUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(Email_Password_SignIn.this, "Verification Email Send", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(Email_Password_SignIn.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                }
            });
        }


    }

    public void getOTPHere(View view) {

        if(txtEmail.getText().toString().isEmpty())
        {
            txtEmail.setError("Please Enter Email Address");txtEmail.requestFocus();
        }
        else if(txtOTP.getText().toString().isEmpty())
        {
            txtOTP.setError("Please Enter Password");txtOTP.requestFocus();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString().trim()).matches())
        {
            txtEmail.setError("Please Enter Valid Email Address");txtEmail.requestFocus();
        }
        else
        {
            String email = txtEmail.getText().toString().trim();
            String pass = txtOTP.getText().toString().trim();

            fbAut.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    chk();
                    if(task.isSuccessful())
                    {
                        fbUser = FirebaseAuth.getInstance().getCurrentUser();
                        if(fbUser.isEmailVerified())
                        {
                            Toast.makeText(Email_Password_SignIn.this, "User Login Sucessfully ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Email_Password_SignIn.this,Information_Activity.class));
                        }
                        else
                        {
                            Toast.makeText(Email_Password_SignIn.this, "Please Verifiya Your Email We Have Send Email On Your Email address", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(Email_Password_SignIn.this, "User Login Field!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void chk()
    {

        String email = txtEmail.getText().toString().trim();
        fbAut.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                boolean check = !task.getResult().getSignInMethods().isEmpty();
                if(!check)
                {
                    Toast.makeText(Email_Password_SignIn.this, "Email Not Found", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Email_Password_SignIn.this, "Email already Present", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}

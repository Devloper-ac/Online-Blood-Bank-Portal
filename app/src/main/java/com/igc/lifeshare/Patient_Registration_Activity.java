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

public class Patient_Registration_Activity extends AppCompatActivity {

    TextInputEditText pEmail,pPass;
    FirebaseAuth fbAuthh;
    FirebaseUser fbUserr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__registration_activity);

        pEmail = findViewById(R.id.pEmail);
        pPass = findViewById(R.id.pPass);

        fbAuthh = FirebaseAuth.getInstance();


    }


    public void subMitP(View view) {

        if(pEmail.getText().toString().isEmpty())
        {
            pEmail.setError("Please Enter Email Address");pEmail.requestFocus();
        }
        else if(pPass.getText().toString().isEmpty())
        {
            pPass.setError("Please Enter Password");pPass.requestFocus();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(pEmail.getText().toString().trim()).matches())
        {
            pEmail.setError("Please Enter Valid Email Address");pEmail.requestFocus();
        }
        else
        {
            String email = pEmail.getText().toString().trim();
            String pass = pPass.getText().toString().trim();
            fbAuthh.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    checkEmail1();
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Patient_Registration_Activity.this, "User Registration Succefully", Toast.LENGTH_SHORT).show();
                        fbUserr = FirebaseAuth.getInstance().getCurrentUser();
                        fbUserr.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(Patient_Registration_Activity.this, "Verification Email Send", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Patient_Registration_Activity.this,Patient_Information_Activity.class));
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(Patient_Registration_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                }
            });
        }



    }


    public void checkEmail1()
    {
        String email = pEmail.getText().toString().trim();
        fbAuthh.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                boolean check = !task.getResult().getSignInMethods().isEmpty();
                if(!check)
                {
                    Toast.makeText(Patient_Registration_Activity.this, "Email Not Found", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Patient_Registration_Activity.this, "Email already Present", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
package com.igc.lifeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class Reset_EmailPass_Activity extends AppCompatActivity {

    TextInputEditText txtOReset;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset__email_pass_activity);

        mAuth = FirebaseAuth.getInstance();

        txtOReset = findViewById(R.id.txtOReset);
    }

    public void ResetEmailOPass(View view) {

        String email = txtOReset.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Reset_EmailPass_Activity.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Reset_EmailPass_Activity.this, "Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
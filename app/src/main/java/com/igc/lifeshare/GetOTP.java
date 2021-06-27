package com.igc.lifeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class GetOTP extends AppCompatActivity {

    EditText txtMobno;
    private FirebaseAuth fAuth;
    String OTP;
    PhoneAuthProvider.ForceResendingToken myforceResendingToken;
    LinearLayout lytOGETOTP,lytOVerify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getotp_activity);

        txtMobno = findViewById(R.id.txtMobno);

        fAuth = FirebaseAuth.getInstance();
        lytOGETOTP = findViewById(R.id.lytOGETOTP);
        lytOVerify = findViewById(R.id.lytOVerify);

        lytOVerify.setVisibility(View.GONE);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks myCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            myforceResendingToken = forceResendingToken;
            OTP = s;
            Toast.makeText(GetOTP.this, "OTP"+OTP, Toast.LENGTH_SHORT).show();
        }
    };

    public void getOTP(View view) {

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(fAuth)
                .setPhoneNumber(txtMobno.getText().toString().trim())
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(myCallback)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


            /*PhoneAuthProvider.verifyPhoneNumber(PhoneAuthOptions.newBuilder(fAuth)
                    .setPhoneNumber(txtMobno.getText().toString().trim())
                    .setActivity(this)
                    .setCallbacks(myCallback)
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .build());
            Toast.makeText(this, "OTP Send", Toast.LENGTH_SHORT).show();
            lytOGETOTP.setVisibility(view.GONE);
            lytOVerify.setVisibility(view.VISIBLE);*/

    }

    public void verifyOTP(View view) {
    }
}
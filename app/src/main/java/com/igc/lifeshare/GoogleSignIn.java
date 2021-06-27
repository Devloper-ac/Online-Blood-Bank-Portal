package com.igc.lifeshare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleSignIn extends AppCompatActivity {

    FirebaseAuth fbAuth;
    GoogleSignInClient gsclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlesignin_activity);

        fbAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        gsclient = com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(GoogleSignIn.this,gso);
        findViewById(R.id.btnOGoogle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = gsclient.getSignInIntent();
                startActivityForResult(i,111);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 111 && data!=null)
        {
            Task<GoogleSignInAccount> taskAcc = com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent(data);
            GoogleSignInAccount acc = taskAcc.getResult();
            //Task<GoogleSignInAccount> taskAcc = GoogleSignIn.getSignedInAccountFromIntent(data);
            //GoogleSignInAccount acc = taskAcc.getResult();
            AuthCredential credential = GoogleAuthProvider.getCredential(acc.getIdToken(),null);
            fbAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
                        Toast.makeText(GoogleSignIn.this, "Login Success\n"+fbUser.getDisplayName(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(GoogleSignIn.this,OwnerInfo_Activity.class));
                    }
                    else
                    {
                        Toast.makeText(GoogleSignIn.this, "Login Field", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}
package com.example.barbers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {  //if we dont have a user ->go Login
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//            setVisible(false);
//            showProgress();
            if (FirebaseAuth.getInstance().getCurrentUser() == null){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        }
    };

    // that say that if we are logged in go to mainActivity, else go to LoginActivity!!!!
    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.b_log_out).setOnClickListener(b-> logOut());
        Intent intent = getIntent();
        FirebaseUser user = (FirebaseUser) intent.getSerializableExtra("key_user");

    }


    //METHODS
    private void logOut(){
        FirebaseAuth.getInstance().signOut();
    }

    ProgressDialog progressDialog;
    private void showProgress(){
        if (progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Logging you in");
            progressDialog.setMessage("please wait...");
        }
        progressDialog.show();
    }


}




package com.example.barbers;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barbers.fragments.LoginFragment;
import com.example.barbers.fragments.SignUp;

public class LoginActivity extends AppCompatActivity {

    private TextView signIn;
    private TextView signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn = findViewById(R.id.sign_in);
        signUp = findViewById(R.id.signupforfree);

        signIn.setOnClickListener(v->{
            getSupportFragmentManager().beginTransaction().replace(R.id.cl_entry,new LoginFragment()).commit();

        });

        signUp.setOnClickListener(v->{
            getSupportFragmentManager().beginTransaction().replace(R.id.cl_entry,new SignUp()).commit();
        });


    }


}

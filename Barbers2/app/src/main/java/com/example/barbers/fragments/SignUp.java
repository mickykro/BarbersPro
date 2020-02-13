package com.example.barbers.fragments;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.barbers.MainActivity;
import com.example.barbers.R;
import com.example.barbers.java.Barber;
import com.example.barbers.java.Client;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Pattern;

//import androidx.fragment.app.FragmentManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment {

    //properties
    private EditText etMail;
    private EditText etPass;
    private EditText etUsername;
    private EditText etType;
    private EditText etFullName;
    private EditText etPhone;
    private TextView btnCreate;
    private TextView sBack;
    FragmentManager fm;
    Context mContext;

    OnSuccessListener<AuthResult> mSuccessListener = new OnSuccessListener<AuthResult>() {
        @Override
        public void onSuccess(AuthResult authResult) {
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            //go to main activity
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();

        }
    };


    OnSuccessListener<AuthResult> registerListener = new OnSuccessListener<AuthResult>() {
        @Override
        public void onSuccess(AuthResult authResult) {
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            //go to main activity
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);

            registerType(getType().toString());
            getActivity().finish();

        }
    };

    OnFailureListener mFailureListener = new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            showError(e);
        }
    };


    public SignUp() {
        // Required empty public constructor
    }

    public static SignUp newInstance(String type) {

        Bundle args = new Bundle();

        SignUp fragment = new SignUp();
        args.putString("type",type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.activity_signup, container, false);
        View v = inflater.inflate(R.layout.activity_signup, container, false);


        btnCreate = v.findViewById(R.id.btn_sign_up);
        etMail = v.findViewById(R.id.mail);
        etPass = v.findViewById(R.id.pswrd);
        etUsername = v.findViewById(R.id.usrusr);
        etType = v.findViewById(R.id.type);
        etFullName = v.findViewById(R.id.fname);
        etPhone = v.findViewById(R.id.phone);
        sBack = v.findViewById(R.id.go_to_sin);

//        FragmentManager fm = getFragmentManager();
//        fm = getFragmentManager();
        mContext = getContext();
        fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();

        sBack.setOnClickListener(b->{
            fm.beginTransaction().replace(R.id.cl_login, new LoginFragment()).commit();
        });


        btnCreate.setOnClickListener(b-> register());

        return v;
    }




    //METHODS
    private void login(){
        String email = getEmailforRegister();
        String pass = getPass();

        if (email==null || pass ==null){
            return;
        }

        showProgress();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)
                .addOnSuccessListener(mSuccessListener)
                .addOnFailureListener(mFailureListener);
    }
    private void register() {
        String email = getEmailforRegister();
        String pass = getPass();
        String fullName = getFullName();
        String phone = getPhone();
        String type = getType();
        String userName = getUserName();

        if (email == null || pass == null || fullName == null || phone == null || type == null || userName == null) {
            return;
        }

        showProgress();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener(registerListener)
                .addOnFailureListener(mFailureListener);

    }

    //TODO: open 2 classes: Barber and Clients and put them as a param here.
    private void registerType(String type) {

        if (type.equals("barber")) {
            Barber barber = new Barber(FirebaseAuth.getInstance().getCurrentUser().getUid(), getFullName(),getPass(), getPhone(), getEmailforRegister());
            String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child("barbers").child(uID);
            ref.setValue(barber);
        }
        else {
            Client client = new Client(FirebaseAuth.getInstance().getCurrentUser().getUid(), getFullName(),getPass(), getPhone(), getEmailforRegister());
            String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child("clients").child(uID);
            ref.setValue(client);
        }
    }


    private String getEmailforRegister() {
        String email = etMail.getText().toString();
        Pattern emailAddressPattern = Patterns.EMAIL_ADDRESS;

        boolean validEmail = emailAddressPattern.matcher(email).matches();
        if (!validEmail) {
            etMail.setError("Not valid!");
            return null;
        }

        return email;
    }

    private String getUserName() {
        String userName = etUsername.getText().toString();

        if (userName.length() < 4 ) {
            etUsername.setError("Not valid! must contain at least 4 characters");
            return null;
        }

        return userName;
    }

    public String getFullName() {
        String fullName = etFullName.getText().toString();

        if (fullName.length() < 4) {
            etFullName.setError("Too short");
            return null;
        }

        return fullName;
    }

    private String getPhone() {
        String phone = etPhone.getText().toString();
        Pattern phonePattern = Patterns.PHONE;

        boolean validPhone = phonePattern.matcher(phone).matches();
        if (!validPhone) {
            etPhone.setError("Not valid!");
            return null;
        }

        return phone;
    }

    private String getType() {
        String type = etType.getText().toString();
        if (type.equalsIgnoreCase("Barber") || type.equalsIgnoreCase("Client")) {
            System.out.println(type);
            return type;
        }else {
            etType.setError("Not valid!");
            return null;
        }
    }



    private String getPass() {

        String pass = etPass.getText().toString();

        if (pass.length() < 6) {
            etPass.setError("Must at least 6");
            return null;
        }
        return pass;
    }

    private void showError(Exception e) {
        new AlertDialog.Builder(getContext()).setTitle("Error").setMessage(e.getLocalizedMessage())
                .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

    }

    ProgressDialog progressDialog;
    private void showProgress(){
        if (progressDialog == null){
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Logging you in");
            progressDialog.setMessage("please wait...");
        }
        progressDialog.show();
    }
}

package com.example.barbers.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.barbers.R;
import com.example.barbers.java.Barber;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailUpdate extends Fragment {

    DatabaseReference ref;
    Button btn_update_name;
    EditText fname_update;
    Button btn_update_phone;
    EditText phone_update;
    Button btn_update_mail;
    EditText mail_update;
    Button btn_update_type;
    EditText type_update;
    Button btn_update_usernme;
    EditText username_update;


    public DetailUpdate() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.detail_update_fragment, container, false);
        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference().child("users").child("barbers").child(fUser.getUid());
        System.out.println(fUser.getUid()+"  user id");
            btn_update_name = v.findViewById(R.id.btn_update_name);
            fname_update = v.findViewById(R.id.fname_update);
            btn_update_phone = v.findViewById(R.id.btn_update_phone);
            phone_update = v.findViewById(R.id.phone_update);
            btn_update_mail = v.findViewById(R.id.btn_update_email);
            mail_update = v.findViewById(R.id.mail_update);
            btn_update_type = v.findViewById(R.id.btn_update_type);
            type_update= v.findViewById(R.id.type_update);
            btn_update_usernme = v.findViewById(R.id.btn_update_username);
            username_update= v.findViewById(R.id.username_update);


        btn_update_name.setOnClickListener(b-> {
            System.out.println("name is changed");
            updateDetail(fUser,btn_update_name);
        });





        return v;
    }

    private void updateDetail(FirebaseUser fUser, Button b) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Barber brb = dataSnapshot.getValue(Barber.class);
                String changeId = b.getResources().getResourceName(b.getId());
                switch(changeId.substring(changeId.indexOf("e_")+2)){

                    case "name":
                        assert brb != null;
                        Barber UserBarber = new Barber(fUser.getUid(),fname_update.getText().toString(),brb.getPassword(),brb.getPhone(),brb.getEmail());
                        System.out.println("name is changed in case");
                        ref.setValue(UserBarber);
                        break;
                        default: System.out.println(changeId.substring(changeId.indexOf("e_")));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

package com.example.barbers.fragments;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.barbers.R;
import com.example.barbers.java.Barber;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */

public class BarberHomeFragment extends Fragment {

    ImageButton profile;
    DatabaseReference ref;
    TextView tv_profile_name;
    Button schedule;
    FragmentManager fm;


    //on create view we init the recycler to the container with our adapter.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_barber_home, container, false);

        schedule = root.findViewById(R.id.b_schedule);

        schedule.setOnClickListener(b->{
            NavController navController = Navigation.findNavController(root);
            navController.navigate(R.id.action_barberHomeFragment_to_queuesFragment);
        });

        root.findViewById(R.id.btn_edit_details).setOnClickListener(v -> {
            NavController nv = Navigation.findNavController(root);
            nv.navigate(R.id.action_barberHomeFragment_to_detailUpdate);
        });



        tv_profile_name = root.findViewById(R.id.tv_profile_name);
        readFromDB("name");

        /**
         * Update details by creating a new barber that will have a builder
//         */
//        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        ref = FirebaseDatabase.getInstance().getReference().child("users").child("barbers").child(fUser.getUid());
//        System.out.println(fUser.getUid());
//        ref.setValue(new Barber(fUser.getUid(),"changed name dksjbvfvibv","123456789","0545855552","mickykro@gmail.com"));
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Barber barber = dataSnapshot.getValue(Barber.class);
//                barber.setFullName("the name was changed");
//
//
//                System.out.println(barber.getBarberID()+"here here");
//                System.out.println(barber.getFullName());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//


        return root;
    }

    private void readFromDB(String key) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child("barbers").child(user.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Barber barber = dataSnapshot.getValue(Barber.class);
                     tv_profile_name.setText(barber.getFullName());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profile = view.findViewById(R.id.ib_barber_profile_picture);

        view.findViewById(R.id.btn_edit_details).setOnClickListener(b->{
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_barberHomeFragment_to_detailUpdate);
        });

        profile.setOnClickListener(v -> {
            picImage();
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                        profile.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void picImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }


}


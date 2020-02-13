package com.example.barbers.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbers.R;
import com.example.barbers.java.Barber;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarberShopDetails extends Fragment {

     DatabaseReference sampleRef;
     FirebaseRecyclerAdapter<Barber, BarberViewHolder> adapter;




    public BarberShopDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_barber_home, container, false);

        sampleRef = FirebaseDatabase.getInstance().getReference().child("users").child("barbers");
        sampleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return v;
    }

    public static class BarberViewHolder extends RecyclerView.ViewHolder {

        public BarberViewHolder(@NonNull View itemView) {
            super(itemView);
            TextView tv_barber_name;

        }
    }

        private void AdapterSetter(){
            FirebaseRecyclerOptions options = new FirebaseRecyclerOptions
                    .Builder<Barber>().
                    setQuery(sampleRef,Barber.class)
                    .build();



        }

    }



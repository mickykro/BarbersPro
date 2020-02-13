package com.example.barbers.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbers.R;
import com.example.barbers.java.Barber;
import com.example.barbers.recycle.ClientHomeAdapter;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CostumerHomeFragment extends Fragment {

    private TextView fullName;
    private TextView fullNameByRegister;
    DatabaseReference ref;


    public CostumerHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_costumer_home, container, false);

        fullName = root.findViewById(R.id.tv_profile_name);
        fullNameByRegister = root.findViewById(R.id.fname);

//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

        final RecyclerView rvForClients = root.findViewById(R.id.recycler_view_client_home);

        List<Barber> barberHomeFragments = new ArrayList<>();




//        ref = FirebaseDatabase.getInstance().getReference().child("users").child("barbers");
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                System.out.println(dataSnapshot);
//                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//
//                for (DataSnapshot child : children) {
//                    Barber value = child.getValue(Barber.class);
//                    barberHomeFragments.add(value);
//                }
//
//                // recycler -> list
//           }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        ClientHomeAdapter adapter = new ClientHomeAdapter(barberHomeFragments, getLayoutInflater());
        rvForClients.setLayoutManager(new LinearLayoutManager(getContext()));
        rvForClients.setAdapter(adapter);




        return root;
    }

}

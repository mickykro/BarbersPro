package com.example.barbers.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.barbers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarberForClientsRecyleFragment extends Fragment {


    public BarberForClientsRecyleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_barber_for_clients_recyle, container, false);
    }

}

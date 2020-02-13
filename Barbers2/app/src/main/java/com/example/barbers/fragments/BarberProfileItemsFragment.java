package com.example.barbers.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.barbers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarberProfileItemsFragment extends Fragment {

    private ImageView arrowNext;
    private ImageView arrowBack;
    private ImageView product;
    int i = -1;
    private int [] products = {R.drawable.shampoo,R.drawable.wax,R.drawable.barber_img};

    public BarberProfileItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.barber_profile_items, container, false);

        arrowNext = v.findViewById(R.id.iv_arrow_next);
        arrowBack = v.findViewById(R.id.iv_arrow_back);
        product = v.findViewById(R.id.iv_single_product);


        arrowNext.setOnClickListener(b->{
            i--;
            if (i<0) i = products.length-1;
            product.setImageResource(products[i]);
        });

        arrowBack.setOnClickListener(b->{
            i++;
            if (i>=products.length) i = 0;
            product.setImageResource(products[i]);
        });


        return v;
    }

}

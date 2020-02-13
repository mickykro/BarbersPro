package com.example.barbers.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbers.R;
import com.example.barbers.java.Barber;

import java.util.List;

public class ClientHomeAdapter extends RecyclerView.Adapter<ClientHomeAdapter.ClientHomeViewHolder> {

    //properties
    List<Barber> barbersOnline;
    LayoutInflater inflater; //takes xml ->Views

    public ClientHomeAdapter(List<Barber> barbersOnline, LayoutInflater inflater) {
        this.barbersOnline = barbersOnline;
        this.inflater = inflater;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    @NonNull
    @Override
    public ClientHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.fragment_barber_home, parent,false);
        return new ClientHomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientHomeViewHolder holder, int position) {
        Barber b = barbersOnline.get(position);

    }

    @Override
    public int getItemCount() {
        return barbersOnline.size();
    }

    public static class ClientHomeViewHolder extends RecyclerView.ViewHolder {

        public ClientHomeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

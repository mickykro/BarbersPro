package com.example.barbers.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbers.R;

import java.util.List;

public class BarberHomeAdapter extends RecyclerView.Adapter<BarberHomeAdapter.BarberHomeViewHolder>  {

    //properties
    List<BarbersProfile> barbersProfiles;
    LayoutInflater inflater; //takes xml ->Views



    //constructor that take list of barber profiles (soon JSON) and the xml(inflater)
    public BarberHomeAdapter(List<BarbersProfile> barbersProfiles, LayoutInflater inflater) {
        this.barbersProfiles = barbersProfiles;
        this.inflater = inflater;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }


    @NonNull
    @Override
    public BarberHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.barber_profile_items, parent,false);
        return new BarberHomeViewHolder(v);
    }


    //the actions that happened on the BarberHomeHolder
    @Override
    public void onBindViewHolder(@NonNull BarberHomeViewHolder holder, int position) {
        BarbersProfile b = barbersProfiles.get(position);
        holder.tvAboutUsTitle.setText(b.getTvAboutUsTitle());
        holder.tvAboutUsinfo.setText(b.getTvAboutUsinfo());
        holder.tvProductsTitle.setText(b.getTvProductsTitle());
        holder.tvBioTitle.setText(b.getTvBioTitle());
        holder.tvBioInfo.setText(b.getTvBioInfo());

//        Picasso.get().load(s.getArtworkUrl100()).placeholder(R.drawable.ic_image_placeholder).into(holder.imageArt);

//        //respond to onclick:
//        holder.itemView.setOnClickListener(v->{
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(s.getArtistUrl()));
//
//            //startActivity?
//            holder.itemView.getContext().startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return barbersProfiles.size();   //
    }



    /*
     * firs we want an inner class(obj for profile items
     */

    //the view holder class -> hold references to vies in the item view.
    public static class BarberHomeViewHolder extends RecyclerView.ViewHolder{

        TextView tvAboutUsTitle;
        TextView tvAboutUsinfo;
        TextView tvProductsTitle;
        TextView tvBioTitle;
        TextView tvBioInfo;


        public BarberHomeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAboutUsTitle = itemView.findViewById(R.id.tv_about_us_title);
            tvAboutUsinfo = itemView.findViewById(R.id.tv_about_us_info);
            tvProductsTitle = itemView.findViewById(R.id.tv_products_title);
            tvBioInfo = itemView.findViewById(R.id.tv_details_info);
            tvBioTitle = itemView.findViewById(R.id.tv_details_title);
        }
    }
}

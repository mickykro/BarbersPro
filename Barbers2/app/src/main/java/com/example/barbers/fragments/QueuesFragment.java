package com.example.barbers.fragments;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.barbers.Constants;
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
public class QueuesFragment extends Fragment {

    private static int count = 0;
    private static int i = 0;
    Button Constant;
    int ConstantId;
    int j;
    String temp_hour;
    Button back;
    public QueuesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_queues, container, false);

        back= v.findViewById(R.id.btn_back);
        back.setOnClickListener(b->{

            /**
             * until we find out how to make it stay booked
             */
            if(QueuesFragment.count==0) {
                NavController nv = Navigation.findNavController(v);
                nv.navigate(R.id.action_queuesFragment_to_barberHomeFragment);
            }else {
                Toast.makeText(getContext(), "learn how to save queues!", Toast.LENGTH_SHORT).show();
            }
        });

        int[] sunday_buttons = {R.id.sunday_btn_700,R.id.sunday_btn_730, R.id.sunday_btn_800, R.id.sunday_btn_830, R.id.sunday_btn_900, R.id.sunday_btn_930, R.id.sunday_btn_1000, R.id.sunday_btn_1030, R.id.sunday_btn_11100};



        for (i=0; i < Constants.BUTTONS.length; i++) {
            Button button = v.findViewById(Constants.BUTTONS[i]);
            j=i;
            button.setOnClickListener(b -> {
                if (count == 0) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    String day = button.getResources().getResourceName(button.getId());
                    builder.setTitle("Appointment is booked on " + day.substring(day.indexOf("/") + 1, day.indexOf("_")) + " at " + button.getText().toString()).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //TODO: change hour to name and fancy button
                            count++;
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child("barbers").child(user.getUid());
                            System.out.println("count "+count);
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    button.setBackgroundResource(R.drawable.queues_btn_clicked);
                                    button.setTextSize(8f);
                                    button.setTextColor(Color.parseColor("#FFFFFF"));

                                    Barber barber = dataSnapshot.getValue(Barber.class);
                                    temp_hour = button.getText().toString();

                                    button.setText(barber.getFullName());

//                                    ConstantId=button.getId();
                                    System.out.println(button.getId());
//                                    Constant= button;
                                    System.out.println("count "+count);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dialog.dismiss();
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            count = 0;
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }

                else {

//                    button.setOnClickListener(c->{

                        System.out.println("count " + count);
                        if (button.getBackground().getConstantState()==getResources().getDrawable(R.drawable.queues_btn_clicked).getConstantState()){

                            AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext()).setTitle("Are you sure you want to cancel?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            System.out.println("yes was clicked");
                                            System.out.println(button.getId());
                                            button.setBackgroundResource(R.drawable.queues_btn_not_clicked);
                                            if (j < Constants.HOURS.length) {
                                                String hour = Constants.HOURS[j];
                                                button.setText(temp_hour);
                                                button.setTextSize(12f);
                                                button.setTextColor(Color.parseColor("#C8FFFDD0"));
                                                System.out.println("sunday j " + j);
                                                System.out.println(Constants.HOURS.length);
                                                System.out.println(Constants.HOURS.length % j);
                                                count = 0;

                                            } else {
                                                button.setBackgroundResource(R.drawable.queues_btn_not_clicked);
                                                String hour = Constants.HOURS[j % Constants.HOURS.length];
                                                button.setText(temp_hour);
                                                button.setTextSize(12f);
                                                button.setTextColor(Color.parseColor("#C8FFFDD0"));
                                                System.out.println("monday j" + j);
                                                System.out.println(Constants.HOURS.length);
                                                System.out.println(j % Constants.HOURS.length);
                                                dialog.dismiss();
                                                count = 0;
                                            }
                                            System.out.println("count " + count);
                                        }
                                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            //test
                                            count = 1;
                                            dialog.dismiss();
                                        }
                                    });
                            builder2.show();
                        } else  Toast.makeText(getContext(), "only 1 appointment at a time", Toast.LENGTH_SHORT).show();

//                    });

                }
            });

        }

        return v;
    }

}
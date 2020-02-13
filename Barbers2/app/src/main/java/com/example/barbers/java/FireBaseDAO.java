package com.example.barbers.java;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBaseDAO {
    //singleton: share a single object across the entire app:
    private FireBaseDAO(){} //hide the constructor


    public static FireBaseDAO shared = new FireBaseDAO();

    public void saveUser(User user){
        DatabaseReference newUserRef = FirebaseDatabase.getInstance().getReference("users").push();
        user.setName(newUserRef.getKey());
    }

    public void readUsers(UserListener listener){
        ArrayList<User> users = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    users.add(child.getValue(User.class));
                }
                listener.userArrived(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public interface  UserListener{
        void userArrived(List<User> users);
    }

}

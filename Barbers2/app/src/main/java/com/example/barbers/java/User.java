package com.example.barbers.java;


//FireBase database rules for costom objects:
public class User {

    private String name;
    private String uid;

    //required empty constructor
    public User() {

    }

    public User(String name, String uid) {
        this.name = name;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
}

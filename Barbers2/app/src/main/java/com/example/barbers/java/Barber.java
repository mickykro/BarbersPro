package com.example.barbers.java;

public class Barber {
   private String barberID;
   private String fullName;
   private String password;
   private String phone;
   private String email;


    public Barber() {
    }



    public Barber(String barberID, String password, String email) {
        this.barberID = barberID;
        this.password = password;
        this.email = email;
    }

    public Barber(String barberID, String fullName, String password, String phone, String email) {
        this.barberID = barberID;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public static  class Builder{
        private String barberID;
        private String fullName;
        private String password;
        private String phone;
        private String email;

        public Builder() {
        }

        public Builder setBuilderBarberID(String barberID) {
            this.barberID = barberID;
            return this;
        }

        public Builder setBuilderFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setBuilderPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setBuilderPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setBuilderEmail(String email) {
            this.email = email;
            return this;
        }

        public Barber build() {
            return new Barber(barberID,fullName,password,phone,email);
        }
    }



    public void setBarberID(String barberID) {
        this.barberID = barberID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBarberID() {
        return barberID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Barber{" +
                "barberID='" + barberID + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}

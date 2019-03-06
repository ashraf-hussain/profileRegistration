package project.profile.profileList;

import android.util.Log;

import java.util.Collection;

public class UserProfileModel  {

    public String name;
    public String gender;
    public String userDob;
    public String email;
    public String username;
    public String country;
    public String postalAddress;

    public String getName() {
//        Log.d("check", name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        //Log.d("check", gender);
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserDob() {
        return userDob;
    }

    public void setUserDob(String userDob) {
        this.userDob = userDob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }


}

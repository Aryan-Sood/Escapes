package com.abhijeet.travel_saathi.chat_app.model;

import com.google.firebase.Timestamp;

public class UserModel {
    private String username;
    private Timestamp createdTimestamp;
    private String userId;
    private String fcmToken;

    private String email;
    private String age, bio, occupation, gender;

    public UserModel() {
    }

    public UserModel( String email,String username, Timestamp createdTimestamp,String userId, String age, String bio, String occupation, String gender) {
        this.username = username;
        this.createdTimestamp = createdTimestamp;
        this.userId = userId;
        this.age = age;
        this.bio = bio;
        this.occupation = occupation;
        this.email = email;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

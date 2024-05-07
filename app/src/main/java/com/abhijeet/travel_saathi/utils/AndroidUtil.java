package com.abhijeet.travel_saathi.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.abhijeet.travel_saathi.models.UserModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class AndroidUtil {

    public static  void showToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static void passUserModelAsIntent(Intent intent, UserModel model){
        intent.putExtra("username",model.getUsername());
        intent.putExtra("email", model.getEmail());
        intent.putExtra("bio", model.getBio());
        intent.putExtra("occupation", model.getOccupation());
        intent.putExtra("age", model.getAge());
        intent.putExtra("userId",model.getUserId());
        intent.putExtra("gender", model.getGender());
        intent.putExtra("fcmToken",model.getFcmToken());

    }

    public static UserModel getUserModelFromIntent(Intent intent){
        UserModel userModel = new UserModel();
        userModel.setUsername(intent.getStringExtra("username"));
        userModel.setAge(intent.getStringExtra("age"));
        userModel.setEmail(intent.getStringExtra("email"));
        userModel.setBio(intent.getStringExtra("bio"));
        userModel.setOccupation(intent.getStringExtra("occupation"));
        userModel.setUserId(intent.getStringExtra("userId"));
        userModel.setGender(intent.getStringExtra("gender"));
        userModel.setFcmToken(intent.getStringExtra("fcmToken"));
        return userModel;
    }

    public static void setProfilePic(Context context, Uri imageUri, ImageView imageView){
        Glide.with(context).load(imageUri).apply(RequestOptions.circleCropTransform()).into(imageView);
    }
}
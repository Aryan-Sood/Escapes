package com.abhijeet.travel_saathi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class LoginSignup extends AppCompatActivity {
    Integer height;

    ImageView loginBackImage;

    Animation loginHideAnimation;

    ImageView upButton, downButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

//        loginBackImage = findViewById(R.id.login_background_image);
//        upButton = findViewById(R.id.up_arrow);


//        getScreenHeight();
//
//        loginBackImage.getLayoutParams().height=height/2;
//        loginBackImage.requestLayout();

//        upButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loginHideAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.login_hide_animation);
//                loginBackImage.startAnimation(loginHideAnimation);
//            }
//        });

    }


    public void getScreenHeight(){
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        height = metrics.heightPixels;
    }
}
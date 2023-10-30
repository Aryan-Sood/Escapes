package com.example.travel_saathi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView earthImage;

    Animation rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        earthImage = findViewById(R.id.earthImage);

        rotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.earth_rotation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                earthImage.startAnimation(rotate);
            }
        },1000);


    }
}
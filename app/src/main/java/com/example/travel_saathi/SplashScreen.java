package com.example.travel_saathi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    ImageView earthImage, travelImage;
    ConstraintLayout constraintLayout;

    Button button;
    TextView appName, bridgeDescription;
    Animation forAppNameStart,forAppNameEnd, bridgeDescStart, travelImageStart, travelImageEnd;
    private float currentRotationAngle = 0;

    Interpolator interpolator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        earthImage = findViewById(R.id.earthImage);
        button = findViewById(R.id.button);
        appName = findViewById(R.id.appName);
        constraintLayout = findViewById(R.id.constraintLayoutMain);
        bridgeDescription = findViewById(R.id.bridgeDescription);
        travelImage = findViewById(R.id.travelImage);

        appName.setVisibility(View.INVISIBLE);
        bridgeDescription.setVisibility(View.INVISIBLE);

        Handler handler= new Handler();

//        interpolator = new DecelerateInterpolator();

        handler.postDelayed(new Runnable() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void run() {
                rotateEarth();
                initializeAppNameStartAnimation();
                changeBackground1();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changeBackground2();
                        rotateEarth();
                        initializeAppNameEndAnimation();
                        initializeBridgeTextStartAnimation();
                        initializeTravelImageStart();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                rotateEarth();
                                initializeTravelImageEnd();
                                initializeBridgeTextEnd();
                            }
                        },3000);

                    }
                },4000);
            }
        },1000);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateEarth();
            }
        });


    }


    public void rotateEarth(){
        float newRotationAngle = currentRotationAngle - 180;
        currentRotationAngle = currentRotationAngle -180;
        earthImage.animate().setDuration(1000).rotation(newRotationAngle);
    }


    public void initializeAppNameStartAnimation(){
        forAppNameStart = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.app_name_start_animation);
        appName.startAnimation(forAppNameStart);
        appName.setVisibility(View.VISIBLE);
    }

    public void initializeAppNameEndAnimation(){
        forAppNameEnd = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.app_name_end_animation);
        appName.startAnimation(forAppNameEnd);
        appName.setVisibility(View.INVISIBLE);
    }

    public void initializeBridgeTextStartAnimation(){
        bridgeDescription.setVisibility(View.VISIBLE);
        bridgeDescStart = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bridge_desc_entry_animation);
        bridgeDescription.startAnimation(bridgeDescStart);
    }

    public void initializeTravelImageStart(){
        travelImage.setVisibility(View.VISIBLE);
        travelImageStart = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.travel_image_start_animation);
        travelImage.startAnimation(travelImageStart);
        //wow !
    }

    public void initializeTravelImageEnd(){
        travelImageEnd = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.travel_image_end_animation);
        travelImage.startAnimation(travelImageEnd);
        travelImage.setVisibility(View.INVISIBLE);
    }

    public void initializeBridgeTextEnd(){
        travelImageEnd = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.travel_image_end_animation);
        bridgeDescription.startAnimation(travelImageEnd);
        bridgeDescription.setVisibility(View.INVISIBLE);
    }

    public void changeBackground1(){
        constraintLayout.setBackgroundResource(R.drawable.splash_screen_background_2);
    }

    public void changeBackground2(){
        constraintLayout.setBackgroundResource(R.drawable.splash_screen_background_3);
    }
}
package com.example.travel_saathi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    ImageView earthImage;
    ConstraintLayout constraintLayout;

    Button button;
    TextView appName;
    Animation forAppName;
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

        interpolator = new DecelerateInterpolator();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rotateEarth();
                initializeAnimations();
                changeBackground(constraintLayout,getResources().getDrawable(R.drawable.splash_screen_background),getResources().getDrawable(R.drawable.splash_screen_background_2));
            }
        },500);


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
        earthImage.animate().setDuration(1000).rotation(newRotationAngle).setInterpolator(interpolator);
    }


    public void initializeAnimations(){
        forAppName = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.app_name_animation);
        appName.startAnimation(forAppName);
    }

    public void changeBackground(ConstraintLayout layout, Drawable initial, Drawable last){
        TransitionDrawable crossFade = new TransitionDrawable(new Drawable[]{initial,last});
        layout.setBackground(last);
        crossFade.startTransition(3000);
//        CrossFadeAnimation crossFadeAnimation = new CrossFadeAnimation(layout, initial,last,4000);
//        crossFadeAnimation.start();
    }
}
package com.abhijeet.travel_saathi.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.abhijeet.travel_saathi.R;

public class SecondTimeSplashScreen extends AppCompatActivity {
    MotionLayout motionLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_time_splash_screen);

        motionLayout = findViewById(R.id.justScooterUp);

    }
    @Override
    protected void onResume() {
        super.onResume();

        // Trigger your transition here
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Trigger your transition after the delay
                motionLayout.transitionToState(R.id.justScooter);
            }
        }, 500);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Trigger your transition after the delay
                motionLayout.transitionToState(R.id.findYourCompanyEnter);
            }
        }, 1100);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Trigger your transition after the delay
                motionLayout.transitionToState(R.id.chooseADateEnter);
            }
        }, 1800);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Trigger your transition after the delay
                motionLayout.transitionToState(R.id.travelTheWorldEntry);
            }
        }, 2500);

    }
}
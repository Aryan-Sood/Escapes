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
        }, 1200);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Trigger your transition after the delay
                motionLayout.transitionToState(R.id.chooseADateEnter);
            }
        }, 2000);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Trigger your transition after the delay
                motionLayout.transitionToState(R.id.travelTheWorldEntry);
            }
        }, 2600);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Trigger your transition after the delay
                motionLayout.transitionToState(R.id.jitter1);
            }
        }, 3200);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Trigger your transition after the delay
                motionLayout.transitionToState(R.id.jitter2);
            }
        }, 3800);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Trigger your transition after the delay
                motionLayout.transitionToState(R.id.jitter3);
            }
        }, 4400);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Trigger your transition after the delay
                motionLayout.transitionToState(R.id.jitter1);
            }
        }, 5000);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Trigger your transition after the delay
                motionLayout.transitionToState(R.id.jitter4);
            }
        }, 5600);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Trigger your transition after the delay
                motionLayout.transitionToState(R.id.travelTheWorldExit);
            }
        }, 6200);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Trigger your transition after the delay
                motionLayout.transitionToState(R.id.chooseADateExit);
            }
        }, 6800);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Trigger your transition after the delay
                motionLayout.transitionToState(R.id.findYourCompanyExit);
            }
        }, 7400);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Trigger your transition after the delay
                motionLayout.transitionToState(R.id.end);
            }
        }, 8000);


    }
}
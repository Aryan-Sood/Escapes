package com.abhijeet.travel_saathi.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.activities.Home_page;
import com.abhijeet.travel_saathi.activities.Signup_successfully;

public class LoginSignup extends AppCompatActivity {

    Button home_page;
    private boolean isLoginTransitioned = false;
    private boolean isSignupTransitioned1 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        MotionLayout motionLayout1 = findViewById(R.id.loginSignupCardScene);
        MotionLayout motionLayout2 = findViewById(R.id.loginCardComponentsScene);
        MotionLayout motionLayout3 = findViewById(R.id.signupCardComponentsScene);

    //for home_page
        home_page = findViewById(R.id.button);

        CardView loginCard = findViewById(R.id.loginCard);
        CardView SignupCard = findViewById(R.id.singupCard);
        loginCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLoginTransitioned){
                    motionLayout1.transitionToState(R.id.login);
                    motionLayout2.transitionToState(R.id.login_components);
                }
                else{
                    motionLayout1.transitionToState(R.id.start);
                    motionLayout2.transitionToStart();
                    motionLayout3.transitionToState(R.id.start);
                }
                isLoginTransitioned = !isLoginTransitioned;
            }
        });
        SignupCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                isLoginTransitioned = !isLoginTransitioned;
                if (!isSignupTransitioned1){
                    motionLayout1.transitionToState(R.id.signup);
                    motionLayout3.transitionToState(R.id.signup_components);
                }
                else{
                    motionLayout1.transitionToState(R.id.start);
                    motionLayout2.transitionToStart();
                    motionLayout3.transitionToState(R.id.start);
                }
                isSignupTransitioned1 = !isSignupTransitioned1;
            }
        });

        home_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginSignup.this, Signup_successfully.class);
                startActivity(intent);
                finish();
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (vibrator != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibrator.vibrate(50);
                    }
                }
            }
        });


    }
}
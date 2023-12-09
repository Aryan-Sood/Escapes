package com.abhijeet.travel_saathi.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.os.Bundle;
import android.view.View;

import com.abhijeet.travel_saathi.R;

public class LoginSignup extends AppCompatActivity {
    private boolean isLoginTransitioned = false;
    private boolean isSignupTransitioned1 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        MotionLayout motionLayout1 = findViewById(R.id.loginSignupCardScene);
        MotionLayout motionLayout2 = findViewById(R.id.loginCardComponentsScene);
        MotionLayout motionLayout3 = findViewById(R.id.signupCardComponentsScene);

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
    }
}
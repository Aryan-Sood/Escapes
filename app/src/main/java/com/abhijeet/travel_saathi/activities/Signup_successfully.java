package com.abhijeet.travel_saathi.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.abhijeet.travel_saathi.R;

public class Signup_successfully extends AppCompatActivity {

    ImageButton avtar_selection;
    MotionLayout motionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_successfully);

        avtar_selection = findViewById(R.id.avtar_selection);
        motionLayout = findViewById(R.id.signupSuccessfullyAnimation);

        motionLayout.transitionToState(R.id.end);

        avtar_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup_successfully.this, AvatarSelectionScreen.class);
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
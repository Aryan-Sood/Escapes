package com.example.travel_saathi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class setting_splash_screen extends AppCompatActivity {

    ImageView imageView;
    Animation forAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_splash_screen);

        imageView = findViewById(R.id.image);
        initializeAnimations();
    }

    public void initializeAnimations() {
        forAppName = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_to_up_animation);
        imageView.startAnimation(forAppName);
    }
}
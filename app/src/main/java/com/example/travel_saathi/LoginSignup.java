package com.example.travel_saathi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ImageView;

public class LoginSignup extends AppCompatActivity {
    Integer height;

    ImageView loginBackImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        loginBackImage = findViewById(R.id.login_background_image);

        getScreenHeight();
        loginBackImage.getLayoutParams().height=height/2;
        loginBackImage.requestLayout();

    }


    public void getScreenHeight(){
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        height = metrics.heightPixels;
    }
}
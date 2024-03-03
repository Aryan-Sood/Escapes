package com.abhijeet.travel_saathi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.abhijeet.travel_saathi.R;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.card.MaterialCardView;

public class Home_page extends AppCompatActivity {



    FlexboxLayout flexboxLayout;
    MaterialCardView mapsCardView, locationCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        flexboxLayout = findViewById(R.id.flexboxLayout);
        mapsCardView = findViewById(R.id.mapsCardView);
        locationCardView = findViewById(R.id.locationCardView);
    }




    public void initializeSizes(){
        int parentWidth = flexboxLayout.getWidth();



    }
}
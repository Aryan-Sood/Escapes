package com.abhijeet.travel_saathi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.abhijeet.travel_saathi.fragments.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class SplashScreenV2 extends AppCompatActivity {

    TabLayout tab;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_v2);

        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);
//
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);

    }
}
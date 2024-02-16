package com.abhijeet.travel_saathi.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.fragments.SplashCardScreenOne;
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

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(adapter.getCount());

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (position==1){
//                    // travelling splash screen appears now
//                }
//
//                else if (position==2){
//                    //companion splash screen appears now
//                }
//
//                else if (position==3){
//                    //privacy splash screen appears
//                }
            }

            @Override
            public void onPageSelected(int position) {
//                 if (position == 1) {
//                    SplashCardScreenOne fragment = (SplashCardScreenOne) adapter.getItem(position);
//                    fragment.startAnimationDelayed();
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}

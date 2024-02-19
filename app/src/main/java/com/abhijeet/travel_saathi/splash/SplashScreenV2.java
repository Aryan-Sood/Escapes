package com.abhijeet.travel_saathi.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.fragments.SplashCardScreenOne;
import com.abhijeet.travel_saathi.fragments.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class SplashScreenV2 extends AppCompatActivity {

    TabLayout tab;
    ViewPager viewPager;
    ImageView imageView;
    MotionLayout motionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_v2);

        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);
        imageView = findViewById(R.id.imageView2);
        motionLayout = findViewById(R.id.earthRotationAnimation);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(adapter.getCount());

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            Boolean earthGayab = false;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == adapter.getCount()-1){
                    motionLayout.transitionToState(R.id.end);
                    earthGayab = true;
                }
                else if (position == adapter.getCount() - 2 && earthGayab){
                    motionLayout.transitionToState(R.id.start);
                    earthGayab = false;
                }
//                else if (position == adapter.getCount() - 2 && positionOffset > 0.5 && !backward) {
//                    motionLayout.transitionToState(R.id.end);
//                }
//                else if (position == adapter.getCount() - 2 && positionOffset > 0.8 && backward) {
//                    motionLayout.transitionToState(R.id.start);
//                }

                float rotation = -180 * (position + positionOffset);
                imageView.setRotation(rotation);

            }

            @Override
            public void onPageSelected(int position) {
                // Handle page selected event if needed
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Handle page scroll state changed event if needed
            }
        });

    }
}

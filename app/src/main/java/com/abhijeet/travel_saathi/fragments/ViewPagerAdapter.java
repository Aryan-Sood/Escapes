package com.abhijeet.travel_saathi.fragments;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return new NameFragment();
        }

        else if (position==1){
            return new SplashCardScreenOne();
        }

        else if (position==2){
            return new SplashCardScreenTwo();
        }
        else if (position==3){
            return new SplashCardScreenThree();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}

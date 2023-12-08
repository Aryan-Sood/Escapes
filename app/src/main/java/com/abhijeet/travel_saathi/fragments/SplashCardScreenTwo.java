package com.abhijeet.travel_saathi.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhijeet.travel_saathi.R;


public class SplashCardScreenTwo extends Fragment {

    public SplashCardScreenTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_splash_card_screen_two, container, false);
    }
}
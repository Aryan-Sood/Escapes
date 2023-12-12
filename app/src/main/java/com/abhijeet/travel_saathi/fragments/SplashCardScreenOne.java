package com.abhijeet.travel_saathi.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhijeet.travel_saathi.R;


public class SplashCardScreenOne extends Fragment {
    MotionLayout motionLayout1;

    public SplashCardScreenOne() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        motionLayout1 = view.findViewById(R.id.travel_animation);

        motionLayout1.transitionToState(R.id.end);
        Log.d("TAG", "onViewCreated: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_splash_card_screen_one, container, false);
        motionLayout1 = rootView.findViewById(R.id.travel_animation);

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TAG", "onPause: ");
    }

    void startAnimation(){
        motionLayout1.transitionToState(R.id.end);
    }
}
package com.abhijeet.travel_saathi.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhijeet.travel_saathi.R;


public class NameFragment extends Fragment {
    boolean isVisibleToUser = false;
    MotionLayout motionLayout;

    public NameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_name, container, false);
        motionLayout = rootView.findViewById(R.id.name_fragment_animation);

//        startAnimationWithDelay();
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TAG", "onStop: ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startAnimationWithDelay();
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        this.isVisibleToUser = isVisibleToUser;
//        startAnimationWithDelay();
//    }

    void startAnimationWithDelay() {
        if (motionLayout != null ) {
            Log.d("mylog", "1 sgfrtarted");
            motionLayout.transitionToState(R.id.end);
//            motionLayout.transitionToState(R.id.end);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {

                    motionLayout.transitionToState(R.id.end2);
                }
            }, 3000);
        }
    }

}
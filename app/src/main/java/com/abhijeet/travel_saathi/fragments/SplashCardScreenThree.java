package com.abhijeet.travel_saathi.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.auth.LoginSignup;


public class SplashCardScreenThree extends Fragment {

    ImageButton login_page;
    public SplashCardScreenThree() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash_card_screen_three, container, false);

        login_page = view.findViewById(R.id.imageButton2); // Replace with your CardView ID

        // Set OnClickListener for the CardView
        login_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the LoginActivity here
                Intent intent = new Intent(getActivity(), LoginSignup.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
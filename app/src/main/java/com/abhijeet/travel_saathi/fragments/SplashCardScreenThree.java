package com.abhijeet.travel_saathi.fragments;
import android.content.Intent;
import android.os.Bundle;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.constraintlayout.motion.widget.MotionLayout;
        import androidx.fragment.app.Fragment;

        import android.os.Handler;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.ImageButton;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.auth.LoginSignup;
import com.abhijeet.travel_saathi.auth.NewLoginActivity;

public class SplashCardScreenThree extends Fragment {

    ImageButton login_page;
    public SplashCardScreenThree() {
        // Required empty public constructor
    }
    MotionLayout motionLayout1;
    boolean isVisibleToUser = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        motionLayout1 = view.findViewById(R.id.safeguarding_animation);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_splash_card_screen_three, container, false);
        motionLayout1 = rootView.findViewById(R.id.safeguarding_animation);
        login_page = rootView.findViewById(R.id.imageButton2);
        login_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the LoginActivity here
                Intent intent = new Intent(getActivity(), NewLoginActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startAnimationWithDelay();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        startAnimationWithDelay();
    }

    void startAnimationWithDelay() {
        if (motionLayout1 != null && isVisibleToUser) {
//            Log.d("mylog", "3 started");
            motionLayout1.transitionToState(R.id.end);
        }
    }
}



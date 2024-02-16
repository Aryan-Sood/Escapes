package com.abhijeet.travel_saathi.fragments;
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

        import com.abhijeet.travel_saathi.R;

public class SplashCardScreenTwo extends Fragment {

    public SplashCardScreenTwo() {
        // Required empty public constructor
    }
    MotionLayout motionLayout1;
    boolean isVisibleToUser = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        motionLayout1 = view.findViewById(R.id.companion_animation);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_splash_card_screen_two, container, false);
        motionLayout1 = rootView.findViewById(R.id.companion_animation);

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
            Log.d("mylog", "1 started");
            motionLayout1.transitionToState(R.id.end);
        }
    }
}


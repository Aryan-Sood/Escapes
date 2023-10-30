package com.example.travel_saathi;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CrossFadeAnimation {
    private final ConstraintLayout constraintLayout;
    private final Drawable initialDrawable;
    private final Drawable finalDrawable;
    private final int duration;

    public CrossFadeAnimation(ConstraintLayout constraintLayout, Drawable initialDrawable, Drawable finalDrawable, int duration) {
        this.constraintLayout = constraintLayout;
        this.initialDrawable = initialDrawable;
        this.finalDrawable = finalDrawable;
        this.duration = 4000;
    }


    public void start() {
        constraintLayout.setBackground(initialDrawable);

        constraintLayout.animate()
                .alpha(0f)
                .setDuration(duration / 2)
                .withEndAction(() -> {
                    constraintLayout.setBackground(finalDrawable);
                    constraintLayout.setAlpha(1f);
                });


    }
}

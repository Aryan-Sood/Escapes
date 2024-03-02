package com.abhijeet.travel_saathi.utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

public class GradientTextView extends AppCompatTextView {

    private int firstColor;
    private int secondColor;

    public GradientTextView(Context context, int color1, int color2) {
        super(context);
        this.firstColor = color1;
        this.secondColor = color2;
        init();
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GradientTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        firstColor = 0xFF000000;
        secondColor = 0xFF5754FF;
    }

    public void setGradientColors(int startColor, int endColor) {
        this.firstColor = startColor;
        this.secondColor = endColor;
        invalidate();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        getPaint().setShader(new LinearGradient(
                0, 0, getWidth(), getHeight(),
                new int[]{firstColor, secondColor}, // Example gradient colors
                null, Shader.TileMode.CLAMP));
        super.onDraw(canvas);
    }
}

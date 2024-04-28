package com.abhijeet.travel_saathi.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.abhijeet.travel_saathi.R;

public class AvatarSelectionScreen extends AppCompatActivity {
//
//
//    ImageView selectedProfileImage;
//    GridLayout profileGridLayout;

    private NumberPicker agePicker;
    private NumberPicker genderPicker;
    private EditText username, occupation, bio;
    MotionLayout motionLayout;
    TextView age,gender;
    private Button nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_selection_screen);

//        initializeIDs();
//        addGridProfileViews();
        motionLayout = findViewById(R.id.constraintLayout2);
        username = findViewById(R.id.unm);
        username.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard();
                    motionLayout.setTransition(R.id.end, R.id.ageselector);
                    return true;
                }
                return false;
            }
        });


        age=findViewById(R.id.agetext);
        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                motionLayout.setTransition(R.id.ageselector, R.id.agepicker);
            }
        });
        agePicker = (NumberPicker) findViewById(R.id.numberPicker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            agePicker.setTextColor(Color.BLACK);
        }
        agePicker.setMinValue(5);
        agePicker.setMaxValue(100);
        agePicker.setValue(20);
        agePicker.setWrapSelectorWheel(false);

        agePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age.setText(String.valueOf(agePicker.getValue())+" years");
                motionLayout.setTransition(R.id.agepicker, R.id.genderselector);
            }
        });

        gender = findViewById(R.id.gendertext);
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                motionLayout.setTransition(R.id.genderselector, R.id.genderpicker);
            }
        });
        genderPicker = (NumberPicker) findViewById(R.id.genderPicker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            genderPicker.setTextColor(Color.BLACK);
        }
        genderPicker.setMinValue(0);
        genderPicker.setMaxValue(2);
        genderPicker.setValue(1);
        genderPicker.setDisplayedValues(new String[]{"Male", "Female", "Others"}); // Displayed values
        genderPicker.setWrapSelectorWheel(false);

        genderPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] displayedValues = genderPicker.getDisplayedValues();
                gender.setText(displayedValues[genderPicker.getValue()]);
                motionLayout.setTransition(R.id.genderpicker, R.id.occupationtyper);
            }
        });

        occupation = findViewById(R.id.occupation);
        occupation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard();
                    motionLayout.setTransition(R.id.occupationtyper, R.id.bio);
                    return true;
                }
                return false;
            }
        });

        bio = findViewById(R.id.bio);
//        bio.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    hideKeyboard();
//                    return true;
//                }
//                return false;
//            }
//        });
        nextbtn = findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AvatarSelectionScreen.this, Select_Interest.class);
                startActivity(intent);
            }
        });

    }



    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



//    public void initializeIDs(){
//        profileGridLayout = findViewById(R.id.profileGridLayout);
//        selectedProfileImage = findViewById(R.id.selectedProfileImage);
//    }

//    public void addGridProfileViews(){
//        ImageView imageView1 = new ImageView(this);
//        imageView1.setImageResource(R.drawable.profileone);
//        ImageView imageView2 = new ImageView(this);
//        imageView2.setImageResource(R.drawable.profileone);
//        ImageView imageView3 = new ImageView(this);
//        imageView3.setImageResource(R.drawable.profileone);
//        ImageView imageView4 = new ImageView(this);
//        imageView4.setImageResource(R.drawable.profileone);
//        ImageView imageView5 = new ImageView(this);
//        imageView5.setImageResource(R.drawable.profileone);
//        ImageView imageView6 = new ImageView(this);
//        imageView6.setImageResource(R.drawable.profileone);
//        profileGridLayout.addView(imageView1);
//        profileGridLayout.addView(imageView2);
//        profileGridLayout.addView(imageView3);
//        profileGridLayout.addView(imageView4);
//        profileGridLayout.addView(imageView5);
//        profileGridLayout.addView(imageView6);
//
//        imageView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("clicked", "onClick: First");
//            }
//        });
//
//        imageView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("clicked", "onClick: Second");
//            }
//        });
//
//        imageView3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("clicked", "onClick: Third");
//            }
//        });
//
//        imageView4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("clicked", "onClick: Fourth");
//            }
//        });
//    }
}
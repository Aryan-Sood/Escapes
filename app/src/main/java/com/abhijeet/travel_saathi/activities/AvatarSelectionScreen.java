package com.abhijeet.travel_saathi.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.models.UserModel;
import com.abhijeet.travel_saathi.utils.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;


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
    UserModel userModel;
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
        bio.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard();
                    setUsername();
                    return true;
                }
                return false;
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

    void setUsername(){
        String user_name = username.getText().toString();
        String email = getSharedPreferences("isLoggedIn", MODE_PRIVATE).getString("Email", null).toString();

        Log.v("Email", email);

        if(user_name.isEmpty() || user_name.length()<3){
            Toast.makeText(this, "Username length should be at least 3 chars", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userModel != null){
            userModel.setUsername(user_name);
        }else{
            userModel = new UserModel(email, user_name, Timestamp.now(), FirebaseUtil.currentUserId(),age.getText().toString(), bio.getText().toString(), occupation.getText().toString() , gender.getText().toString());
        }

        FirebaseUtil.currentUserDetails().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AvatarSelectionScreen.this, "Done!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AvatarSelectionScreen.this, Home_page.class);
                    startActivity(intent);
                }
            }
        });

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
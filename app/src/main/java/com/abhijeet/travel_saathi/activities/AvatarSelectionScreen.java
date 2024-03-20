package com.abhijeet.travel_saathi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.abhijeet.travel_saathi.R;

public class AvatarSelectionScreen extends AppCompatActivity {


    ImageView selectedProfileImage;
    GridLayout profileGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_selection_screen);

        initializeIDs();
        addGridProfileViews();


    }



    public void initializeIDs(){
        profileGridLayout = findViewById(R.id.profileGridLayout);
        selectedProfileImage = findViewById(R.id.selectedProfileImage);
    }

    public void addGridProfileViews(){
        ImageView imageView1 = new ImageView(this);
        imageView1.setImageResource(R.drawable.profileone);
        ImageView imageView2 = new ImageView(this);
        imageView2.setImageResource(R.drawable.profileone);
        ImageView imageView3 = new ImageView(this);
        imageView3.setImageResource(R.drawable.profileone);
        ImageView imageView4 = new ImageView(this);
        imageView4.setImageResource(R.drawable.profileone);
        ImageView imageView5 = new ImageView(this);
        imageView5.setImageResource(R.drawable.profileone);
        ImageView imageView6 = new ImageView(this);
        imageView6.setImageResource(R.drawable.profileone);
        profileGridLayout.addView(imageView1);
        profileGridLayout.addView(imageView2);
        profileGridLayout.addView(imageView3);
        profileGridLayout.addView(imageView4);
        profileGridLayout.addView(imageView5);
        profileGridLayout.addView(imageView6);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("clicked", "onClick: First");
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("clicked", "onClick: Second");
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("clicked", "onClick: Third");
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("clicked", "onClick: Fourth");
            }
        });
    }
}
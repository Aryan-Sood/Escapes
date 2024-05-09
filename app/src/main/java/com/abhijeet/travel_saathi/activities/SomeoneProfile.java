package com.abhijeet.travel_saathi.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.abhijeet.travel_saathi.R;
import com.google.android.material.button.MaterialButton;

public class SomeoneProfile extends AppCompatActivity {

    ImageView backButton, profileImage;
    MaterialButton chatButton;
    TextView profileName, profileAge, profileBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_someone_profile);



        initializeIds();


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    public void initializeIds(){
        backButton = findViewById(R.id.backButton);
        profileImage = findViewById(R.id.profileImage);
        chatButton = findViewById(R.id.chatButton);
        profileName = findViewById(R.id.profileName);
        profileAge = findViewById(R.id.profileAge);
        profileBio = findViewById(R.id.profileBio);
    }
}
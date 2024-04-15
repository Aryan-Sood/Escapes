package com.abhijeet.travel_saathi.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.auth.NewLoginActivity;
import com.abhijeet.travel_saathi.chat_app.ChatMainActivity;
import com.abhijeet.travel_saathi.chat_app.model.UserModel;
import com.abhijeet.travel_saathi.chat_app.utils.FirebaseUtil;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

public class Home_page extends AppCompatActivity {



    FlexboxLayout flexboxLayout;
    MaterialCardView mapsCardView, locationCardView;
    private ImageView chat_button;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        flexboxLayout = findViewById(R.id.flexboxLayout);
        mapsCardView = findViewById(R.id.mapsCardView);
        locationCardView = findViewById(R.id.locationCardView);
        chat_button = findViewById(R.id.chat_button);

        getUsername();
        chat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home_page.this, ChatMainActivity.class));
            }
        });
    }




    public void initializeSizes(){
        int parentWidth = flexboxLayout.getWidth();
    }

    void getUsername(){
        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    userModel =  task.getResult().toObject(UserModel.class);
                    if(userModel!=null){

                    }
                }
            }
        });
    }
}
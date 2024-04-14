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
    private String email;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        flexboxLayout = findViewById(R.id.flexboxLayout);
        mapsCardView = findViewById(R.id.mapsCardView);
        locationCardView = findViewById(R.id.locationCardView);
        chat_button = findViewById(R.id.chat_button);

        SharedPreferences sh =getSharedPreferences("OnceLoggedIn", MODE_PRIVATE);
        email = sh.getString("Email", null);

        getUsername();
        chat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUsername();
            }
        });
    }




    public void initializeSizes(){
        int parentWidth = flexboxLayout.getWidth();
    }

    void setUsername(){
        String username = email;
        if(username.isEmpty() || username.length()<3){
            Toast.makeText(this, "Username length should be at least 3 chars", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userModel!=null){
            userModel.setUsername(username);
        }else{
            userModel = new UserModel(username, Timestamp.now(), FirebaseUtil.currentUserId());
        }

        FirebaseUtil.currentUserDetails().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Home_page.this, "Done!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Home_page.this, ChatMainActivity.class));
                }
            }
        });

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
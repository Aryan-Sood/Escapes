package com.abhijeet.travel_saathi.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.fragments.MessageFragment;
import com.abhijeet.travel_saathi.models.ChatroomModel;
import com.abhijeet.travel_saathi.models.UserModel;
import com.abhijeet.travel_saathi.utils.AndroidUtil;
import com.abhijeet.travel_saathi.utils.FirebaseUtil;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.Timestamp;

import java.util.Arrays;

public class SomeoneProfile extends AppCompatActivity {

    ImageView backButton, profileImage;
    MaterialButton chatButton;
    TextView profileName, profileAge, profileBio;

    ChatroomModel chatroomModel;
    String chatroomId;

    UserModel otherUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_someone_profile);

        otherUser = AndroidUtil.getUserModelFromIntent(getIntent());
        chatroomId = FirebaseUtil.getChatroomId(FirebaseUtil.currentUserId(), otherUser.getUserId());
        initializeIds();
        setValues();




        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOrCreateChatroomModel();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.show(getSupportFragmentManager(), messageFragment.getTag());
            }
        });

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

    public void setValues(){
        profileImage.setImageResource(R.drawable.avatar_uncle1);
        profileName.setText(otherUser.getUsername());
        profileAge.setText(otherUser.getAge());
        profileBio.setText(otherUser.getBio());
    }

    void getOrCreateChatroomModel(){
        FirebaseUtil.getChatroomReference(chatroomId).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                chatroomModel = task.getResult().toObject(ChatroomModel.class);
                if(chatroomModel==null){
                    //first time chat
                    chatroomModel = new ChatroomModel(
                            chatroomId,
                            Arrays.asList(FirebaseUtil.currentUserId(),otherUser.getUserId()),
                            Timestamp.now(),
                            ""
                    );
                    FirebaseUtil.getChatroomReference(chatroomId).set(chatroomModel);
                }
            }
        });
    }
}
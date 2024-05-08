package com.abhijeet.travel_saathi.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.adapters.QuestionAdapter;
import com.abhijeet.travel_saathi.auth.NewLoginActivity;
import com.abhijeet.travel_saathi.models.QuestionsModelClass;
import com.abhijeet.travel_saathi.models.UserModel;
import com.abhijeet.travel_saathi.utils.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class Select_Interest extends AppCompatActivity {

    private Button updatebtn;

    UserModel currentUserModel;
    String preferences;
//    ListView q1listview;
//    ConstraintLayout q1layout;
//    ArrayAdapter<String> adapter1;
//    String[] q1options = {"Adventure","Cultural","Relaxation","Sightseeing",
//            "Food & Drink","Wildlife","Beach"," City Breaks","Mountains"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_interest);

        updatebtn = findViewById(R.id.textButton);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // update the prefernecs only if minimum 5 questions are answered
                //i.e only if the size of marked answers array is graeter than equal to 5, allow next page intent

                Intent intent = new Intent(Select_Interest.this, Home_page.class);
                startActivity(intent);
            }
        });

        List<QuestionsModelClass> questions = new ArrayList<>();
        questions.add(new QuestionsModelClass("What type of travel experiences are you interested in?", new String[]{"Adventure", "Cultural", "Relaxation", "Sightseeing", "Food & Drink", "Wildlife", "Beach", "City Breaks", "Mountains"}));
        questions.add(new QuestionsModelClass("What type of food and cuisines do you enjoy?", new String[]{"Italian", "Mexican", "Indian", "Chinese", "Japanese", "Mediterranean", "American"}));
        questions.add(new QuestionsModelClass("What are your main interests or hobbies?", new String[]{"Sports", "Photography", "History", "Music", "Art", "Fashion", "Technology"}));
        questions.add(new QuestionsModelClass("Which of the following destinations would you prefer to visit during the spring season?", new String[]{"Shimla", "Darjeeling", "Ooty", "Munnar", "Manali", "Rishikesh", "Gangtok", "Coorg"}));
        questions.add(new QuestionsModelClass("Where would you like to travel to escape the summer heat?", new String[]{"Ladakh", "Leh", "Gulmarg", "Andaman and Nicobar Islands", "Pahalgam", "Nainital", "Mussoorie", "Mahabaleshwar"}));
        questions.add(new QuestionsModelClass("Which monsoon destination appeals to you the most?", new String[]{"Kerala (Backwaters)", "Goa (Beaches)", "Cherrapunji (Meghalaya)", "Alleppey (Alappuzha)", "Matheran", "Lonavala", "Pondicherry", "Udaipur"}));
        questions.add(new QuestionsModelClass("Where would you like to experience the winter chill?", new String[]{"Auli (Skiing)", "Gulmarg (Skiing)", "Manali", "Shimla", "Srinagar", "Kullu", "Rann of Kutch (Rann Utsav)", "Jaipur"}));


        RecyclerView recyclerView = findViewById(R.id.recycler_options);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new QuestionAdapter(questions));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

//    public void expand (View view){
//        int v = (options_list.getVisibility() == View.GONE)?View.VISIBLE:View.GONE;
//        TransitionManager.beginDelayedTransition(q1layout, new AutoTransition());
//        q1listview.setVisibility(v);
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //on selection jo karna hai

        return super.onOptionsItemSelected(item);
    }

    void setPreferences(UserModel userModel){


        FirebaseUtil.currentUserDetails().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Select_Interest.this, "Done!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void getUserData(){
        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                currentUserModel = task.getResult().toObject(UserModel.class);
                currentUserModel.setPreferences(preferences);
                setPreferences(currentUserModel);
            }
        });


    }
}
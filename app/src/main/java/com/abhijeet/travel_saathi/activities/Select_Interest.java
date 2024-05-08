package com.abhijeet.travel_saathi.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.adapters.QuestionAdapter;
import com.abhijeet.travel_saathi.models.QuestionsModelClass;
import com.abhijeet.travel_saathi.models.UserModel;
import com.abhijeet.travel_saathi.utils.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class Select_Interest extends AppCompatActivity {

    private Button updatebtn;

    UserModel currentUserModel;
    String travel, food, hobbies, spring, summer, monsoon, winter;
//    ListView q1listview;
//    ConstraintLayout q1layout;
//    ArrayAdapter<String> adapter1;
//    String[] q1options = {"Adventure","Cultural","Relaxation","Sightseeing",
//            "Food & Drink","Wildlife","Beach"," City Breaks","Mountains"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_interest);

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



//        getUserData();

        updatebtn = findViewById(R.id.textButton);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                travel = String.valueOf(questions.get(0).getSelectedOptionIndex()+1);
                food = String.valueOf(questions.get(1).getSelectedOptionIndex()+1);
                hobbies = String.valueOf(questions.get(2).getSelectedOptionIndex()+1);
                spring = String.valueOf(questions.get(3).getSelectedOptionIndex()+1);
                summer = String.valueOf(questions.get(4).getSelectedOptionIndex()+1);
                monsoon = String.valueOf(questions.get(5).getSelectedOptionIndex()+1);
                winter = String.valueOf(questions.get(6).getSelectedOptionIndex()+1);

                Log.d("VMII", "Travel: " + travel);

            }
        });


    }

//    public void expand (View view){
//        int v = (options_list.getVisibility() == View.GONE)?View.VISIBLE:View.GONE;
//        TransitionManager.beginDelayedTransition(q1layout, new AutoTransition());
//        q1listview.setVisibility(v);
//    }

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
                assert currentUserModel != null;
                currentUserModel.setFood(food);
                currentUserModel.setTravel(travel);
                currentUserModel.setHobbies(hobbies);
                currentUserModel.setSpring(spring);
                currentUserModel.setSummer(summer);
                currentUserModel.setMonsoon(monsoon);
                currentUserModel.setWinter(winter);
                setPreferences(currentUserModel);
            }
        });


    }
}
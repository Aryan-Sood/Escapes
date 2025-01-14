package com.abhijeet.travel_saathi.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.adapters.FromYourLocationAdapter;
import com.abhijeet.travel_saathi.adapters.SuggestedPlacesAdapter;
import com.abhijeet.travel_saathi.fragments.MessageFragment;
import com.abhijeet.travel_saathi.models.SuggestedPlacesModelClass;
import com.abhijeet.travel_saathi.models.UserModel;
import com.abhijeet.travel_saathi.utils.FirebaseUtil;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.*;

public class Home_page extends AppCompatActivity {

    FlexboxLayout flexboxLayout;
    MaterialCardView mapsCardView, locationCardView;

    RecyclerView fromYourLocationRecyclerView, suggestedPlacesRecyclerView;
    List<UserModel> locationUsersList = new ArrayList<>();;
    List<SuggestedPlacesModelClass> suggestedPlacesList = new ArrayList<>();
    FromYourLocationAdapter locationUsersAdapter;
    SuggestedPlacesAdapter suggestedPlacesAdapter;
    LinearLayoutManager locationUsersLayout, suggestedPlacesLayout;
    ImageView messageIcon, sideNavIcon;
    DrawerLayout drawerLayout;
    List<UserModel> users;
    NavigationView navigationView;
    ConstraintLayout suggestedConstraint;
    TextView settings, username;
    UserModel currentUserModel;
    //Suggestion
    String url = "http://10.0.2.2:5000/predict";
    int month;
    HashMap<String,CustomVariable> season =  new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        flexboxLayout = findViewById(R.id.flexboxLayout);
        mapsCardView = findViewById(R.id.mapsCardView);
        locationCardView = findViewById(R.id.locationCardView);
        fromYourLocationRecyclerView = findViewById(R.id.fromYourLocationRecyclerView);
        suggestedPlacesRecyclerView = findViewById(R.id.suggestedPlacesRecyclerView);
        messageIcon = findViewById(R.id.messages_icon);
        sideNavIcon = findViewById(R.id.side_nav);
        suggestedConstraint = findViewById(R.id.suggestedConstraintLayout);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        settings = navigationView.findViewById(R.id.settingsOption);
        username = navigationView.findViewById(R.id.username);


        SharedPreferences sh = getSharedPreferences("user_data", MODE_PRIVATE);

        String user_name = sh.getString("USERNAME", "null");
        if(user_name.isEmpty()){
            username.setText("None");
        }else{
            username.setText(user_name);
        }


        setCardsDimensions();
        suggestedPlacesInitData();
        suggestedPlacesRecyclerView();
        setConstraintLayoutHeight();
        getUserData();


        Dialog dialog = new Dialog(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.side_drawer,null);
        dialog.setContentView(dialogView);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.5F;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.START;
        View rootView = dialogView.findViewById(R.id.drawer_root_view);
        rootView.setTranslationX(-rootView.getWidth());


        HashMap< String,CustomVariable> spring = new HashMap<>();
        spring.put("1", new CustomVariable("Shimla", R.drawable.az));
        spring.put("2", new CustomVariable("Darjeeling", R.drawable.az));
        spring.put("3", new CustomVariable("Ooty", R.drawable.az));
        spring.put("4", new CustomVariable("Munnar", R.drawable.az));
        spring.put("5", new CustomVariable("Manali", R.drawable.az));
        spring.put("6", new CustomVariable("Rishikesh", R.drawable.az));
        spring.put("7", new CustomVariable("Gangtok", R.drawable.az));
        spring.put("8", new CustomVariable("Coorg", R.drawable.az));

        HashMap< String,CustomVariable> summer = new HashMap<>();
        summer.put("1", new CustomVariable("Ladakh", R.drawable.az));
        summer.put("2", new CustomVariable("Leh", R.drawable.az));
        summer.put("3", new CustomVariable("Gulmarg", R.drawable.az));
        summer.put("4", new CustomVariable("Andaman and Nicobar Island", R.drawable.az));
        summer.put("5", new CustomVariable("Pahalgam", R.drawable.az));
        summer.put("6", new CustomVariable("Nainital", R.drawable.az));
        summer.put("7", new CustomVariable("Mussoorie", R.drawable.az));
        summer.put("8", new CustomVariable("Mahabaleshwar", R.drawable.az));

        HashMap<String,CustomVariable> monsoon = new HashMap<>();
        monsoon.put("1", new CustomVariable("Kerala", R.drawable.az));
        monsoon.put("2", new CustomVariable("Goa", R.drawable.az));
        monsoon.put("3", new CustomVariable("Cherrapunji", R.drawable.az));
        monsoon.put("4", new CustomVariable("Alleppey", R.drawable.az));
        monsoon.put("5", new CustomVariable("Matheran", R.drawable.az));
        monsoon.put("6", new CustomVariable("Lonavala", R.drawable.az));
        monsoon.put("7", new CustomVariable("Pondicherry", R.drawable.az));
        monsoon.put("8", new CustomVariable("Udaipur", R.drawable.az));

        HashMap<String,CustomVariable> winter = new HashMap<>();
        winter.put("1",new CustomVariable("Auli", R.drawable.az));
        winter.put("2",new CustomVariable("Gulmarg", R.drawable.az));
        winter.put("3",new CustomVariable("Manali", R.drawable.az));
        winter.put("4",new CustomVariable("Shimla", R.drawable.az));
        winter.put("5",new CustomVariable("Srinagar", R.drawable.az));
        winter.put("6",new CustomVariable("Kullu", R.drawable.az));
        winter.put("7",new CustomVariable("Rann of Kutch", R.drawable.az));
        winter.put("8",new CustomVariable("Jaipur", R.drawable.az));


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            month = LocalDateTime.now().getMonthValue();
        }

        if(month>=3 && month<=5){
            season = spring;
        }
        else if(month>=6 && month<=8){
            season = summer;
        }
        else if(month>=9 && month<=11){
            season = monsoon;
        }
        else{
            season = winter;
        }

//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            String data = jsonObject.getString("place");
//                            CustomVariable temp = season.get(data);
//
//                            assert temp != null;
//                            String SugPlaceName = temp.Name;
//                            int SugPlaceImage = temp.image;
//
//                            suggestedPlacesList.add(new SuggestedPlacesModelClass(SugPlaceName,SugPlaceImage));
//                            for (int i=0;i< season.size();i++){
//                                if(!data.equals((i + 1) + "")){
//                                    suggestedPlacesList.add(new SuggestedPlacesModelClass(season.get((i+1)+"").Name,season.get((i+1)+"").image));
//                                }
//                            }
//                            suggestedPlacesRecyclerView();
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(Home_page.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                        Log.d(TAG, "onErrorResponse: "+error.getMessage());
//                    }
//                }){
//
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String,String>();
//                params.put("Travel_Preference","1");
//                params.put("Food_and_Cuisine_Taste","2");
//                params.put("Interests","3");
//                return params;
//            }
//
//        };
//        RequestQueue queue = Volley.newRequestQueue(Home_page.this);
//        queue.add(stringRequest);






        /*
        GENERATING PERSON SUGGESTION LIST
        {
            VariableType VName = current user data; (required from sid)
            VT list = list of all users along with attributes; (required from sid)
            VT list2 = list of all users having similar prefs as current users;
            **list2 may be created directly by Db query OR by comparing users attribute in 'list' with VName attributes(curr user).
        }
         */









    // previous
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_page.this, SettingsPage.class);
                startActivity(intent);
            }
        });

        messageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.show(getSupportFragmentManager(), messageFragment.getTag());
            }
        });


        mapsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_page.this, MapPage.class);
                startActivity(intent);

            }
        });

        sideNavIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });




    }

    public void fromYourLocationInitData(){
        locationUsersList.add(new UserModel(R.drawable.avatar_uncle1,"Uncle 1"));
        locationUsersList.add(new UserModel(R.drawable.avatar_lady1,"Lady 1"));
        locationUsersList.add(new UserModel(R.drawable.scooter_uncle,"Uncle 2"));
        locationUsersList.add(new UserModel(R.drawable.avatar_lady2,"Lady 2"));
    }

    public void fromYourLocationRecyclerView(){
        locationUsersLayout = new LinearLayoutManager(this);
        locationUsersLayout.setOrientation(RecyclerView.VERTICAL);
        fromYourLocationRecyclerView.setLayoutManager(locationUsersLayout);
        locationUsersAdapter = new FromYourLocationAdapter(locationUsersList, Home_page.this);
        fromYourLocationRecyclerView.setAdapter(locationUsersAdapter);
        locationUsersAdapter.notifyDataSetChanged();
    }

    public void suggestedPlacesInitData(){


//        for (int i=0;i<season.size();i++){
//            System.out.println("IN");
//            suggestedPlacesList.add(new SuggestedPlacesModelClass("HELLO",R.drawable.scene_two));
//        }



        suggestedPlacesList.add(new SuggestedPlacesModelClass("Shimla",R.drawable.scene_two));
        suggestedPlacesList.add(new SuggestedPlacesModelClass("Cuba",R.drawable.az));
        suggestedPlacesList.add(new SuggestedPlacesModelClass("Washington",R.drawable.scene_one));
        suggestedPlacesList.add(new SuggestedPlacesModelClass("Argentina",R.drawable.ar));
        suggestedPlacesList.add(new SuggestedPlacesModelClass("Australia",R.drawable.aus));
        suggestedPlacesList.add(new SuggestedPlacesModelClass("Bangkok",R.drawable.bang));
//        suggestedPlacesList.add(new SuggestedPlacesModelClass(R.drawable.scene_one));
//        suggestedPlacesList.add(new SuggestedPlacesModelClass(R.drawable.scene_two));
//        suggestedPlacesList.add(new SuggestedPlacesModelClass(R.drawable.scene_one));
//        suggestedPlacesList.add(new SuggestedPlacesModelClass(R.drawable.scene_two));
//        suggestedPlacesList.add(new SuggestedPlacesModelClass(R.drawable.scene_one));
//        suggestedPlacesList.add(new SuggestedPlacesModelClass(R.drawable.scene_two));
    }

    public void suggestedPlacesRecyclerView(){
        suggestedPlacesRecyclerView.setNestedScrollingEnabled(true);
        suggestedPlacesLayout = new LinearLayoutManager(this);
        suggestedPlacesLayout.setOrientation(RecyclerView.VERTICAL);
        suggestedPlacesRecyclerView.setLayoutManager(suggestedPlacesLayout);
        suggestedPlacesAdapter = new SuggestedPlacesAdapter(suggestedPlacesList);
        suggestedPlacesRecyclerView.setAdapter(suggestedPlacesAdapter);
        suggestedPlacesAdapter.notifyDataSetChanged();
    }

    public int getScreenWidth(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public int getScreenHeight(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public int getCardsSize(int screenWidth){
        int availableWidth = screenWidth - 40 - 100;
        return Integer.valueOf(availableWidth/2);
    }

    public void setCardsDimensions(){
        int screenWidth = getScreenWidth(this);
        int cardWidth = getCardsSize(screenWidth);

        FlexboxLayout.LayoutParams mapsLayoutParams = (FlexboxLayout.LayoutParams) mapsCardView.getLayoutParams();
        FlexboxLayout.LayoutParams locationLayoutParams = (FlexboxLayout.LayoutParams) locationCardView.getLayoutParams();

        mapsLayoutParams.width = cardWidth;
        mapsLayoutParams.height = (int) (cardWidth*1.2);
        locationLayoutParams.width = cardWidth;
        locationLayoutParams.height = (int) (cardWidth*1.2);
        mapsCardView.setLayoutParams(mapsLayoutParams);
        locationCardView.setLayoutParams(locationLayoutParams);
    }

    public void setConstraintLayoutHeight(){
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) suggestedConstraint.getLayoutParams();
        layoutParams.height = getScreenHeight(this);
        suggestedConstraint.setLayoutParams(layoutParams);
    }

    public void getData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").where(Filter.or(
                Filter.equalTo("travel", currentUserModel.getTravel()),
                Filter.equalTo("food", currentUserModel.getFood()),
                Filter.equalTo("hobbies", currentUserModel.getHobbies()),
                Filter.equalTo("spring", currentUserModel.getSpring()),
                Filter.equalTo("summer", currentUserModel.getSummer()),
                Filter.equalTo("monsoon", currentUserModel.getMonsoon()),
                Filter.equalTo("winter", currentUserModel.getWinter())
            )).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    users = task.getResult().toObjects(UserModel.class);
                    if (!users.isEmpty() || users != null) {
                        for (UserModel i : users) {
                            if(!i.getUserId().equals( currentUserModel.getUserId())){
                                locationUsersList.add(i);
                            }
                        }
                    } else {
                        fromYourLocationInitData();
                    }
                    fromYourLocationRecyclerView();
                }else{
                    Log.v("GET", "Unsuccessful");
                }
            }
        });

    }

    void getUserData(){
        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                currentUserModel = task.getResult().toObject(UserModel.class);
                getData();
            }
        });
    }
}
package com.abhijeet.travel_saathi.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.adapters.FromYourLocationAdapter;
import com.abhijeet.travel_saathi.adapters.SuggestedPlacesAdapter;
import com.abhijeet.travel_saathi.fragments.MessageFragment;
import com.abhijeet.travel_saathi.models.FromYourLocationModelClass;
import com.abhijeet.travel_saathi.models.SuggestedPlacesModelClass;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class Home_page extends AppCompatActivity {

    FlexboxLayout flexboxLayout;
    MaterialCardView mapsCardView, locationCardView;

    RecyclerView fromYourLocationRecyclerView, suggestedPlacesRecyclerView;
    List<FromYourLocationModelClass> locationUsersList;
    List<SuggestedPlacesModelClass> suggestedPlacesList;
    FromYourLocationAdapter locationUsersAdapter;
    SuggestedPlacesAdapter suggestedPlacesAdapter;
    LinearLayoutManager locationUsersLayout, suggestedPlacesLayout;
    ImageView messageIcon;

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




        setCardsDimensions();
        fromYourLocationInitData();
        suggestedPlacesInitData();
        fromYourLocationRecyclerView();
        suggestedPlacesRecyclerView();


        messageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.show(getSupportFragmentManager(), messageFragment.getTag());
            }
        });
    }

    public void fromYourLocationInitData(){
        locationUsersList = new ArrayList<>();
        locationUsersList.add(new FromYourLocationModelClass(R.drawable.avatar_uncle1,"Uncle 1"));
        locationUsersList.add(new FromYourLocationModelClass(R.drawable.avatar_lady1,"Lady 1"));
        locationUsersList.add(new FromYourLocationModelClass(R.drawable.scooter_uncle,"Uncle 2"));
        locationUsersList.add(new FromYourLocationModelClass(R.drawable.avatar_lady2,"Lady 2"));
    }

    public void fromYourLocationRecyclerView(){
        locationUsersLayout = new LinearLayoutManager(this);
        locationUsersLayout.setOrientation(RecyclerView.VERTICAL);
        fromYourLocationRecyclerView.setLayoutManager(locationUsersLayout);
        locationUsersAdapter = new FromYourLocationAdapter(locationUsersList);
        fromYourLocationRecyclerView.setAdapter(locationUsersAdapter);
        locationUsersAdapter.notifyDataSetChanged();
    }

    public void suggestedPlacesInitData(){
        suggestedPlacesList = new ArrayList<>();
        suggestedPlacesList.add(new SuggestedPlacesModelClass(R.drawable.scene_one));
        suggestedPlacesList.add(new SuggestedPlacesModelClass(R.drawable.scene_two));
    }

    public void suggestedPlacesRecyclerView(){
        suggestedPlacesRecyclerView.setNestedScrollingEnabled(false);
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

}
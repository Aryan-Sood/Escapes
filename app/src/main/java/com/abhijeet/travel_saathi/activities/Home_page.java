package com.abhijeet.travel_saathi.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.adapters.FromYourLocationAdapter;
import com.abhijeet.travel_saathi.models.FromYourLocationModelClass;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class Home_page extends AppCompatActivity {

    FlexboxLayout flexboxLayout;
    MaterialCardView mapsCardView, locationCardView;

    RecyclerView fromYourLocationRecyclerView;
    List<FromYourLocationModelClass> locationUsersList;
    FromYourLocationAdapter locationUsersAdapter;
    LinearLayoutManager locationUsersLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        flexboxLayout = findViewById(R.id.flexboxLayout);
        mapsCardView = findViewById(R.id.mapsCardView);
        locationCardView = findViewById(R.id.locationCardView);
        fromYourLocationRecyclerView = findViewById(R.id.fromYourLocationRecyclerView);


        fromYourLocationInitData();
        fromYourLocationRecyclerView();
    }

    public void fromYourLocationInitData(){
        locationUsersList = new ArrayList<>();
        locationUsersList.add(new FromYourLocationModelClass(R.drawable.avatar_uncle1,"Uncle 1"));
        locationUsersList.add(new FromYourLocationModelClass(R.drawable.avatar_lady1,"Lady 1"));
        locationUsersList.add(new FromYourLocationModelClass(R.drawable.scooter_uncle,"Uncle 2"));
        locationUsersList.add(new FromYourLocationModelClass(R.drawable.avatar_lady2,"Lady 2"));
    }

    public void fromYourLocationRecyclerView(){
//        fromYourLocationRecyclerView.setNestedScrollingEnabled(true);
        locationUsersLayout = new LinearLayoutManager(this);
        locationUsersLayout.setOrientation(RecyclerView.VERTICAL);
        fromYourLocationRecyclerView.setLayoutManager(locationUsersLayout);
        locationUsersAdapter = new FromYourLocationAdapter(locationUsersList);
        fromYourLocationRecyclerView.setAdapter(locationUsersAdapter);
        locationUsersAdapter.notifyDataSetChanged();
    }

}
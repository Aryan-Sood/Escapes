package com.abhijeet.travel_saathi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.travel_saathi.adapters.FromYourLocationAdapter;
import com.abhijeet.travel_saathi.models.UserModel;

import java.util.List;

public class location_main extends AppCompatActivity {

    RecyclerView locationRecyclerView;
    List<UserModel> locationList;
    FromYourLocationAdapter locationAdapter;
    LinearLayoutManager locationLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_main);


        initializeIds();
    }


    public void initializeIds(){
        locationRecyclerView = findViewById(R.id.locationRecyclerView);
    }
}
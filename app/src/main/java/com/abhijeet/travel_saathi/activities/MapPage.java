package com.abhijeet.travel_saathi.activities;

import android.os.Bundle;
import android.widget.DatePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.abhijeet.travel_saathi.R;
import com.google.android.material.datepicker.MaterialDatePicker;

public class MapPage extends AppCompatActivity {

    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);


        initializeId();


//        MaterialDatePicker dateSelector = MaterialDatePicker.Builder.dateRangePicker().setTitleText("Select travel dates").setSelection(new Pair<>(MaterialDatePicker.thisMonthInUtcMilliseconds(), MaterialDatePicker.todayInUtcMilliseconds())).build();
//        dateSelector.show(getSupportFragmentManager(),"");

    }


    public void initializeId(){
//        datePicker = findViewById(R.id.date_picker);
    }
}
package com.abhijeet.travel_saathi.utilities;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.abhijeet.travel_saathi.R;
import com.kizitonwose.calendar.view.ViewContainer;

public class DayViewController extends ViewContainer {

    TextView textView;


    public DayViewController(@NonNull View view) {
        super(view);
        textView = view.findViewById(R.id.calendarDayText);
    }
}

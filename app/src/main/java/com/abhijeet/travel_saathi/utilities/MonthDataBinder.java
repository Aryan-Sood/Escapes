package com.abhijeet.travel_saathi.utilities;

import android.view.View;

import androidx.annotation.NonNull;

import com.kizitonwose.calendar.core.CalendarDay;
import com.kizitonwose.calendar.view.MonthDayBinder;

public class MonthDataBinder implements MonthDayBinder<DayViewController> {

    @Override
    public void bind(@NonNull DayViewController container, CalendarDay calendarDay) {
        container.textView.setText(String.valueOf(calendarDay.getDate().getDayOfMonth()));

    }

    @NonNull
    @Override
    public DayViewController create(@NonNull View view) {
        return new DayViewController(view);
    }
}

package com.abhijeet.travel_saathi.activities;

import static com.kizitonwose.calendar.core.ExtensionsKt.daysOfWeek;
import static com.kizitonwose.calendar.core.ExtensionsKt.firstDayOfWeekFromLocale;

import android.graphics.Canvas;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.travel_saathi.BuildConfig;
import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.utilities.MonthDataBinder;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import ru.cleverpumpkin.calendar.CalendarDate;
import ru.cleverpumpkin.calendar.CalendarView;


public class MapPage extends AppCompatActivity {

    public RequestQueue requestQueue;
    CalendarView calendarView;
    private final String apiKey = BuildConfig.HERE_API_KEY;

    MaterialAutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);

        requestQueue = Volley.newRequestQueue(this);


        initializeId();
        initializeCalender();


        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchPlaces(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }


    public void initializeId(){
        autoCompleteTextView = findViewById(R.id.auto_complete);
        calendarView = findViewById(R.id.calendar_view);
    }

    private void searchPlaces(String query) {

        String url = "https://autosuggest.search.hereapi.com/v1/autosuggest?apiKey="+apiKey+"&at=33.738045,73.084488&limit=5&resultType=city&q=" + query + "&lang=en-US";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray items = response.getJSONArray("items");
                    ArrayList<String> placeNames = new ArrayList<>();
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject item = items.getJSONObject(i);
                        placeNames.add(item.getString("title"));
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MapPage.this, android.R.layout.simple_dropdown_item_1line, placeNames);
                    autoCompleteTextView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MapPage.this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }


    public void initializeCalender(){

        Calendar calendar = Calendar.getInstance();

        calendar.set(2024,Calendar.APRIL,29);
        CalendarDate initialDate = new CalendarDate(calendar.getTime());

        CalendarDate minDate = new CalendarDate(calendar.getTime());

        calendar.set(2030, Calendar.JULY, 15);
        CalendarDate maxDate = new CalendarDate(calendar.getTime());

        List<CalendarDate> preSelectedDate = new ArrayList<>();
        preSelectedDate.add(initialDate);
        preSelectedDate.add(initialDate);

        int firstDayOfWeek = Calendar.MONDAY;

        calendarView.setupCalendar(initialDate, minDate, maxDate, CalendarView.SelectionMode.RANGE,preSelectedDate, firstDayOfWeek, false);
//            calendarView.setupCalendar(initialDate);
        calendarView.setOnDateClickListener(new Function1<CalendarDate, Unit>() {
            @Override
            public Unit invoke(CalendarDate calendarDate) {
//                Toast.makeText(MapPage.this, String.valueOf(calendarDate.getDayOfMonth()), Toast.LENGTH_SHORT).show();
                return null;
            }
        });

        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        };

        calendarView.addCustomItemDecoration(itemDecoration);

//        calendarView = findViewById(R.id.calendarView);

//        calendarView.setDayBinder(new MonthDataBinder());
//        YearMonth currentMonth = YearMonth.now();
//        Toast.makeText(this, currentMonth.getMonth().toString(), Toast.LENGTH_SHORT).show();
//        YearMonth startMonth = currentMonth.minusMonths(100);
//        YearMonth endMonth = currentMonth.plusMonths(100);
//        DayOfWeek firstDayOfWeek = firstDayOfWeekFromLocale();
//
//        calendarView.setup(startMonth, endMonth, firstDayOfWeek);
//        calendarView.scrollToMonth(currentMonth);
//
//        List<DayOfWeek> allWeekDays = daysOfWeek();
//
////        calendarView.setup(startMonth, endMonth, allWeekDays.first());
//
//        ViewGroup titlesContainer = (ViewGroup) findViewById(R.id.titlesContainer);
//        for (int i = 0; i < titlesContainer.getChildCount(); i++) {
//            View childView = titlesContainer.getChildAt(i);
//
//            if (childView instanceof TextView) {
//                TextView textView = (TextView) childView;
//                int index = i;
//                DayOfWeek dayOfWeek = allWeekDays.get(index);
//                String title = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault());
//                textView.setText(title);
//            }
//        }


//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2024, Calendar.APRIL, 28);
//        CalendarDate initialDate = new CalendarDate(calendar.getTime());
//
//        calendar.set(2018, Calendar.APRIL, 28);
//        CalendarDate minDate = new CalendarDate(calendar.getTime());
//
//        calendar.set(2030, Calendar.APRIL, 28);
//        CalendarDate maxDate = new CalendarDate(calendar.getTime());

//        List<CalendarDate> preselectedDate = getPreselectedDates();
//        CalendarDate firstDayOfWeek = java.util.Calendar.MONDAY;

//        calendarView.setupCalendar(
//                initialDate = initialDate,
//                minDate = minDate,
//                maxDate = maxDate,
//                CalendarView.SelectionMode.RANGE,
//                preselectedDate,
//
//
//
//        );

    }
}
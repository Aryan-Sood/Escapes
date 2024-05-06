package com.abhijeet.travel_saathi.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.travel_saathi.BuildConfig;
import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.adapters.SimilarInterestAdapter;
import com.abhijeet.travel_saathi.models.SimilarInterestModelClass;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MapPage extends AppCompatActivity {

    public RequestQueue requestQueue;

    CalendarView calendarView;
    FlexboxLayout chipLayout;
    List<SimilarInterestModelClass> similarInterestList;
    SimilarInterestAdapter similarInterestAdapter;
    LinearLayoutManager similarInterestLayout;
    RecyclerView similarInterestRecyclerView;



    private final String apiKey = BuildConfig.HERE_API_KEY;

    MaterialAutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);

        requestQueue = Volley.newRequestQueue(this);

        initializeId();

        setSimilarInterestRecyclerViewData();
        setSimilarInterestRecyclerView();

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

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                Chip chip = new Chip(getApplicationContext());
                chip.setText(selectedItem);
                ChipGroup chipGroup = new ChipGroup(getApplicationContext());
                chipGroup.addView(chip);
                chipLayout.addView(chipGroup);
            }
        });

    }


    public void initializeId(){
        autoCompleteTextView = findViewById(R.id.auto_complete);
        chipLayout = findViewById(R.id.flexboxLayout6);
        similarInterestRecyclerView = findViewById(R.id.locationRecyclerView);
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
//                Toast.makeText(MapPage.this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    public void setSimilarInterestRecyclerViewData(){
        similarInterestList = new ArrayList<>();
        similarInterestList.add(new SimilarInterestModelClass("Aryan Sood","Chandigarh"));
        similarInterestList.add(new SimilarInterestModelClass("Rishiraj Jain","Sagar"));
        similarInterestList.add(new SimilarInterestModelClass("Siddharth Kumar","Delhi"));
        similarInterestList.add(new SimilarInterestModelClass("Abhijeet Pandey","Bihar"));
        similarInterestList.add(new SimilarInterestModelClass("Vibhor Mathur","Jabalpur"));
    }
    public void setSimilarInterestRecyclerView(){
        similarInterestLayout = new LinearLayoutManager(this);
        similarInterestLayout.setOrientation(RecyclerView.VERTICAL);
        similarInterestRecyclerView.setLayoutManager(similarInterestLayout);
        similarInterestAdapter = new SimilarInterestAdapter(similarInterestList);
        similarInterestRecyclerView.setAdapter(similarInterestAdapter);
        similarInterestAdapter.notifyDataSetChanged();
    }


}
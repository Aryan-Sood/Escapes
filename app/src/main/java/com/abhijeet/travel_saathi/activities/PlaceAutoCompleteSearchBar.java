package com.abhijeet.travel_saathi.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.abhijeet.travel_saathi.BuildConfig;
import com.abhijeet.travel_saathi.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlaceAutoCompleteSearchBar extends AppCompatActivity {

    public RequestQueue requestQueue;
    private String apiKey = BuildConfig.HERE_API_KEY;

    AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_autocomplete_search_bar);

        requestQueue = Volley.newRequestQueue(this);

       autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
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

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(PlaceAutoCompleteSearchBar.this, android.R.layout.simple_dropdown_item_1line, placeNames);
                    autoCompleteTextView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PlaceAutoCompleteSearchBar.this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
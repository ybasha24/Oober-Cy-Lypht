package com.example.myapplication.rider.searchtrip;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.myapplication.MainActivity;
import com.example.myapplication.*;
import com.example.myapplication.app.AppController;
import com.example.myapplication.rider.searchtrip.SearchTripPlace;
import com.example.myapplication.rider.searchtrip.SearchTripTime;
import com.example.myapplication.rider.TripsAdapter;
import com.example.myapplication.endpoints.Endpoints;
import com.google.android.material.slider.Slider;

import org.json.JSONArray;
import org.json.JSONException;

import java.time.LocalDateTime;

public class SearchPage extends AppCompatActivity {

    ListView listView;
    Slider radiusSlider;
    JSONArray tripsList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String originAddress;
    private String destAddress;
    private int durationHours;
    private int durationMinutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_search_page);
        radiusSlider = findViewById(R.id.radiusSlider);

        if(SearchTripTime.startDate != null){
            startDate = SearchTripTime.startDate;
        }
        if(startDate != null && (SearchTripPlace.durationHours != 0 || SearchTripPlace.durationMinutes != 0)){
            durationHours = SearchTripPlace.durationHours;
            durationMinutes = SearchTripPlace.durationMinutes;
            endDate = SearchTripTime.startDate.plusHours(durationHours).plusMinutes(durationMinutes);
        }
        if(SearchTripPlace.originAddress != null){
            originAddress = SearchTripPlace.originAddress;
        }
        if(SearchTripPlace.destAddress != null){
            destAddress = SearchTripPlace.destAddress;
        }
    }

    public void searchTrips(View view)
    {
        listView = findViewById(R.id.listView);
        String url = Endpoints.RiderSearchTripUrl + startDate +
                "&scheduledEndDate=" + endDate;
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    Log.e("response", response.toString());
                    if(response != null) {
                        tripsList = response;
                        listView.setAdapter(new TripsAdapter(tripsList, getApplicationContext()));
                    }
                },
                error -> Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG));
        AppController.getInstance().addToRequestQueue(req, "array_req");
    }

    public void sortList()
    {
        for(int i = 0; i < tripsList.length(); i++)
        {
           int x = tripsList.length();
        }
    }
}

package com.example.myapplication.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.*;
import com.example.myapplication.app.AppController;
import com.example.myapplication.driver.createtrip.SelectTripTime;
import com.example.myapplication.endpoints.Endpoints;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class TripDetail extends AppCompatActivity {

    JSONObject trip;
    ArrayList<String> riderNames;
    TextView riders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);
        trip = TripsAdapter.currentJson;
        setDetails();
        riderNames = new ArrayList<>();
    }

    private void setDetails(){
        TextView origin = findViewById(R.id.originTV);
        TextView dest = findViewById(R.id.destTV);
        TextView start = findViewById(R.id.starttimeTV);
        TextView end = findViewById(R.id.endTimeTV);
        riders = findViewById(R.id.ridersTV);

        try {
            origin.setText(trip.getString("originAddress"));
            dest.setText(trip.getString("destAddress"));
            start.setText(trip.getString("scheduledStartDate"));
            end.setText(trip.getString("scheduledEndDate"));
            JSONArray riderIdsArray = trip.getJSONArray("riderIds");
            for(int i = 0; i < riderIdsArray.length(); i++) {
                setRiderNames(riderIdsArray.getInt(i));
            }
        }
        catch(Exception e){
            Log.e("error", e.toString());
        }
    }

    public void editTrip(View v){
        Intent i = new Intent(this, SelectTripTime.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            i.putExtra("editing", true);
            i.putExtra("tripId", trip.getInt("id"));
        }
        catch(JSONException e) {}
        this.startActivity(i);
    }

    public void deleteTrip(View v){
        try {
            StringRequest req = new StringRequest(Request.Method.DELETE, Endpoints.DeleteTripUrl + "?id=" + trip.getInt("id"),
                response -> {
                    Intent i = new Intent(this, TripsList.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    this.startActivity(i);
                    Toast toast = Toast.makeText(this, "Successfully deleted trip", Toast.LENGTH_LONG);
                    toast.show();
                },
                error -> {
                    Log.e("trips error", error.toString());
                    Toast toast = Toast.makeText(this, "Error deleting trip", Toast.LENGTH_LONG);
                    toast.show();
                }
            );
            AppController.getInstance().addToRequestQueue(req, "string_req");
        }
        catch(Exception e){}
    }

    public void setRiderNames(int id){
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, Endpoints.GetUserUrl + id, null,
            response -> {
                try {
                    riderNames.add(response.getString("firstName") + " " + response.getString("lastName"));
                    riders.setText("Riders: " + riderNames.toString());
                }
                catch(Exception e){
                    Log.e("error", e.toString());
                }
            },
            error -> { }
        );
        AppController.getInstance().addToRequestQueue(req, "string_req");
    }
}
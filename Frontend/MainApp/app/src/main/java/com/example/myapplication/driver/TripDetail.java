package com.example.myapplication.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.*;
import com.example.myapplication.app.AppController;
import com.example.myapplication.driver.createtrip.SelectTripTime;
import com.example.myapplication.endpoints.Endpoints;

import org.json.JSONException;
import org.json.JSONObject;

public class TripDetail extends AppCompatActivity {

    JSONObject trip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);
        trip = TripsAdapter.currentJson;
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
}
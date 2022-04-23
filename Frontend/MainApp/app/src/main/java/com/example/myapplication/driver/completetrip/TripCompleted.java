package com.example.myapplication.driver.completetrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.myapplication.*;
import com.example.myapplication.app.AppController;
import com.example.myapplication.driver.HomePage;
import com.example.myapplication.driver.TripDetail;
import com.example.myapplication.driver.TripsAdapter;
import com.example.myapplication.endpoints.Endpoints;

import org.json.JSONArray;
import org.json.JSONException;

public class TripCompleted extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_trip_completed);
        listAllRiders();
    }

    private void listAllRiders(){
        try {
            listView = findViewById(R.id.listView);
            listView.setAdapter(new RateRidersAdapter(TripDetail.riderNames, getApplicationContext()));
        } catch(Exception e){}
    }

    /**
     * finishes a trip and goes back to home screen
     * @param v view that is referencing this method
     */
    public void finishTrip(View v){
        Intent i = new Intent(this, HomePage.class);
        startActivity(i);
    }
}
package com.example.myapplication.driver;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.TripsAdapter;
import com.example.myapplication.app.AppController;
import com.example.myapplication.endpoints.endpoints;

import org.json.JSONArray;
import org.json.JSONException;

public class DriverCreatedRides extends AppCompatActivity {

    ListView listView;
    JSONArray tripsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_created_rides);
        listAllTrips();
    }

    public void listAllTrips(){
        try {
            listView = findViewById(R.id.listView);
            String url = endpoints.AllDriverTripsUrl + MainActivity.accountObj.getInt("id");
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    tripsList = response;
                    listView.setAdapter(new TripsAdapter(tripsList, getApplicationContext()));
                },
                error -> Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG));
            AppController.getInstance().addToRequestQueue(req, "array_req");
        } catch(JSONException e){}
    }
}
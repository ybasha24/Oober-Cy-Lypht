package com.example.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.myapplication.R;
import com.example.myapplication.app.AppController;
import com.example.myapplication.driver.DriverHomePage;

import org.json.JSONArray;

public class TripsList extends AppCompatActivity {

    ListView listView;
    JSONArray tripsList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_trips_list);
        listView = findViewById(R.id.listView);
        String url = "";
        url = "http://coms-309-030.class.las.iastate.edu:8080/trip/getAllTrips";
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
            response -> {
                Log.e("Trips list error", response.toString());
                tripsList = response;
                listView.setAdapter(new AdminTripsAdapter(tripsList, getApplicationContext()) );
            },
            error -> {
                Log.e("trips list error", error.toString());
                Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG);
            });
        AppController.getInstance().addToRequestQueue(req, "post_object_tag");
    }

    public void onBackPressed() {
        Intent i = new Intent(this, AdminHomePage.class);
        this.startActivity(i);
        super.onBackPressed();
    }
}
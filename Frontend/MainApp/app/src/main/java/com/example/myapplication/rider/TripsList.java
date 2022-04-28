package com.example.myapplication.rider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.myapplication.*;
import com.example.myapplication.app.AppController;
import com.example.myapplication.endpoints.Endpoints;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

public class TripsList extends AppCompatActivity {

    private ListView listView;
    private JSONArray arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_trip_list);
        listView = findViewById(R.id.trip_Info_List);
        setList();
    }

    public void setList() {
        try {
        String url = Endpoints.GetRiderTrips + MainActivity.accountObj.getInt("id");
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
                    response -> {
                        Log.e("response", response.toString());

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                if (obj.getBoolean("hasStarted")){
                                    Intent intent = new Intent(this, OngoingTrip.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            }

                            if(response != null) {
                                arr = response;
                                listView.setAdapter(new TripsAdapter(arr, "TripsList", getApplicationContext()));
                            }

                        }catch(Exception e){}



                    },
                    error -> Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG));
            AppController.getInstance().addToRequestQueue(req, "array_req");
        }
        catch (Exception e){}

    }

}

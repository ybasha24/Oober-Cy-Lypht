package com.example.myapplication.rider;

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

import org.json.JSONArray;

public class TripsList extends AppCompatActivity {

    private ListView listView;
    private JSONArray arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_trip_info);
        listView = findViewById(R.id.trip_Info_List);
        setList();
    }

    public void setList() {
        try {
        String url = Endpoints.GetRiderTrips + MainActivity.accountObj.getInt("id");
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
                    response -> {
                        Log.e("response", response.toString());
                        if(response != null) {
                            arr = response;
                            listView.setAdapter(new TripsAdapter(arr,"TripsList",getApplicationContext()));
                        }
                    },
                    error -> Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG));
            AppController.getInstance().addToRequestQueue(req, "array_req");
        }
        catch (Exception e){}

    }

}

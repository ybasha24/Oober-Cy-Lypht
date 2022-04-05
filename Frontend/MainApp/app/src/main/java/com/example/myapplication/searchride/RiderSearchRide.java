package com.example.myapplication.searchride;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.app.AppController;
import com.example.myapplication.createride.SelectRidePlace;
import com.example.myapplication.createride.SelectRideTime;
import com.example.myapplication.driver.DriverHomePage;
import com.google.android.material.slider.Slider;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.example.myapplication.endpoints.endpoints;

public class RiderSearchRide extends AppCompatActivity {
    private ListView listWidget;
    int searchRadius;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String originAddress;
    String destAddress;
    int durationHours;
    int durationMinutes;

    public static JSONArray arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_search_ride);
        listWidget = (ListView)findViewById(R.id.listWidget);
        Slider slider = (Slider)findViewById(R.id.searchRadiusSlider);
        TextView tv = (TextView)findViewById(R.id.searchRadius);

        slider.addOnChangeListener((slider1, value, fromUser) -> {
            searchRadius = (int) value;
            tv.setText("Search radius: " + (int) value + " miles");

        });
    }

    public void searchTrips(View v) throws JSONException {
        if(searchRadius <= 0)
        {
            runOnUiThread(()->Toast.makeText(this, "Enter a valid search radius", Toast.LENGTH_LONG).show());
        }
        else
        {
            arr = new JSONArray();
            if(SelectRideTime.datettime != null){
                startDate = SelectRideTime.datettime;
            }
            if(startDate != null && (SelectRidePlace.durationHours != 0 || SelectRidePlace.durationMinutes != 0)){
                durationHours = SelectRidePlace.durationHours;
                durationMinutes = SelectRidePlace.durationMinutes;
                endDate = SelectRideTime.datettime.plusHours(durationHours).plusMinutes(durationMinutes);
            }
            if(SelectRidePlace.originAddress != null){
                originAddress = SelectRidePlace.originAddress;
            }
            if(SelectRidePlace.destAddress != null){
                destAddress = SelectRidePlace.destAddress;
            }

            String url = endpoints.RiderSearchTripUrl + startDate + "&scheduledEndDate=" + endDate;
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
                    response -> {
                        try{
                            arr = response;
                            Log.d("Success! ","Success for ArrayReq");
                            sortResults();
                        }
                        catch (Exception e)
                        {
                            runOnUiThread(()-> Toast.makeText(this, "Failed " + e, Toast.LENGTH_LONG).show());
                        }
                    },
                    error -> {runOnUiThread(()-> Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show());
                    });
            AppController.getInstance().addToRequestQueue(req, "post_object_tag");

        }
    }

    void sortResults()
    {
    int x = 1;
    }


}

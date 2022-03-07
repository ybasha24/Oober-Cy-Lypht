package com.example.myapplication.selectride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.app.AppController;
import com.example.myapplication.driver.DriverHomePage;
import com.google.android.material.slider.Slider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class ConfirmRide extends AppCompatActivity {

    public TextView details;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String startAddress;
    String endAddress;
    int pickupRadius;
    int dropoffRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_ride);

        Slider pickupRadiusSlider = findViewById(R.id.pickupRadiusSlider);
        Slider dropoffRadiusSlider = findViewById(R.id.dropoffRadiusSlider);
        TextView pickupRadiusTV = findViewById(R.id.pickupRadiusTV);
        TextView dropoffRadiusTV = findViewById(R.id.dropoffRadiusTV);

        pickupRadiusSlider.addOnChangeListener((slider1, value, fromUser) -> {
            pickupRadius = (int) value;
            pickupRadiusTV.setText("Pickup radius: " + pickupRadius + " miles");
        });
        dropoffRadiusSlider.addOnChangeListener((slider1, value, fromUser) -> {
            dropoffRadius = (int) value;
            dropoffRadiusTV.setText("Dropoff radius: " + dropoffRadius + " miles");
        });

        if(SelectRideTime.datettime != null){
            startDate = SelectRideTime.datettime;
        }
        if(SelectRideTime.datettime != null && SelectRideLocation.durationHours != 0 && SelectRideLocation.durationMinutes != 0){
            endDate = SelectRideTime.datettime.plusHours(SelectRideLocation.durationHours).plusMinutes(SelectRideLocation.durationMinutes);
        }
        if(startAddress != null){
            startAddress = SelectRideLocation.originAddress;
        }
        if(endAddress != null){
            endAddress = SelectRideLocation.destAddress;
        }

        details = findViewById(R.id.tripDetailsTV);
        if(startDate != null && endDate != null && startAddress != null && endAddress != null)
            details.setText("Start time: " + startDate.toString() + "\n" +
                            "End time: " + endDate.toString() + "\n" +
                            "Start address: " + startAddress + "\n" +
                            "End address: " + endAddress);
    }

    public void confirm(View v) throws JSONException {

        JSONObject obj = new JSONObject();

        obj.put("driverId", 29);
        obj.put("scheduleStartDate", startDate);
        obj.put("scheduledEndDate", startDate);
        obj.put("hasADriver", true);
        obj.put("startAddress", "Add");
        obj.put("startCity", "City");
        obj.put("startState", "State");
        obj.put("startZip", "Zip");
        obj.put("endAddress", "Add");
        obj.put("endCity", "City");
        obj.put("endState", "State");
        obj.put("endZip", "Zip");
        obj.put("driverPickupRadius", 1);
        obj.put("driverDropOffRadius", 1);

        String url = "http://coms-309-030.class.las.iastate.edu:8080/trip/createTripByDriver";

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj,
            response -> {
                Log.e("Trip", response.toString());
            },
            error -> {
                Log.e("Trip", error.toString());
            });
        AppController.getInstance().addToRequestQueue(req, "obj_req");
    }
}
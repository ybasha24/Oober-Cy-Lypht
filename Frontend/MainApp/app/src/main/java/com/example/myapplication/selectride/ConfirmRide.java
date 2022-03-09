package com.example.myapplication.selectride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.sql.Driver;
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
        TextView pickupRadiusTV = findViewById(R.id.pickupRadiusTV);

        pickupRadiusSlider.addOnChangeListener((slider1, value, fromUser) -> {
            pickupRadius = (int) value;
            pickupRadiusTV.setText("Pickup radius: " + pickupRadius + " miles");
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
        obj.put("driver", MainActivity.accountObj);
        obj.put("scheduleStartDate", startDate);
        obj.put("scheduledEndDate", startDate);
        obj.put("startAddress", "Address");
        obj.put("startCity", "City");
        obj.put("startState", "State");
        obj.put("startZip", "Zip");
        obj.put("endAddress", "Address");
        obj.put("endCity", "City");
        obj.put("endState", "State");
        obj.put("endZip", "Zip");
        obj.put("pickupRadius", 1);

        String url = "http://coms-309-030.class.las.iastate.edu:8080/trip/createTripByDriver?driverId=" + MainActivity.accountObj.getInt("id");

        Log.e("Trip", url);
        Log.e("Trip", obj.toString());
        StringRequest req = new StringRequest(Request.Method.POST, url,
            response -> {
                Intent intent = new Intent(this, DriverHomePage.class);
                startActivity(intent);
                Toast toast = Toast.makeText(getApplicationContext(), "Trip successfully created", Toast.LENGTH_LONG);
                toast.show();
            },
            error -> {
                Toast toast = Toast.makeText(getApplicationContext(), "Error creating trip", Toast.LENGTH_LONG);
                toast.show();
            }){
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return obj.toString().getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) { return new byte[0]; }
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        AppController.getInstance().addToRequestQueue(req, "obj_req");
    }
}
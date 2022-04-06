package com.example.myapplication.createride;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.app.AppController;
import com.example.myapplication.driver.DriverHomePage;
import com.google.android.material.slider.Slider;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import com.example.myapplication.endpoints.endpoints;

public class ConfirmRide extends AppCompatActivity {

    private TextView details;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String originAddress;
    private String destAddress;
    private int radius = 0;
    private int maxRiders = 0;
    private double rate = 0;
    private int durationHours;
    private int durationMinutes;

    private TextView radiusTV;
    private Slider radiusSlider;

    private TextView maxRidersTV;
    private Slider maxRidersSlider;

    private TextView rateTV;
    private Slider rateSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_ride);

        radiusSlider = findViewById(R.id.radiusSlider);
        radiusTV = findViewById(R.id.radiusTV);

        maxRidersTV = findViewById(R.id.maxRidersTV);
        maxRidersSlider = findViewById(R.id.maxRidersSlider);

        rateTV = findViewById(R.id.rateTV);
        rateSlider = findViewById(R.id.rateSlider);


        radiusSlider.addOnChangeListener((slider1, value, fromUser) -> {
            radius = (int) value;
            radiusTV.setText("Pickup radius: " + (int) value + " miles");
        });

        maxRidersSlider.addOnChangeListener((slider1, value, fromUser) -> {
            maxRiders = (int) value;
            maxRidersTV.setText("Max riders: " + (int) value);
        });

        rateSlider.addOnChangeListener((slider1, value, fromUser) -> {
            rate = (double) value;
            rateTV.setText("$" + (double) value + " per minute");
        });

        if(SelectRideTime.startDate != null){
            startDate = SelectRideTime.startDate;
        }
        if(startDate != null && (SelectRidePlace.durationHours != 0 || SelectRidePlace.durationMinutes != 0)){
            durationHours = SelectRidePlace.durationHours;
            durationMinutes = SelectRidePlace.durationMinutes;
            endDate = SelectRideTime.startDate.plusHours(durationHours).plusMinutes(durationMinutes);
        }
        if(SelectRidePlace.originAddress != null){
            originAddress = SelectRidePlace.originAddress;
        }
        if(SelectRidePlace.destAddress != null){
            destAddress = SelectRidePlace.destAddress;
        }

        details = findViewById(R.id.tripDetailsTV);
        if(startDate != null && endDate != null && originAddress != null && destAddress != null)
            details.setText("Start time: " + prettyHoursAndMinutes(startDate.getHour(), startDate.getMinute()) + "\n" +
                            "End time: " + prettyHoursAndMinutes(endDate.getHour(), endDate.getMinute()) + "\n" +
                            "Duration: " + prettyDistance(durationHours, durationMinutes) + "\n" +
                            "Origin: " + originAddress + "\n" +
                            "Destination: " + destAddress + "\n" +
                            "Distance: " + SelectRidePlace.distance + " km");
    }

    /**
     * Confirms ride details and creates ride through the remote server
     * @param v the activity that is referencing this method
     * @throws JSONException
     */
    public void confirm(View v) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("scheduledStartDate", startDate);
        obj.put("scheduledEndDate", endDate);
        obj.put("originAddress", originAddress);
        obj.put("destAddress", destAddress);
        obj.put("radius", radius);
        obj.put("maxNumberOfRiders", maxRiders);
        obj.put("ratePerMin", rate);

        Log.e("trips error", startDate + " " + endDate + " " + originAddress + " " + destAddress);

        String url = endpoints.DriverCreateTripUrl + MainActivity.accountObj.getInt("id");
        int verb = Request.Method.POST;

        try {
            if ((boolean) getIntent().getSerializableExtra("editing")) {
                url = endpoints.EditTripUrl;
                url += ("?tripId=" + (int) getIntent().getSerializableExtra("tripId"));
                verb = Request.Method.PUT;
            }
        } catch(Exception e){}

        StringRequest req = new StringRequest(verb, url,
            response -> {
                Intent intent = new Intent(this, DriverHomePage.class);
                this.startActivity(intent);
                String toastText = "Successfully created trip";
                try{
                    if ((boolean) getIntent().getSerializableExtra("editing")) {
                        toastText = "Successfully edited trip";
                    }
                } catch(Exception e) {}
                Toast toast = Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_LONG);
                toast.show();
            },
            error -> {
                Toast toast = Toast.makeText(getApplicationContext(), "Error creating trip", Toast.LENGTH_LONG);
                toast.show();
                Log.e("trips error", error.toString());
            }){
            @Override
            public byte[] getBody() {
                try {
                    return obj.toString().getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) { return new byte[0]; }
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        AppController.getInstance().addToRequestQueue(req, "string_req");
    }

    private static String prettyHoursAndMinutes(int hour, int minute){
        if(hour > 12){
            if(minute >= 10) {
                return (hour - 12) + ":" + minute + " PM";
            }
            else{
                return (hour - 12) + ":0" + minute + " PM";
            }
        }
        else{
            if(minute >= 10) {
                return (hour) + ":" + minute + " PM";
            }
            else{
                return (hour) + ":0" + minute + " PM";
            }
        }
    }

    private static String prettyDistance(int durationHours, int durationMinutes){
        String s = "";
        if(durationHours == 1){
            s += durationHours + " hour";
        }
        else if(durationHours > 1){
            s += durationHours + " hours";
        }
        if(durationMinutes == 1){
            if(durationHours >= 1)
                s += ", " + durationMinutes + " minute";
            else
                s += durationMinutes + " minute";
        }
        else if(durationMinutes >= 1){
            if(durationHours >= 1)
                s += ", " + durationMinutes + " minutes";
            else
                s += durationMinutes + " minutes";
        }
        return s;
    }
}
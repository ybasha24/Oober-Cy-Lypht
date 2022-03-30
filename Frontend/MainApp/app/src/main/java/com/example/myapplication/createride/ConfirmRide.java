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

    public TextView details;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String originAddress;
    String destAddress;
    int pickupRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_ride);

        Slider pickupRadiusSlider = findViewById(R.id.pickupRadiusSlider);
        TextView pickupRadiusTV = findViewById(R.id.pickupRadiusTV);

        pickupRadiusSlider.addOnChangeListener((slider1, value, fromUser) -> {
            pickupRadius = (int) value;
            pickupRadiusTV.setText("Pickup radius: " + (int) value + " miles");
        });

        if(SelectRideTime.datettime != null){
            startDate = SelectRideTime.datettime;
        }
        if(SelectRideTime.datettime != null && SelectRidePlace.durationHours != 0 && SelectRidePlace.durationMinutes != 0){
            endDate = SelectRideTime.datettime.plusHours(SelectRidePlace.durationHours).plusMinutes(SelectRidePlace.durationMinutes);
        }
        if(originAddress != null){
            originAddress = SelectRidePlace.originAddress;
        }
        if(destAddress != null){
            destAddress = SelectRidePlace.destAddress;
        }

        details = findViewById(R.id.tripDetailsTV);
        if(startDate != null && endDate != null && originAddress != null && destAddress != null)
            details.setText("Start time: " + startDate + "\n" +
                            "End time: " + endDate + "\n" +
                            "Start address: " + originAddress + "\n" +
                            "End address: " + destAddress);
    }

    public void confirm(View v) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("driver", MainActivity.accountObj);
        obj.put("scheduleStartDate", startDate);
        obj.put("scheduledEndDate", startDate);
        obj.put("originAddress", originAddress);
        obj.put("destAddress", destAddress);
//        obj.put("pickupRadius", 1);

        String url;
        int verb = Request.Method.POST;
        url = endpoints.DriverCreateTripUrl + MainActivity.accountObj.getInt("id");
        try {
            if ((boolean) getIntent().getSerializableExtra("editing")) {
                url = endpoints.EditTripUrl;
                url += ("?tripId=" + (int) getIntent().getSerializableExtra("tripId"));
                Log.e("trips error", url);
                verb = Request.Method.PUT;
            }
        } catch(Exception e){}

        StringRequest req = new StringRequest(verb, url,
            response -> {
                Intent intent = new Intent(this, DriverHomePage.class);
                startActivity(intent);
<<<<<<< HEAD
                runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Trip successfully created", Toast.LENGTH_LONG).show());
=======
                try{
                    if ((boolean) getIntent().getSerializableExtra("editing")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Trip successfully edited", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Trip successfully created", Toast.LENGTH_LONG);
                        toast.show();
                    }
                } catch(Exception e) {}
>>>>>>> e59f7d4 (Need to communicate with backend regarding editTripById endpoint)
            },
            error -> {
                runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Error creating trip", Toast.LENGTH_LONG).show());
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
        AppController.getInstance().addToRequestQueue(req, "obj_req");
    }
}
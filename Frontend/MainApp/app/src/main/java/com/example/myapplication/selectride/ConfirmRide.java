package com.example.myapplication.selectride;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.app.AppController;
import com.example.myapplication.driver.DriverHomePage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class ConfirmRide extends AppCompatActivity {

    public TextView details;
    LocalDateTime startDate = SelectRideTime.datettime;
    LocalDateTime endDate = SelectRideTime.datettime.plusHours(SelectRideLocation.durationHours).plusMinutes(SelectRideLocation.durationMinutes);
    String startAddress = SelectRideLocation.originAddress;
    String endAddress = SelectRideLocation.destAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_ride);

        details = findViewById(R.id.tripDetailsTV);
        details.setText("Start time: " + startDate.toString() + "\n" +
                        "End time: " + endDate.toString() + "\n" +
                        "Start address: " + startAddress + "\n" +
                        "End address: " + endAddress);
    }

//    public void confirm(View v) throws JSONException {
//
//        JSONObject obj = new JSONObject();
//        obj.put("driverId", MainActivity.accountObj.getInt("driverId"));
//        obj.put("scheduleStartDate",startDate);
//        obj.put("scheduledEndDate", endDate);
//        obj.put("hasADriver", true);
//        obj.put("startAddress", startAddress);
//        obj.put("endAddress", endAddress);
//        obj.put("driverPickupRadius", 1);
//        obj.put("driverDropOffRadius", 1);
//
//        String url = "http://coms-309-030.class.las.iastate.edu:8080/driver/registerDriver/";
//
//        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj,
//                response -> {((TextView) findViewById(R.id.regStatusTextView)).setText(response.toString());
//                    try {
//                        Intent intent = new Intent(this, DriverHomePage.class);
//                        int id = Integer.parseInt(response.getString("id"));
//                        if (id != 0) {
//                            intent.putExtra("obj", response.toString());
//                            startActivity(intent);
//                        } else {
//                            ((TextView) findViewById(R.id.regStatusTextView)).setText("Email already exists");
//                        }
//                    }
//                    catch(JSONException e){
//                        ((TextView) findViewById(R.id.regStatusTextView)).setText("JSON Exception Error.");
//                    }
//                },
//                error -> ((TextView) findViewById(R.id.regStatusTextView)).setText("Error!"))
//        {
//        };
//        AppController.getInstance().addToRequestQueue(req, "obj_req");
//    }
}
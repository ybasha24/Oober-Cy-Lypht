package com.example.myapplication.selectride;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
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

public class ConfirmRide extends AppCompatActivity {

    public TextView details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_ride);

        details = findViewById(R.id.tripDetailsTV);
        details.setText("Time: " + SelectRideTime.time + "\n" +
                        "Date: " + SelectRideTime.date + "\n" +
                        "From: " + SelectRideLocation.originString + "\n" +
                        "To: " + SelectRideLocation.destString);
    }

    public void confirm(View v) throws JSONException {
//        int driverId = MainActivity.accountObj.getInt("driverId");
//        LocalDateTime scheduledStartDate = SelectRideTime.datettime;
//        LocalDateTime scheduledEndDate,
//        boolean hasADriver = true;
//        String startAddress,
//        String startCity,
//        String startState,
//        String startZip,
//        String endAddress,
//        String endCity,
//        String endState,
//        String endZip,
//        int driverPickupRadius,
//        int driverDropOffRadius
    }

    public void register(View view) throws JSONException {
        String firstName = ((EditText) findViewById(R.id.editTextFirstName)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.editTextLastName)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
        String phone = ((EditText) findViewById(R.id.editTextPhone)).getText().toString();
        String address = ((EditText) findViewById(R.id.editTextAddress)).getText().toString();
        String state = ((EditText) findViewById(R.id.editTextState)).getText().toString();
        String zip = ((EditText) findViewById(R.id.editTextZip)).getText().toString();


        JSONObject obj = new JSONObject();
        obj.put("firstName", firstName);
        obj.put("lastName", lastName);
        obj.put("address", address);
        obj.put("city", "Ames");
        obj.put("state", state);
        obj.put("zip", zip);
        obj.put("email", email);
        obj.put("phoneNumber", phone);
        obj.put("password", password);

        String url = "http://coms-309-030.class.las.iastate.edu:8080/driver/registerDriver/";


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj,
                response -> {((TextView) findViewById(R.id.regStatusTextView)).setText(response.toString());

                    try {
                        Intent intent = new Intent(this, DriverHomePage.class);
                        int id = Integer.parseInt(response.getString("id"));
                        if (id != 0) {
                            intent.putExtra("obj", response.toString());
                            startActivity(intent);
                        } else {
                            ((TextView) findViewById(R.id.regStatusTextView)).setText("Email already exists");
                        }
                    }
                    catch(JSONException e){
                        ((TextView) findViewById(R.id.regStatusTextView)).setText("JSON Exception Error.");
                    }
                },
                error -> ((TextView) findViewById(R.id.regStatusTextView)).setText("Error!"))
        {
        };
        AppController.getInstance().addToRequestQueue(req, "obj_req");
    }
}
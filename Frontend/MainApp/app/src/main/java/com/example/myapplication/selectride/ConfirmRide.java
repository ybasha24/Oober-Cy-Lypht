package com.example.myapplication.selectride;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.driver.DriverHomePage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

    public void home(View v){
        Intent i = new Intent(this, DriverHomePage.class);
        startActivity(i);
    }
}
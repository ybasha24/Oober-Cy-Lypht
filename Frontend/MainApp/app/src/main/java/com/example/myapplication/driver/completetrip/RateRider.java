package com.example.myapplication.driver.completetrip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myapplication.*;
import com.example.myapplication.driver.TripDetail;

public class RateRider extends AppCompatActivity {

    TextView tv;
    EditText comments;
    RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_rate_rider);

        tv = findViewById(R.id.ratingNameTV);
        tv.setText("Rating: " + RateRidersAdapter.currentRiderString + "; id = " + TripDetail.nameToIdMap.get(RateRidersAdapter.currentRiderString));

        comments = findViewById(R.id.driverCommentingRiderET);
        rating = findViewById(R.id.driverRatingRiderBar);
    }

    public void submitRating(){
        //make 2 requests
    }
}
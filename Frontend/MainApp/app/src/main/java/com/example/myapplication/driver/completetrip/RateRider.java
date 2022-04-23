package com.example.myapplication.driver.completetrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.*;
import com.example.myapplication.app.AppController;
import com.example.myapplication.driver.HomePage;
import com.example.myapplication.driver.TripDetail;
import com.example.myapplication.endpoints.Endpoints;

import org.json.JSONObject;

public class RateRider extends AppCompatActivity {

    TextView tv;
    EditText comments;
    RatingBar rating;
    int driverId;
    int riderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_rate_rider);
        try {
            driverId = MainActivity.accountObj.getInt("id");
            riderId = TripDetail.nameToIdMap.get(RateRidersAdapter.currentRiderString);

        }catch(Exception e){}
        tv = findViewById(R.id.ratingNameTV);
        tv.setText("Rating: " + RateRidersAdapter.currentRiderString + "; id = " + riderId);

        comments = findViewById(R.id.driverCommentingRiderET);
        rating = findViewById(R.id.driverRatingRiderBar);
    }

    public void submitRating(View view){

        String rateUrl = "http://coms-309-030.class.las.iastate.edu:8080/rating/createRating?raterId=" + driverId + "&ratedId=" + riderId;

        JSONObject obj = new JSONObject();
        try {
            obj.put("rating", rating.getRating());
        } catch(Exception e){ }

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, rateUrl, obj,
                response -> submitReview(),
                error -> runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG).show()));
        AppController.getInstance().addToRequestQueue(req, "post_object_tag");
    }

    public void submitReview() {

        String reviewUrl = "http://coms-309-030.class.las.iastate.edu:8080/riderReview/postRiderReview?driverId=" + driverId + "&riderId=" + riderId;

        JSONObject obj = new JSONObject();
        try {
            obj.put("review", comments.getText().toString());
        } catch (Exception e) { }

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, reviewUrl, obj,
                response -> {
                    RateRidersAdapter.finishRating(RateRidersAdapter.currentPosition);
                    Intent intent = new Intent(this, TripCompleted.class);
                    startActivity(intent);
                },
                error -> runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG).show()));
        AppController.getInstance().addToRequestQueue(req, "post_object_tag");
    }
}
package com.example.myapplication.rider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.*;
import com.example.myapplication.app.AppController;

import org.json.JSONObject;

import java.util.ArrayList;

public class Reviews extends AppCompatActivity {

    private TextView ratingTV;
    private TextView reviewsTV;
    private ArrayList<String> reviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_reviews);

        ratingTV = findViewById(R.id.riderRatingTV);
        reviewsTV = findViewById(R.id.riderReviewsTV);
        reviews = new ArrayList<>();

        getRating();
        getReviews();
    }

    private void getRating() {
        String url = "http://coms-309-030.class.las.iastate.edu:8080/rating/getUserRating?userId=1";

        StringRequest req = new StringRequest(Request.Method.GET, url,
            response -> ratingTV.setText("Rating: " + response),
            error -> Log.e("error", error.toString())
        );
        AppController.getInstance().addToRequestQueue(req, "get_string_req");
    }

    private void getReviews() {
        String url = "http://coms-309-030.class.las.iastate.edu:8080/riderReview/getAllRiderReviewsByRiderId?riderId=1";

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            reviews.add(obj.getString("review"));
                        }
                    }catch(Exception e){}
                    prettyPrintReviews();
                },
                error -> Log.e("error", error.toString())
        );
        AppController.getInstance().addToRequestQueue(req, "get_array_req");
    }

    private void prettyPrintReviews(){
        String setme = "Reviews: \n\n";
        for(String review : reviews){
            setme += "\t\t- " + review + "\n\n";
        }
        reviewsTV.setText(setme);
    }
}
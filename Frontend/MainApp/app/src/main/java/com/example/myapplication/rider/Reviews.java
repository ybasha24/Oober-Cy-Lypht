package com.example.myapplication.rider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.*;

import org.json.JSONArray;

public class Reviews extends AppCompatActivity {

    private TextView ratingTV;
    private TextView reviewsTV;
    private JSONArray reviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_reviews);

        ratingTV = findViewById(R.id.riderRatingTV);
        reviewsTV = findViewById(R.id.riderReviewsTV);

    }
}
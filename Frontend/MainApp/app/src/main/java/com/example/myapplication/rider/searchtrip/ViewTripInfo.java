package com.example.myapplication.rider.searchtrip;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.time.LocalDateTime;

public class ViewTripInfo extends AppCompatActivity {

    private static TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_ride_info);
        info = findViewById(R.id.InfoView);
        LocalDateTime startDate =(LocalDateTime) getIntent().getSerializableExtra("startDate");
        String startLocation = getIntent().getStringExtra("startLocation");
        String endLocation = getIntent().getStringExtra("endLocation");
        //info.setText(startDate);
    }


}

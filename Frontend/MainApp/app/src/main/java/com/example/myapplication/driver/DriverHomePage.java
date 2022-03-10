package com.example.myapplication.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.MainActivity;
import com.example.myapplication.ProfileSettings;
import com.example.myapplication.R;
import com.example.myapplication.createride.SelectRideTime;

public class DriverHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home_page);
    }

    public void createRide(View view){
        Intent intent = new Intent(this, SelectRideTime.class);
        startActivity(intent);
    }

    public void viewCreatedRides(View view){
        Intent intent = new Intent(this, DriverCreatedRides.class);
        startActivity(intent);
    }

    public void profileSettings(View view){
        Intent intent = new Intent(this, ProfileSettings.class);
        startActivity(intent);
    }

    public void signOut(View view){
        MainActivity.accountObj = null;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
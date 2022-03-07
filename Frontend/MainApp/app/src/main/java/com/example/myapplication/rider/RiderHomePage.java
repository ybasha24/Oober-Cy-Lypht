package com.example.myapplication.rider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.ProfileSettings;
import com.example.myapplication.R;
import com.example.myapplication.selectride.SelectRideTime;


public class RiderHomePage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_home_page);

        TextView tv = (TextView) findViewById(R.id.welcomeBackTV);
        tv.setText("Welcome back!");
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

    public void createRide(View view){
//        Intent intent = new Intent(this, SelectRideLocation.class);
        Intent intent = new Intent(this, SelectRideTime.class);
        startActivity(intent);
    }

}
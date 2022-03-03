package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

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
//        Intent intent = new Intent(this, MapsActivity.class);
        Intent intent = new Intent(this, SelectRideTime.class);
        startActivity(intent);
    }

}
package com.example.myapplication.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.MainActivity;
import com.example.myapplication.ProfileSettings;
import com.example.myapplication.*;
import com.example.myapplication.driver.createtrip.SelectTripTime;
import com.example.myapplication.endpoints.OtherConstants;

/**
 * home page of the driver
 */
public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home_page);
    }

    /**
     * allows for creating a ride by first asking for the time of the trip
     * @param view the activity that is referencing this method
     */
    public void createRide(View view){
        Intent intent = new Intent(this, SelectTripTime.class);
        startActivity(intent);
    }

    /**
     * views all the trips the driver has created and not finished
     * @param view the activity that is referencing this method
     */
    public void viewCreatedRides(View view){
        Intent intent = new Intent(this, TripsList.class);
        startActivity(intent);
    }

    /**
     * allows the driver to change their account settings
     * @param view the activity that is referencing this method
     */
    public void profileSettings(View view){
        Intent intent = new Intent(this, ProfileSettings.class);
        startActivity(intent);
    }

    /**
     * signs the driver out and resets local parameters; goes to login screen
     * @param view the activity that is referencing this method
     */
    public void signOut(View view){
        SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
        editor.putString("email", "");
        editor.putString("password", "");
        editor.putBoolean("isLoggedIn", false);
        editor.apply();

        MainActivity.accountObj = null;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
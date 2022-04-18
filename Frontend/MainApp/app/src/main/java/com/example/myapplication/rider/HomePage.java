package com.example.myapplication.rider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.ProfileSettings;
import com.example.myapplication.rider.searchtrip.SearchTripTime;
import com.example.myapplication.*;

/**
 * Home page of the rider
 */
public class HomePage extends AppCompatActivity {

    private ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_home_page);

        profilePic = (ImageView) findViewById(R.id.riderProfilePic);
        HelperFunctions.setProfilePic(profilePic);
    }

    /**
     * allows the rider to change their account settings
     * @param view the activity that is referencing this method
     */
    public void profileSettings(View view){
        Intent intent = new Intent(this, ProfileSettings.class);
        startActivity(intent);
    }

    /**
     * signs the rider out and resets local parameters; goes to login screen
     * @param view the activity that is referencing this method
     */
    public void signOut(View view) {
        SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
        editor.putString("email", "");
        editor.putString("password", "");
        editor.putBoolean("isLoggedIn", false);
        editor.apply();

        MainActivity.accountObj = null;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * allows for a ride to be searched
     * @param view the activity that is referencing this method
     */
    public void searchRides(View view)
    {
        Intent intent = new Intent(this, SearchTripTime.class);
        startActivity(intent);
    }

}
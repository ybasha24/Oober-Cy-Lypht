package com.example.myapplication.driver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.ProfileSettings;
import com.example.myapplication.*;
import com.example.myapplication.driver.createtrip.SelectTripTime;
import com.example.myapplication.endpoints.OtherConstants;
import com.google.android.gms.common.internal.Constants;


/**
 * home page of the driver
 */
public class HomePage extends AppCompatActivity {

    private ImageView profilePic;
    private String uriString;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home_page);

        setProfilePic();
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

    public void setProfilePic(){
        profilePic = (ImageView) findViewById(R.id.driverProfilePic);
        uriString = HelperFunctions.getProfilePic();
        Uri uri = Uri.parse(uriString);

        try {
            profilePic.setImageURI(uri);
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
    }

}
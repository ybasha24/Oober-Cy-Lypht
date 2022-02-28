package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;

public class DriverHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home_page);
        String username = null;
        TextView tv = (TextView) findViewById(R.id.welcomebackTV);

        try{
            username = MainActivity.userResp.getString("firstName");

            tv.setText("Welcome back " + username);
        }
        catch(JSONException e){
            tv.setText("Error has occured");
        }




    }
}
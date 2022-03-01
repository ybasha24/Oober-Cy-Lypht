package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DriverHomePage extends AppCompatActivity {
    JSONObject obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home_page);
        String username = null;

        String objString = getIntent().getStringExtra("obj");
        obj = null;
        try{
            obj = new JSONObject(objString);
        } catch(JSONException e){}

        TextView tv = (TextView) findViewById(R.id.welcomebackTV);

        try{
            username = obj.getString("firstName");

            tv.setText("Welcome back " + username);
        }
        catch(JSONException e){
            tv.setText("Error has occured");
        }
    }
}
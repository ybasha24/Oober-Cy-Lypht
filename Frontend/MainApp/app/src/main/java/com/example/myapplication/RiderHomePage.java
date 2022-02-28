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

    JSONObject obj;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_home_page);

        String objString = getIntent().getStringExtra("obj");
        obj = null;
        try{
            obj = new JSONObject(objString);
        } catch(JSONException e){}

        TextView tv = (TextView) findViewById(R.id.welcomeBackTV);
        try {
            tv.setText("Welcome back " + obj.getString("firstName") + "!");
        } catch(JSONException e){
            tv.setText("Welcome back!");
        }
    }

    public void profileSettings(View view){
        Intent intent = new Intent(this, ProfileSettings.class);
        intent.putExtra("obj", obj.toString());
        startActivity(intent);
    }
}
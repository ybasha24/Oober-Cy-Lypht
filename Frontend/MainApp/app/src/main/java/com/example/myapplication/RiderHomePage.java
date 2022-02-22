package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class RiderHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_home_page);

//        String username = getIntent().getStringExtra("username");
//        TextView tv = (TextView) findViewById(R.id.welcomeBackTV);
//        tv.setText("Welcome back " + username + "!");
    }
}
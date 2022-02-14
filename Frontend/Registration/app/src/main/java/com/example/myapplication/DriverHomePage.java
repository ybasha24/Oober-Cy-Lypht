package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DriverHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home_page);
        String username = getIntent().getStringExtra("username");

        TextView tv = (TextView) findViewById(R.id.welcomebackTV);
        tv.setText("Welcome back " + username);

    }
}
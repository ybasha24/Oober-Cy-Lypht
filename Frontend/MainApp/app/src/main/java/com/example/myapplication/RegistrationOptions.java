package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.driver.DriverRegistrationPage;
import com.example.myapplication.rider.RiderRegistrationPage;

public class RegistrationOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_option);
    }

    public void riderRegistration(View view){
        Intent intent = new Intent(this, RiderRegistrationPage.class);
        startActivity(intent);
    }

    public void driverRegistration(View view){
        Intent intent = new Intent(this, DriverRegistrationPage.class);
        startActivity(intent);
    }
}

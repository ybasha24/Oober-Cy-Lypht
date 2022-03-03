package com.example.myapplication.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONException;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class DriverHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home_page);

        TextView tv = findViewById(R.id.welcomebackTV);

        try{
            String username = MainActivity.accountObj.getString("firstName");
            tv.setText("Welcome back " + username);
        }
        catch(JSONException e){
            tv.setText("Error has occured");
        }
    }
}
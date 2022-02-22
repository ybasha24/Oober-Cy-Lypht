package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.app.AppController;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signIn(View view){
        EditText usernameInput = (EditText) findViewById(R.id.usernameInput);
        EditText passwordInput = (EditText) findViewById(R.id.passwordInput);
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        if(username.equals("rider")) {
            Intent intent = new Intent(this, RiderHomePage.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
        else if(username.equals("driver")){
            Intent intent = new Intent(this, DriverHomePage.class);
            startActivity(intent);
        }
    }

    public void register(View view){
        Intent intent = new Intent(this, RiderRegistrationPage.class);
        startActivity(intent);
    }
}
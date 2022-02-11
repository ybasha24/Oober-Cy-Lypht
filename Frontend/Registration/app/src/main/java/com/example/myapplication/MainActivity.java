package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

import java.sql.Driver;


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
//        if(user == "rider" && pass == "rider"){
            Intent intent = new Intent(this, DriverHomePage.class);
            intent.putExtra("username", username);
            startActivity(intent);
//        }
//        else if(user == "driver" && pass == "driver"){
//            Intent intent = new Intent(this, DriverHomePage.class);
//            startActivity(intent);
//        }
    }
}
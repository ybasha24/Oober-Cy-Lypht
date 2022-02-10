package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signIn(View view){
        EditText username = (EditText) findViewById(R.id.usernameInput);
        EditText password = (EditText) findViewById(R.id.passwordInput);
        if(username.getText().toString() == "rider" && password.getText().toString() == "password"){
            Intent intent = new Intent(this, RiderHomePage.class);
            startActivity(intent);
        }
    }
}
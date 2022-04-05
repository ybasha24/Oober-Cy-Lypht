package com.example.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class AdminHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
    }

    public void manageUsers(View view){
        Intent intent = new Intent(this, UsersList.class);
        startActivity(intent);
    }

    public void manageTrips(View view){
        Intent intent = new Intent(this, TripsList.class);
        startActivity(intent);
    }

    public void signOut(View view) {
        SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
        editor.putString("email", "");
        editor.putString("password", "");
        editor.putBoolean("isLoggedIn", false);
        editor.apply();

        MainActivity.accountObj = null;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
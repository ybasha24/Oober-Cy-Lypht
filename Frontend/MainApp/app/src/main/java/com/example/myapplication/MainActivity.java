package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.admin.AdminHomePage;
import com.example.myapplication.endpoints.endpoints;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.myapplication.app.AppController;
import com.example.myapplication.driver.DriverHomePage;
import com.example.myapplication.rider.RiderHomePage;


public class MainActivity extends AppCompatActivity {

    public static JSONObject accountObj;
    SharedPreferences prefs;
    boolean isLoggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("name", MODE_PRIVATE);
        isLoggedIn = prefs.getBoolean("isLoggedIn", false);

        setContentView(R.layout.activity_main);

        if(isLoggedIn){
            String email = prefs.getString("email", "");
            String password = prefs.getString("password", "");
            signInRequest(email, password);
        }
        else{
            setContentView(R.layout.activity_main);
        }
    }

    public void signIn(View view) {
        String email = ((EditText) findViewById(R.id.usernameInput)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordInput)).getText().toString();
        signInRequest(email, password);
    }

    public void register(View view){
        Intent intent = new Intent(this, RegistrationOptions.class);
        startActivity(intent);
    }

    public void signInRequest(String email, String password){
        String url = endpoints.LoginUrl + email + "&password=" + password;

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                try {
                    accountObj = response;
                    Intent intent = null;
                    if(!accountObj.isNull("firstName")){
                        if(!(accountObj.isNull("adriver")) && accountObj.getBoolean("adriver"))
                            intent = new Intent(this, DriverHomePage.class);
                        else if (!accountObj.isNull("arider") && accountObj.getBoolean("arider"))
                            intent = new Intent(this, RiderHomePage.class);
                        else if (!(accountObj.isNull("anAdmin")) && accountObj.getBoolean("anAdmin"))
                            intent = new Intent(this, AdminHomePage.class);
                        if(((CheckBox) findViewById(R.id.staySignedInCheckBox)).isChecked()){
                            SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
                            editor.putString("email", email);
                            editor.putString("password", password);
                            editor.putBoolean("isLoggedIn", true);
                            editor.apply();
                        }
                        startActivity(intent);
                    }
                    else {
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Error processing" + url, Toast.LENGTH_LONG).show());
                    }
                }
                catch(JSONException e){
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Exception: " + e, Toast.LENGTH_LONG).show());
                }
            },
            error ->  runOnUiThread(()->Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG).show()));
        AppController.getInstance().addToRequestQueue(req, "post_object_tag");
    }
}
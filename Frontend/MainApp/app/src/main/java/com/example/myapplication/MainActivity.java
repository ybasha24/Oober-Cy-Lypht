package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.app.AppController;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    static JSONObject accountObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

/*
Rider : abc@iastate.edu  pass: abc
Driver: xyz@iastate.edu pass: xyz
 */

    public void signIn(View view) throws JSONException{
        TextView tv = (TextView) findViewById(R.id.tv);
        JSONObject obj = new JSONObject();
        String email = ((EditText) findViewById(R.id.usernameInput)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordInput)).getText().toString();

        String url = "http://coms-309-030.class.las.iastate.edu:8080/user/getUserSignIn?email=" + email +
                "&" + "password=" + password;

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        accountObj = response;
                        Intent intent = null;
                        if(!accountObj.isNull("firstName")){
                            if(!(accountObj.isNull("adriver")) && accountObj.getBoolean("adriver") == true)
                                intent = new Intent(this, DriverHomePage.class);
                            else if (!accountObj.isNull("arider") && accountObj.getBoolean("arider") == true)
                                intent = new Intent(this, RiderHomePage.class);
                            startActivity(intent);
                        }
                        else {
                            tv.setText("Error processing " + url);
                        }
                    }
                    catch(JSONException e){
                        tv.setText("Error processing " + url);
                    }
                },
                error -> {
                    tv.setText("Error processing " + url);
                });
        AppController.getInstance().addToRequestQueue(req, "post_object_tag");
    }

    public void register(View view){
        Intent intent = new Intent(this, RegistrationOptions.class);
        startActivity(intent);
    }
}
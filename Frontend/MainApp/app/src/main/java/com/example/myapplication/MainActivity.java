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
import com.example.myapplication.net_utils.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Driver;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signIn(View view) throws JSONException {
        EditText usernameInput = (EditText) findViewById(R.id.usernameInput);
        EditText passwordInput = (EditText) findViewById(R.id.passwordInput);
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        String url = "https://41096e03-1605-4363-a912-57afa92f86c7.mock.pstmn.io/bumbo";
        JSONObject obj = new JSONObject();
        obj.put("username", username);
        obj.put("password", password);

        boolean success = false;
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj,
                response ->{
                    success = true;
                    Log.d("JSON Object Request", "Success");
                },
                error ->{
                    Log.d("JSON Object Request", "Error");
                }
        );
        AppController.getInstance().addToRequestQueue(req, "post_object_tag")

        if(success){
            Intent intent = new Intent(this, DriverHomePage.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
//        else if(user == "driver" && pass == "driver"){
//            Intent intent = new Intent(this, DriverHomePage.class);
//            startActivity(intent);
//        }
    }
}
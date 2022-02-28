package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DriverRegistrationPage extends AppCompatActivity {
    public static String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_registration_page);
    }

    public void register(View view) throws JSONException {
        String firstName = ((EditText) findViewById(R.id.editTextFirstName)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.editTextLastName)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
        String phone = ((EditText) findViewById(R.id.editTextPhone)).getText().toString();
        String address = ((EditText) findViewById(R.id.editTextAddress)).getText().toString();
        String state = ((EditText) findViewById(R.id.editTextState)).getText().toString();
        String zip = ((EditText) findViewById(R.id.editTextZip)).getText().toString();
        final String[] resp = new String[1];

        JSONObject obj = new JSONObject();
        obj.put("firstName", firstName);
        obj.put("lastName", lastName);
        obj.put("address", address);
        obj.put("city", "Ames");
        obj.put("state", state);
        obj.put("zip", zip);
        obj.put("email", email);
        obj.put("phoneNumber", phone);
        obj.put("password", password);
        //String city = ((EditText) findViewById(R.id.editTextCity)).getText().toString();

        String url = "http://coms-309-030.class.las.iastate.edu:8080/driver/registerDriver/";


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj,
                response -> {((TextView) findViewById(R.id.regStatusTextView)).setText(response.toString());
                    try {
                        Intent intent = new Intent(this, DriverHomePage.class);
                        String id = response.getString("id");
                        boolean b = id == null;
                        if (!response.isNull("id")) {
                            intent.putExtra("id", id);
                            startActivity(intent);
                            ((TextView) findViewById(R.id.regStatusTextView)).setText(id);
                        } else {
                            ((TextView) findViewById(R.id.regStatusTextView)).setText("Email already exists");
                        }
                    }
                        catch(JSONException e){
                            ((TextView) findViewById(R.id.regStatusTextView)).setText("JSON Exception Error.");
                        }
                        },
                error -> ((TextView) findViewById(R.id.regStatusTextView)).setText("Error!"))
        {
        };
        AppController.getInstance().addToRequestQueue(req, "obj_req");
    }

}

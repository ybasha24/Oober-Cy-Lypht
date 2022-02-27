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
        String resp;
        //String city = ((EditText) findViewById(R.id.editTextCity)).getText().toString();

        String url = "http://coms-309-030.class.las.iastate.edu:8080/driver/registerDriver/";


        StringRequest req = new StringRequest(Request.Method.POST, url,
                response -> {((TextView) findViewById(R.id.regStatusTextView)).setText("Success!");
                },
                error -> ((TextView) findViewById(R.id.regStatusTextView)).setText("Error!"))
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }
            @Override
            public byte[] getBody() {
                try {
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
                    String objString =  obj.toString();

                    return objString.toString().getBytes();
                }
                catch (Exception e){
                    return null;
                }
            }
        };
        AppController.getInstance().addToRequestQueue(req, "obj_req");

        Intent intent = new Intent(this, DriverHomePage.class);
        //intent.putExtra(resp, s);
        startActivity(intent);


    }

}

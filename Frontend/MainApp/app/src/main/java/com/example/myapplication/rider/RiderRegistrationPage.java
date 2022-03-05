package com.example.myapplication.rider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.MainActivity;
import com.example.myapplication.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;
import com.example.myapplication.R;

public class RiderRegistrationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rider_registration_page);
    }

    public void register(View view) throws JSONException {
        TextView tv = ((TextView) findViewById(R.id.regStatusTextView));
        String firstName = ((EditText) findViewById(R.id.editTextFirstName)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.editTextLastName)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
        String phoneNumber = ((EditText) findViewById(R.id.editTextPhone)).getText().toString();
        String address = ((EditText) findViewById(R.id.editTextAddress)).getText().toString();
        String city = ((EditText) findViewById(R.id.editTextCity)).getText().toString();
        String state = ((EditText) findViewById(R.id.editTextState)).getText().toString();
        String zip = ((EditText) findViewById(R.id.editTextZip)).getText().toString();

        String url = "http://coms-309-030.class.las.iastate.edu:8080/rider/registerRider/";
        JSONObject obj = new JSONObject();
        obj.put("firstName", firstName);
        obj.put("lastName", lastName);
        obj.put("address", address);
        obj.put("city", city);
        obj.put("state", state);
        obj.put("zip", zip);
        obj.put("email", email);
        obj.put("password", password);
        obj.put("phoneNumber", phoneNumber);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj,
            response -> {
                if(!response.isNull("firstName")){
                    Intent intent = new Intent(this, RiderHomePage.class);
                    MainActivity.accountObj = response;
                    startActivity(intent);
                }
                else {
                    tv.setText("Error processing registration.");
                }
            },
            error -> {
                tv.setText(error.toString());
            });
        AppController.getInstance().addToRequestQueue(req, "post_object_tag");
    }
}
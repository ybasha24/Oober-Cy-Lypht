package com.example.myapplication.driver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.MainActivity;
import com.example.myapplication.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.myapplication.R;
import com.example.myapplication.endpoints.endpoints;
import com.example.myapplication.HelperFunctions;

public class DriverRegistrationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_registration_page);
    }

    public void register(View view) throws JSONException {
        TextView tv = ((TextView) findViewById(R.id.regStatusTextView));
        String firstName = ((EditText) findViewById(R.id.editTextFirstName)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.editTextLastName)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
        String phone = ((EditText) findViewById(R.id.editTextPhone)).getText().toString();
        String address = ((EditText) findViewById(R.id.editTextAddress)).getText().toString();
        String state = ((EditText) findViewById(R.id.editTextState)).getText().toString();
        String city = ((EditText) findViewById(R.id.editTextCity)).getText().toString();
        String zip = ((EditText) findViewById(R.id.editTextZip)).getText().toString();


        JSONObject obj = new JSONObject();
        obj.put("firstName", firstName);
        obj.put("lastName", lastName);
        obj.put("address", address);
        obj.put("city", city);
        obj.put("state", state);
        obj.put("zip", zip);
        obj.put("email", email);
        obj.put("phoneNumber", phone);
        obj.put("password", password);

        boolean x = HelperFunctions.verifyNotNull(firstName, lastName, email, address, city, state, zip, password,
                phone, tv);

        boolean y = HelperFunctions.verifyParametersMet(password, email, phone, tv);

        if (x && y) {
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, endpoints.DriverRegUrl, obj,
                response -> {
                    if (!response.isNull("firstName")) {
                        Intent intent = new Intent(this, DriverHomePage.class);
                        MainActivity.accountObj = response;
                        startActivity(intent);
                    } else {
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Error processing" + obj, Toast.LENGTH_LONG).show());
                    }
                },
                error -> runOnUiThread(()->Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG).show()));
            AppController.getInstance().addToRequestQueue(req, "obj_req");
        }
    }




}

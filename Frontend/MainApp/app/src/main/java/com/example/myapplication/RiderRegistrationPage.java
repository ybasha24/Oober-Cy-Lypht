package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

public class RiderRegistrationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_registration_page);
    }

    public void register(View view) throws JSONException {
        EditText firstNameText = (EditText) findViewById(R.id.editTextFirstName);
        String firstName = firstNameText.getText().toString();
        String lastName = ((EditText) findViewById(R.id.editTextLastName)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordInput)).getText().toString();
        String phone = ((EditText) findViewById(R.id.editTextPhone)).getText().toString();
        String address = ((EditText) findViewById(R.id.editTextAddress)).getText().toString();
        String state = ((EditText) findViewById(R.id.editTextState)).getText().toString();
        String zip = ((EditText) findViewById(R.id.editTextZip)).getText().toString();

        String url = "https://41096e03-1605-4363-a912-57afa92f86c7.mock.pstmn.io/create";
        JSONObject obj = new JSONObject();
        obj.put("firstName", firstName);
        obj.put("lastName", lastName);
        obj.put("email", email);
        obj.put("password", password);
        obj.put("phone", phone);
        obj.put("address", address);
        obj.put("state", state);
        obj.put("zip", zip);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj,
            response ->{
                ((TextView) findViewById(R.id.regStatusTextView)).setText("Success!");
            },
            error ->{
                ((TextView) findViewById(R.id.regStatusTextView)).setText("Error!");
            }
        );
        AppController.getInstance().addToRequestQueue(req, "post_object_tag");

    }
}
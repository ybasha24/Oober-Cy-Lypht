package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        String objString = getIntent().getStringExtra("obj");
        JSONObject obj = null;
        try{
            obj = new JSONObject(objString);
        } catch(JSONException e){}

        EditText firstName = (EditText) findViewById(R.id.editTextFirstName2);
        EditText lastName = (EditText) findViewById(R.id.editTextLastName2);
        EditText password = (EditText) findViewById(R.id.editTextPassword2);
        EditText phoneNumber = (EditText) findViewById(R.id.editTextPhone2);
        EditText address = (EditText) findViewById(R.id.editTextAddress2);
        EditText city = (EditText) findViewById(R.id.editTextCity2);
        EditText state = (EditText) findViewById(R.id.editTextState2);
        EditText zip = (EditText) findViewById(R.id.editTextZip2);
        EditText email = (EditText) findViewById(R.id.editTextEmail2);

        try {
            firstName.setText(obj.getString("firstName"));
            lastName.setText(obj.getString("lastName"));
            password.setText(obj.getString("password"));
            phoneNumber.setText(obj.getString("phoneNumber"));
            address.setText(obj.getString("address"));
            city.setText(obj.getString("city"));
            state.setText(obj.getString("state"));
            zip.setText(obj.getString("zip"));
            email.setText(obj.getString("email"));
        } catch(JSONException e){}
    }
}
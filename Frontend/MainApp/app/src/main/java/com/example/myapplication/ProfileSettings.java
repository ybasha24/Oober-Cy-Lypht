package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ProfileSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

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
            firstName.setText(MainActivity.accountObj.getString("firstName"));
            lastName.setText(MainActivity.accountObj.getString("lastName"));
            password.setText(MainActivity.accountObj.getString("password"));
            phoneNumber.setText(MainActivity.accountObj.getString("phoneNumber"));
            address.setText(MainActivity.accountObj.getString("address"));
            city.setText(MainActivity.accountObj.getString("city"));
            state.setText(MainActivity.accountObj.getString("state"));
            zip.setText(MainActivity.accountObj.getString("zip"));
            email.setText(MainActivity.accountObj.getString("email"));
        } catch(JSONException e){}
    }

    public void saveChanges(View view){

        EditText firstName = (EditText) findViewById(R.id.editTextFirstName2);
        EditText lastName = (EditText) findViewById(R.id.editTextLastName2);
        EditText password = (EditText) findViewById(R.id.editTextPassword2);
        EditText phoneNumber = (EditText) findViewById(R.id.editTextPhone2);
        EditText address = (EditText) findViewById(R.id.editTextAddress2);
        EditText city = (EditText) findViewById(R.id.editTextCity2);
        EditText state = (EditText) findViewById(R.id.editTextState2);
        EditText zip = (EditText) findViewById(R.id.editTextZip2);
        EditText email = (EditText) findViewById(R.id.editTextEmail2);

        JSONObject newUserDetails = new JSONObject();
        try {
            newUserDetails.put("firstName", firstName.getText().toString());
            newUserDetails.put("lastName", lastName.getText().toString());
            newUserDetails.put("password", password.getText().toString());
            newUserDetails.put("phoneNumber", phoneNumber.getText().toString());
            newUserDetails.put("address", address.getText().toString());
            newUserDetails.put("city", city.getText().toString());
            newUserDetails.put("state", state.getText().toString());
            newUserDetails.put("zip", zip.getText().toString());
            newUserDetails.put("email", email.getText().toString());
        } catch(JSONException e) {}

        TextView status = (TextView) findViewById(R.id.statusTV);
        String url = "";

        try {
            url = "http://coms-309-030.class.las.iastate.edu:8080/user/editUser?id=" + MainActivity.accountObj.get("id");
        } catch(JSONException e) {}

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.PUT, url, newUserDetails,
                response -> {
                    try {
                        if(!response.isNull("firstName")){
                            MainActivity.accountObj = response;
                            status.setText("Success!");
                        }
                        else {
                            status.setText("Failed here");
                        }
                    }
                    catch(Exception e){
                        status.setText("Something went wrong...");
                    }
                },
                error -> {
                    status.setText(error.toString());
                });
        AppController.getInstance().addToRequestQueue(req, "post_object_tag");
    }
}
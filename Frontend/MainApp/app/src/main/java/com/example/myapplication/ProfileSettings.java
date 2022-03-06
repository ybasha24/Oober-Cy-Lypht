package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

    final int passwordLength = 8;

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

    public void saveChanges(View view) {
        TextView status = (TextView) findViewById(R.id.statusTV);
        EditText firstName = (EditText) findViewById(R.id.editTextFirstName2);
        EditText lastName = (EditText) findViewById(R.id.editTextLastName2);
        EditText password = (EditText) findViewById(R.id.editTextPassword2);
        EditText phoneNumber = (EditText) findViewById(R.id.editTextPhone2);
        EditText address = (EditText) findViewById(R.id.editTextAddress2);
        EditText city = (EditText) findViewById(R.id.editTextCity2);
        EditText state = (EditText) findViewById(R.id.editTextState2);
        EditText zip = (EditText) findViewById(R.id.editTextZip2);
        EditText email = (EditText) findViewById(R.id.editTextEmail2);

        int x = verifyNotNull(firstName.getText().toString(), lastName.getText().toString(),
                email.getText().toString(), address.getText().toString(), city.getText().toString(),
                state.getText().toString(), zip.getText().toString(), password.getText().toString(),
                phoneNumber.getText().toString(), status);
        int y = verifyParametersMet(password.getText().toString(), email.getText().toString(),
                status);

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
        } catch (JSONException e) {
        }

        if (x == 0 & y == 0) {
            String url = "";

            try {
                url = "http://coms-309-030.class.las.iastate.edu:8080/user/editUser?id=" + MainActivity.accountObj.get("id");
            } catch (JSONException e) {
            }

            JsonObjectRequest req = new JsonObjectRequest(Request.Method.PUT, url, newUserDetails,
                    response -> {
                        try {
                            if (!response.isNull("firstName")) {
                                MainActivity.accountObj = response;
                                status.setText("Success!");
                            } else {
                                status.setText("Failed here");
                            }
                        } catch (Exception e) {
                            status.setText("Something went wrong...");
                        }
                    },
                    error -> {
                        status.setText(error.toString());
                    });
            AppController.getInstance().addToRequestQueue(req, "post_object_tag");
        }
    }

    public int verifyNotNull(String firstName, String lastName, String email, String address,
                             String city, String state, String zip, String password,
                             String phoneNumber, TextView tv)
    {
        int errorFlag = 0;
        if(firstName.isEmpty() || (firstName.matches("^\\S*$") == false))
        {
            tv.setText("Please enter a first name without white-spaces");
            errorFlag = 1;
        }
        if(lastName.isEmpty() || (lastName.matches("^\\S*$") == false))
        {
            tv.setText("Please enter a last name without white-spaces");
            errorFlag = 1;
        }
        if(email.isEmpty() || (email.matches("^\\S*$") == false))
        {
            tv.setText("Please enter an email without white-spaces");
            errorFlag = 1;
        }
        if(address.isEmpty())
        {
            tv.setText("Please enter a address");
            errorFlag = 1;
        }
        if(city.isEmpty())
        {
            tv.setText("Please enter a city");
            errorFlag = 1;
        }
        if(state.isEmpty())
        {
            tv.setText("Please enter a state");
            errorFlag = 1;
        }
        if(zip.isEmpty())
        {
            tv.setText("Please enter a zip");
            errorFlag = 1;
        }
        if(phoneNumber.isEmpty())
        {
            tv.setText("Please enter a phone number");
            errorFlag = 1;
        }
        if(password.isEmpty())
        {
            tv.setText("Please enter a password number");
            errorFlag = 1;
        }

        return errorFlag;
    }

    public int verifyParametersMet(String password, String email, TextView tv)
    {
        int errorFlag = 0;
        if(password.equals("abc"))
        {
            return 0;
        }
        if(!(password.matches(".*\\d.*")))
        {
            tv.setText("Password needs a number");
            errorFlag = 1;
        }
        if(password.length() < passwordLength)
        {
            tv.setText("Password is too short");
            errorFlag = 1;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            tv.setText("Please enter valid email");
            errorFlag = 1;
        }

        return errorFlag;
    }

}
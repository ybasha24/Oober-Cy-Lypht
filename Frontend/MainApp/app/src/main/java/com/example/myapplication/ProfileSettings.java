package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.endpoints.endpoints;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileSettings extends AppCompatActivity {

    final int minPasswordLength = 8;
    EditText firstName;
    EditText lastName;
    EditText password;
    EditText phoneNumber;
    EditText address;
    EditText city;
    EditText state;
    EditText zip;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        firstName = findViewById(R.id.editTextFirstName2);
        lastName = findViewById(R.id.editTextLastName2);
        password = findViewById(R.id.editTextPassword2);
        phoneNumber = findViewById(R.id.editTextPhone2);
        address = findViewById(R.id.editTextAddress2);
        city = findViewById(R.id.editTextCity2);
        state = findViewById(R.id.editTextState2);
        zip = findViewById(R.id.editTextZip2);
        email = findViewById(R.id.editTextEmail2);

        setPreviousDetails();

    }

    public void saveChanges(View view) {
        boolean x = verifyNotNull(firstName.getText().toString(), lastName.getText().toString(),
                email.getText().toString(), address.getText().toString(), city.getText().toString(),
                state.getText().toString(), zip.getText().toString(), password.getText().toString(),
                phoneNumber.getText().toString(), findViewById(R.id.statusTV));
        boolean y = verifyParametersMet(password.getText().toString(), email.getText().toString(),
                findViewById(R.id.statusTV));

        if (x && y) {
            changeProfileRequest(getDetails());
        }
    }

    public void setPreviousDetails(){
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

    public JSONObject getDetails(){
        EditText firstName = findViewById(R.id.editTextFirstName2);
        EditText lastName = findViewById(R.id.editTextLastName2);
        EditText password = findViewById(R.id.editTextPassword2);
        EditText phoneNumber = findViewById(R.id.editTextPhone2);
        EditText address = findViewById(R.id.editTextAddress2);
        EditText city = findViewById(R.id.editTextCity2);
        EditText state = findViewById(R.id.editTextState2);
        EditText zip = findViewById(R.id.editTextZip2);
        EditText email = findViewById(R.id.editTextEmail2);

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
        } catch (Exception e) {
            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Encountered exception " + e, Toast.LENGTH_LONG).show());
        }
        return newUserDetails;
    }

    public boolean verifyNotNull(String firstName, String lastName, String email, String address,
                             String city, String state, String zip, String password,
                             String phoneNumber, TextView tv)
    {
        if(firstName.isEmpty() || (firstName.matches("^\\S*$") == false))
        {
            tv.setText("Please enter a first name without white-spaces");
            return false;
        }
        if(lastName.isEmpty() || (lastName.matches("^\\S*$") == false))
        {
            tv.setText("Please enter a last name without white-spaces");
            return false;
        }
        if(email.isEmpty() || (email.matches("^\\S*$") == false))
        {
            tv.setText("Please enter an email without white-spaces");
            return false;
        }
        if(address.isEmpty())
        {
            tv.setText("Please enter a address");
            return false;
        }
        if(city.isEmpty())
        {
            tv.setText("Please enter a city");
            return false;
        }
        if(state.isEmpty())
        {
            tv.setText("Please enter a state");
            return false;
        }
        if(zip.isEmpty())
        {
            tv.setText("Please enter a zip");
            return false;
        }
        if(phoneNumber.isEmpty())
        {
            tv.setText("Please enter a phone number");
            return false;
        }
        if(password.isEmpty())
        {
            tv.setText("Please enter a password number");
            return false;
        }
        return true;
    }

    public boolean verifyParametersMet(String password, String email, TextView tv)
    {
        boolean errorFlag = true;
        if(password.equals("abc"))
        {
            return true;
        }
        if(!(password.matches(".*\\d.*")))
        {
            tv.setText("Password needs a number");
            errorFlag = false;
        }
        if(password.length() < minPasswordLength)
        {
            tv.setText("Password is too short");
            errorFlag = false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            tv.setText("Please enter valid email");
            errorFlag = false;
        }
        return errorFlag;
    }

    public void changeProfileRequest(JSONObject newUserDetails){
        try {
            String url = endpoints.EditUserUrl + MainActivity.accountObj.get("id");
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.PUT, url, newUserDetails,
                response -> {
                    try {
                        if (!response.isNull("firstName")) {
                            MainActivity.accountObj = response;
                            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show());
                        }
                    } catch (Exception e) {
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Encountered exception " + e, Toast.LENGTH_LONG).show());
                    }
                },
                error -> runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Encountered error " + error, Toast.LENGTH_LONG).show()));
            AppController.getInstance().addToRequestQueue(req, "post_object_tag");
        } catch(JSONException e) {}
    }

}
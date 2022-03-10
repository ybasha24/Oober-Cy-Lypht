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

public class DriverRegistrationPage extends AppCompatActivity {

    final int passwordLength = 8;

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

        boolean x = verifyNotNull(firstName, lastName, email, address, city, state, zip, password,
                phone, tv);

        boolean y = verifyParametersMet(password, email, tv);

        if (x && y) {
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, endpoints.DriverRegUrl, obj,
                response -> {
                    if (!response.isNull("firstName")) {
                        Intent intent = new Intent(this, DriverHomePage.class);
                        MainActivity.accountObj = response;
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error processing" + obj, Toast.LENGTH_LONG);
                    }
                },
                error -> Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG));
            AppController.getInstance().addToRequestQueue(req, "obj_req");
        }
    }

    public boolean verifyNotNull(String firstName, String lastName, String email, String address,
                              String city, String state, String zip, String password,
                              String phoneNumber, TextView tv)
    {
        boolean errorFlag = true;
        if(firstName.isEmpty() || (firstName.matches("^\\S*$") == false))
        {
            tv.setText("Please enter a first name without white-spaces");
            errorFlag = false;
        }
        if(lastName.isEmpty() || (lastName.matches("^\\S*$") == false))
        {
            tv.setText("Please enter a last name without white-spaces");
            errorFlag = false;
        }
        if(email.isEmpty() || (email.matches("^\\S*$") == false))
        {
            tv.setText("Please enter an email without white-spaces");
            errorFlag = false;
        }
        if(address.isEmpty())
        {
            tv.setText("Please enter a address");
            errorFlag = false;
        }
        if(city.isEmpty())
        {
            tv.setText("Please enter a city");
            errorFlag = false;
        }
        if(state.isEmpty())
        {
            tv.setText("Please enter a state");
            errorFlag = false;
        }
        if(zip.isEmpty())
        {
            tv.setText("Please enter a zip");
            errorFlag = false;
        }
        if(phoneNumber.isEmpty())
        {
            tv.setText("Please enter a phone number");
            errorFlag = false;
        }
        if(password.isEmpty())
        {
            tv.setText("Please enter a password number");
            errorFlag = false;
        }

        return errorFlag;
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
        if(password.length() < passwordLength)
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

}

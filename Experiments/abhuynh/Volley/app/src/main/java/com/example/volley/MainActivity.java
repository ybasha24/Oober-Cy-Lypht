package com.example.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volley.app.AppController;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private String tag_string_req = "string_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void stringRequest(View view){
        TextView textView = (TextView) findViewById(R.id.textview);
        String url = "https:api.androidhive.info/volley/string_response.html";

        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                textView.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                textView.setText("Error!");
            }
        });

        AppController.getInstance().addToRequestQueue(req, tag_string_req);

    }

    public void jsonObjectRequest(View view){
        TextView textView = (TextView) findViewById(R.id.textview);
        String url = "https:api.androidhive.info/volley/person_object.json";
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        textView.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error " + error.getMessage());
                    }
                });
        AppController.getInstance().addToRequestQueue(req, tag_string_req);

    }
}
package com.example.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.myapplication.R;
import com.example.myapplication.TripsAdapter;
import com.example.myapplication.UsersAdapter;
import com.example.myapplication.app.AppController;

import org.json.JSONArray;

public class UsersList extends AppCompatActivity {

    ListView listView;
    JSONArray usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        listView = findViewById(R.id.listView);
        String url = "";
        url = "http://coms-309-030.class.las.iastate.edu:8080/user/getAllUsers";
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    Log.e("Trips error", response.toString());
                    usersList = response;
                    listView.setAdapter(new UsersAdapter(usersList, getApplicationContext()) );
                },
                error -> Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG));
        AppController.getInstance().addToRequestQueue(req, "post_object_tag");
    }
}
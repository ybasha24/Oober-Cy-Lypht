package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.app.AppController;
import com.example.myapplication.driver.TripsAdapter;
import com.example.myapplication.endpoints.Endpoints;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatChooseUser extends AppCompatActivity {

    private ListView listView;
    private JSONArray usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_choose_user);
        listAllChattableUsers();
    }

    private void listAllChattableUsers(){
        try {
            listView = findViewById(R.id.listView);
            String url = Endpoints.AllDriverTripsUrl + MainActivity.accountObj.getInt("id");
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Log.e("response", response.toString());
                    if(response != null) {
                        try {
                            usersList = response.getJSONArray("riders");
                            listView.setAdapter(new TripsAdapter(usersList, getApplicationContext()));
                        }
                        catch(Exception e){
                            Log.e("error", e.toString());
                        }
                    }
                },
                error -> Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG));
            AppController.getInstance().addToRequestQueue(req, "obj_req");
        }
        catch(JSONException e){
            Log.e("error", e.toString());
        }
    }

}
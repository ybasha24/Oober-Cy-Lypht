package com.example.volleyremoteserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.volleyremoteserver.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private String tag_string_req = "string_req";
    private String tag_object_req = "object_req";
    private String tag_array_req = "array_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void postDriver(View view) throws JSONException {
        TextView textView = (TextView) findViewById(R.id.textview);
        String url = "http://coms-309-030.class.las.iastate.edu:8080/driver/registerDriver/";

        StringRequest req = new StringRequest(Request.Method.POST, url,
                response -> textView.setText(response),
                error -> textView.setText(error.toString()))
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }
            @Override
            public byte[] getBody() {
                try {
                    JSONObject obj = new JSONObject();
                    obj.put("firstName", new String("Matt"));
                    obj.put("lastName", new String("Sinwell"));
                    obj.put("address", "1234 lol St.");
                    obj.put("city", "Ames");
                    obj.put("state", "Iowa");
                    obj.put("zip", "50000");
                    obj.put("email", "g@s.com");
                    obj.put("phoneNumber", "515-911-1234");
                    String objString =  obj.toString();

                    return objString.toString().getBytes("utf-8");
                }
                catch (Exception e){
                    return null;
                }
            }
        };
        AppController.getInstance().addToRequestQueue(req, tag_object_req);
    }

    public void getDriver(View view) {
        TextView textView = (TextView) findViewById(R.id.textview);
        EditText editText = (EditText) findViewById(R.id.getDriverID);
        String id = editText.getText().toString();
        String url = "http://coms-309-030.class.las.iastate.edu:8080/driver/getDriver?id=" + id;

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> textView.setText(response.toString()),
                error -> textView.setText("Error obtaining driver!")
        );
        AppController.getInstance().addToRequestQueue(req, tag_object_req);
    }

    public void deleteDriver(View view){
        TextView textView = (TextView) findViewById(R.id.textview);
        EditText editText = (EditText) findViewById(R.id.getDriverID);
        String id = editText.getText().toString();
        String url = "http://coms-309-030.class.las.iastate.edu:8080/driver/deleteDriver?id=" + id;

        StringRequest req = new StringRequest(Request.Method.DELETE, url,
                response -> textView.setText("Successful delete!"),
                error -> textView.setText("Error deleting!")
        );
        AppController.getInstance().addToRequestQueue(req, tag_string_req);
    }


}
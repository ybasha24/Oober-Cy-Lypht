package com.example.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volley.app.AppController;
import com.example.volley.net_utils.Const;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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

    public void stringRequest(View view){
//        String url = "https:api.androidhive.info/volley/string_response.html";
        String url = "https://41096e03-1605-4363-a912-57afa92f86c7.mock.pstmn.io/bumboString";
        TextView textView = (TextView) findViewById(R.id.textview);

        StringRequest req = new StringRequest(Request.Method.GET, url,
            response -> {
                Log.d(TAG, response);
                textView.setText(response);
            },
            error -> {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                textView.setText("Error!");
            }
        );
        AppController.getInstance().addToRequestQueue(req, tag_string_req);
    }

    public void jsonObjectRequest(View view){
        TextView textView = (TextView) findViewById(R.id.textview);
//        String url = "https:api.androidhive.info/volley/person_object.json";
        String url = "https://41096e03-1605-4363-a912-57afa92f86c7.mock.pstmn.io/bumbo";
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
            response -> {
                Log.d(TAG, response.toString());
                textView.setText(response.toString());
            },
            error -> {
                VolleyLog.d(TAG, "Error " + error.getMessage());
                textView.setText("Error!");
            }
        );
        AppController.getInstance().addToRequestQueue(req, tag_object_req);
    }

    public void jsonArrayRequest(View view){
        TextView textView = (TextView) findViewById(R.id.textview);
//        String url = "https:api.androidhive.info/volley/person_array.json";
        String url = "https://41096e03-1605-4363-a912-57afa92f86c7.mock.pstmn.io/bumboArray";
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
            response -> {
                Log.d(TAG, response.toString());
                textView.setText(response.toString());
            },
            error -> {
                VolleyLog.d(TAG, "Error " + error.getMessage());
                textView.setText("Error!");
            }
        );
        AppController.getInstance().addToRequestQueue(req, tag_array_req);
    }

    public void imageRequest(View view){
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        imageLoader.get(Const.URL_IMAGE, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if(response.getBitmap() != null) imageView.setImageBitmap(response.getBitmap());
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error " + error.getMessage());
            }
        });
        //no need to add to request queue
    }

    public void jsonPostRequest(View view) throws JSONException {
        TextView textView = (TextView) findViewById(R.id.textview);
//        String url = "https://41096e03-1605-4363-a912-57afa92f86c7.mock.pstmn.io/create";
        JSONObject obj = new JSONObject();
//        obj.put("name", new String("He was bumbo"));

        String url = "https://reqres.in/api/users";
        obj.put("name", new String("Bumbo"));
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj,
                response->{
                    textView.setText("Bumbo was made.");
                },
                error -> {
                    textView.setText("Bumbo was not made.");
                }
        );
        AppController.getInstance().addToRequestQueue(req, tag_object_req);
    }

}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.myapplication.AppController;
import com.example.myapplication.Const;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
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
        setTitle("Home");
    }

    public void stringRequest(View view){
        String url = "https:api.androidhive.info/volley/string_response.html";
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
        String url = "https:api.androidhive.info/volley/person_object.json";
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
        String url = "https:api.androidhive.info/volley/person_array.json";
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
    }


//    public void disable(View v) {
//        View mv = findViewById(R.id.button);
//        mv.setEnabled(false);
//        Button b = (Button) mv;
//        b.setText("Button Off");
//    }
//
//    // Enter text and prints it out
//    public void handleInput(View v) {
//        //Takes user input
//        EditText t = findViewById(R.id.Source);
//        s = t.getText().toString();
//
//        int val = 0;
//        try {
//            val = Integer.parseInt(s.trim());
//
//        }
//        catch (NumberFormatException exec) {
//            flag = 0;
//            Toast.makeText(this, "Didn't work", Toast.LENGTH_LONG).show();
//            ((TextView) findViewById(R.id.output)).setText("Please type a number");
//            Toast.makeText(this, "Please type a number", Toast.LENGTH_LONG).show();
//        }
//
//        if(val > 4 || val < 1){
//            flag = 0;
//            Toast.makeText(this, "Didn't work", Toast.LENGTH_LONG).show();
//            ((TextView) findViewById(R.id.output)).setText("Please type a number between 1 and 4");
//        }
//        else
//        {
//            //((TextView) findViewById(R.id.output)).setText("Hello! " + s + "\nPlease click next to continue");
//            String url = ""; //TODO figure out how url is defined. Will try to pass in a string
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                    response -> Toast.makeText(MainActivity.this, "success", Toast.LENGTH_LONG)).show();
//            flag = 1;
//        }
//    }
//
//    public void launchSettings(View v) {
//        if (flag == 1) {
//            Intent i = new Intent(this, SettingsActivity.class);
//            i.putExtra("Cool", s);
//            startActivity(i);
//        } else {
//            Toast.makeText(this, "Please type your name", Toast.LENGTH_LONG).show();
//        }
//
//    }
}